package com.example.favorite_cities.model

import androidx.annotation.VisibleForTesting
import com.example.favorite_cities.model.sharedpreferences.PreferenceManager

open class CitiesModel(
    newCitiesList: List<String>,
    private var preferenceManager: PreferenceManager?
) {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var generalCities: List<String> = listOf()
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var favoriteCities: List<String> = listOf()

    private var generalCitiesFiltered: List<String> = listOf()
    private var favoriteCitiesFiltered: List<String> = listOf()

    private var generalEnteredText: String? = null
    private var favoriteEnteredText: String? = null

    private var favoritesFragmentVisibility: Boolean = false

    init {
        generalCities = newCitiesList
        generalCitiesFiltered = generalCities
        favoriteCities = getFavoriteSavedList()
        favoriteCitiesFiltered = favoriteCities
    }

    fun updateCitiesLists(newCitiesList: List<String>) {
        generalCities = newCitiesList
        generalCitiesFiltered = getCurrentCitiesList(generalCities, generalEnteredText)
        favoriteCities = getFavoriteSavedList()
        favoriteCitiesFiltered = getCurrentCitiesList(favoriteCities, favoriteEnteredText)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    open fun updateFavoriteCities(newCitiesList: List<String>) {
        favoriteCities = newCitiesList
    }

    open fun getGeneralCitiesFiltered(): List<String> =
        generalCitiesFiltered

    open fun getFavoriteCitiesFiltered(): List<String> =
        favoriteCitiesFiltered

    open fun getGeneralEnteredText(): String? =
        generalEnteredText

    open fun getFavoriteEnteredText(): String? =
        favoriteEnteredText

    open fun isFavoriteCitiesEmpty(): Boolean =
        favoriteCities.isEmpty()

    open fun isFavoriteCitiesNotEmpty(): Boolean =
        favoriteCities.isNotEmpty()

    open fun isGeneralCitiesFilteredEmpty(): Boolean =
        generalCitiesFiltered.isEmpty()

    open fun isFavoriteCitiesFilteredEmpty(): Boolean =
        favoriteCitiesFiltered.isEmpty()

    fun filterGeneralList(enteredText: String?) {
        setGeneralEnteredText(enteredText)
        generalCitiesFiltered = filter(enteredText, generalCities)
    }

    fun filterFavoriteList(enteredText: String?) {
        setFavoriteEnteredText(enteredText)
        favoriteCitiesFiltered = filter(enteredText, favoriteCities)
    }

    open fun setVisibleFavoriteFragment(visible: Boolean) {
        favoritesFragmentVisibility = visible
    }

    open fun getVisibleFavoriteFragment(): Boolean =
        favoritesFragmentVisibility

    open fun addFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.plus(nameCity)
        favoriteCitiesFiltered = filter(favoriteEnteredText, favoriteCities)

        saveFavoriteList(favoriteCities)
    }

    open fun removeFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.minus(nameCity)
        favoriteCitiesFiltered = favoriteCitiesFiltered.minus(nameCity)

        saveFavoriteList(favoriteCities)
    }

    open fun findInFavorites(nameCity: String): Boolean =
        find(nameCity, favoriteCities)

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    open fun setGeneralEnteredText(value: String?) {
        generalEnteredText = value
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    open fun setFavoriteEnteredText(value: String?) {
        favoriteEnteredText = value
    }

    private fun getCurrentCitiesList(
        fullCitiesList: List<String>,
        enteredText: String?
    ): List<String> =
        when (enteredText.isNullOrEmpty()) {
            true -> fullCitiesList
            false -> filter(enteredText, fullCitiesList)
        }

    private fun filter(enteredText: String?, ListCities: List<String>): List<String> {
        val searchString: String = enteredText ?: ""
        return if (searchString.isBlank()) {
            ListCities
        } else {
            ListCities.filter {
                it.contains(searchString, true)
            }
        }
    }

    private fun find(nameCity: String, listCities: List<String>): Boolean =
        listCities.any {
            it == nameCity
        }

    private fun saveFavoriteList(savedList: List<String>) {
        val citiesIndexSet: HashSet<String> = getIndexHashSetOf(savedList)
        preferenceManager?.setList(KEY_SAVED_CITIES_FAVORITE, citiesIndexSet)
    }

    private fun getFavoriteSavedList(): List<String> {
        val hashSet: Set<String>? = preferenceManager?.getList(KEY_SAVED_CITIES_FAVORITE)
        var list: List<String> = listOf()
        hashSet?.forEach {
            val index: Int = it.toInt()
            list = list.plus(generalCities[index])
        }
        return list
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    open fun getIndexHashSetOf(list: List<String>): HashSet<String> {
        val indexSet: HashSet<String> = HashSet()
        list.forEach {
            indexSet.add(generalCities.indexOf(it).toString())
        }
        return indexSet
    }

    companion object {
        private const val KEY_SAVED_CITIES_FAVORITE = "CitiesFavorite"
    }
}
