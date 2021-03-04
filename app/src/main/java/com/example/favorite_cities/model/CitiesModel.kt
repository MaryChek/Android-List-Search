package com.example.favorite_cities.model

import androidx.annotation.VisibleForTesting
import com.example.favorite_cities.App
import com.example.favorite_cities.R
import com.example.favorite_cities.model.sharedpreferences.PreferenceManager

class CitiesModel(
    private val activity: App,
    newCitiesList: List<String> = activity.resources.getStringArray(R.array.cities).toList(),
    private val preferenceManager: PreferenceManager = PreferenceManager(activity)
) {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var generalCities: List<String> = listOf()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var favoriteCities: List<String> = listOf()

    var generalEnteredText: String? = null
        private set
    var favoriteEnteredText: String? = null
        private set

    var positionOfTheCurrentFragment = 0

    init {
        generalCities = newCitiesList
        favoriteCities = getFavoriteSavedList()
    }

    fun updateCitiesLists() {
        generalCities = activity.resources.getStringArray(R.array.cities).toList()
        favoriteCities = getFavoriteSavedList()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun updateFavoriteCities(newCitiesList: List<String>) {
        favoriteCities = newCitiesList
    }

    fun getGeneralCitiesFiltered(): List<String> =
        filter(generalEnteredText, generalCities)

    fun getFavoriteCitiesFiltered(): List<String> =
        filter(favoriteEnteredText, favoriteCities)

    fun isFavoriteCitiesEmpty(): Boolean =
        favoriteCities.isEmpty()

    fun isFavoriteCitiesNotEmpty(): Boolean =
        favoriteCities.isNotEmpty()

//    fun isGeneralCitiesFilteredEmpty(): Boolean =
//        getGeneralCitiesFiltered().isEmpty()
//
//    fun isFavoriteCitiesFilteredEmpty(): Boolean =
//        getFavoriteCitiesFiltered().isEmpty()

    fun filterGeneralList(enteredText: String?) =
        setGeneralEnteredText(enteredText)

    fun filterFavoriteList(enteredText: String?) =
        setFavoriteEnteredText(enteredText)

    fun addFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.plus(nameCity)
        saveFavoriteList(favoriteCities)
    }

    fun removeFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.minus(nameCity)
        saveFavoriteList(favoriteCities)
    }

    fun findInFavorites(nameCity: String): Boolean =
        favoriteCities.any {
            it == nameCity
        }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun setGeneralEnteredText(value: String?) {
        generalEnteredText = value
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun setFavoriteEnteredText(value: String?) {
        favoriteEnteredText = value
    }

    private fun filter(enteredText: String?, ListCities: List<String>): List<String> =
        when (enteredText.isNullOrBlank()) {
            true -> ListCities
            false -> ListCities.filter {
                it.contains(enteredText, true)
            }
        }

    private fun saveFavoriteList(savedList: List<String>) {
        val citiesIndexSet: Set<String> = getIndexSetOf(savedList)
        preferenceManager.putSetByKey(KEY_INDEXES_OF_FAVORITE, citiesIndexSet)
    }

    private fun getFavoriteSavedList(): List<String> {
        val list: MutableList<String> = mutableListOf()
        preferenceManager.getSetByKey(KEY_INDEXES_OF_FAVORITE).forEach { strIndex ->
            strIndex.toIntOrNull()?.let {
                list.add(generalCities[it])
            }
        }
        return list
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getIndexSetOf(list: List<String>): Set<String> {
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
}
