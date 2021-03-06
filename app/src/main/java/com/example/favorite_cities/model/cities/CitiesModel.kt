package com.example.favorite_cities.model.cities

import androidx.annotation.VisibleForTesting
import com.example.favorite_cities.R
import com.example.favorite_cities.App
import com.example.favorite_cities.model.sharedpreferences.PreferenceManager

class CitiesModel(
    private val activity: App,
    citiesList: List<String> = activity.resources.getStringArray(R.array.cities).toList(),
    private val preferenceManager: PreferenceManager = PreferenceManager(activity)
) {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var generalCities: List<String> = listOf()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var favoriteCities: List<String> = listOf()

    var generalEnteredText: String? = null
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        set
    var favoriteEnteredText: String? = null
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        set

    var positionOfTheCurrentPage = 0

    init {
        generalCities = citiesList
        favoriteCities = getFavoriteSavedList()
    }

    fun updateCitiesLists() {
        generalCities = activity.resources.getStringArray(R.array.cities).toList()
        favoriteCities = getFavoriteSavedList()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getFavoriteSavedList(): List<String> {
        val list: MutableList<String> = mutableListOf()
        preferenceManager.getSetByKey(KEY_INDEXES_OF_FAVORITE).forEach { strIndex ->
            strIndex.toIntOrNull()?.let {
                list.add(generalCities[it])
            }
        }
        return list
    }

    fun getGeneralCitiesIconFiltered(): List<CityIcon> =
        getListOfCityIcons(
            filter(generalEnteredText, generalCities),
            ListType.GENERAL
        )

    fun getFavoriteCitiesIconFiltered(): List<CityIcon> =
        getListOfCityIcons(
            filter(favoriteEnteredText, favoriteCities),
            ListType.FAVORITE
        )

    private fun filter(enteredText: String?, ListCities: List<String>): List<String> =
        when (enteredText.isNullOrBlank()) {
            true -> ListCities
            false -> ListCities.filter {
                it.contains(enteredText, true)
            }
        }

    private fun getListOfCityIcons(listCities: List<String>, type: ListType): List<CityIcon> {
        val cityIconsList: MutableList<CityIcon> = mutableListOf()
        listCities.forEach { nameCity ->
            val iconId: Int = getIconByListType(type, nameCity)
            val cityIcon = CityIcon(nameCity, iconId)
            cityIconsList.add(cityIcon)
        }
        return cityIconsList
    }

    private fun getIconByListType(type: ListType, nameCity: String): Int =
        when (type) {
            ListType.GENERAL -> getIconForGeneralCity(nameCity)
            ListType.FAVORITE -> android.R.drawable.ic_menu_delete
        }

    private fun getIconForGeneralCity(nameCity: String): Int =
        when (findInFavorites(nameCity)) {
            true -> android.R.drawable.btn_star_big_on
            false -> android.R.drawable.btn_star_big_off
        }

    fun isFavoriteCitiesEmpty(): Boolean =
        favoriteCities.isEmpty()

    fun filterGeneralList(enteredText: String?) {
        generalEnteredText = enteredText
    }

    fun filterFavoriteList(enteredText: String?) {
        favoriteEnteredText = enteredText
    }

    fun addFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.plus(nameCity)
    }

    fun removeFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.minus(nameCity)
    }

    fun findInFavorites(nameCity: String): Boolean =
        favoriteCities.any {
            it == nameCity
        }

    fun saveFavoriteList() =
        saveFavoriteList(favoriteCities)

    private fun saveFavoriteList(savedList: List<String>) {
        val citiesIndexSet: Set<String> = getIndexSetOf(savedList)
        preferenceManager.putSetByKey(KEY_INDEXES_OF_FAVORITE, citiesIndexSet)
    }

    private fun getIndexSetOf(list: List<String>): Set<String> {
        val setOfIndex: MutableSet<String> = mutableSetOf()
        list.forEach {
            val strIndex = generalCities.indexOf(it).toString()
            setOfIndex.add(strIndex)
        }
        return setOfIndex
    }

    companion object {
        private const val KEY_INDEXES_OF_FAVORITE = "CitiesFavorite"
    }

    enum class ListType {
        GENERAL, FAVORITE
    }
}
