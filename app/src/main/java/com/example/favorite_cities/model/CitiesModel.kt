package com.example.favorite_cities.model

import com.example.favorite_cities.model.sharedpreferences.PreferenceManager

class CitiesModel(
    private var generalCities: List<String>,
    private var preferenceManager: PreferenceManager
) {

    companion object {
        private const val GENERAL_ENTERED_TEXT = "General"
        private const val FAVORITE_ENTERED_TEXT = "Favorite"
        private const val VISIBILITY_FAVORITE_FRAGMENT = "Visible Favorite Fragment"
        private const val CITIES_FAVORITE_LIST = "CitiesFavorite"
    }

    private var favoriteCities: List<String>
    private var generalCitiesFiltered: List<String>
    private var favoriteCitiesFiltered: List<String>

    private var enteredTextInGeneral: String?
    private var enteredTextInFavorites: String?

    init {
        enteredTextInGeneral = getSavedEnteredText(GENERAL_ENTERED_TEXT)
        enteredTextInFavorites = getSavedEnteredText(FAVORITE_ENTERED_TEXT)

        favoriteCities = getFavoriteSavedList()

        generalCitiesFiltered = generalCities

        favoriteCitiesFiltered =
            if (enteredTextInGeneral.isNullOrEmpty()) {
                favoriteCities
            } else {
                filter(enteredTextInFavorites, favoriteCities)
            }
    }

    fun getGeneralCities(): List<String> =
        generalCities

    fun getFilteredGeneralCities(): List<String> =
        generalCitiesFiltered

    fun getEnteredTextInGeneral(): String? =
        enteredTextInGeneral

    fun filterGeneralList(enteredText: String?): List<String> {
        enteredTextInFavorites = enteredText
        saveEnteredText(GENERAL_ENTERED_TEXT, enteredText)

        val filteredList: List<String> = filter(enteredText, generalCities)
        generalCitiesFiltered = filteredList
        return filteredList
    }

    fun getFavoriteCities(): List<String> =
        favoriteCities

    fun getFilteredFavoriteCities(): List<String> =
        favoriteCitiesFiltered

    fun getEnteredTextInFavorite(): String? =
        enteredTextInFavorites

    fun filterFavoriteList(enteredText: String?): List<String> {
        enteredTextInFavorites = enteredText
        saveEnteredText(FAVORITE_ENTERED_TEXT, enteredText)

        val filteredList: List<String> = filter(enteredText, favoriteCities)
        favoriteCitiesFiltered = filteredList
        return filteredList
    }

    fun setVisibleFavoriteFragment(visible: Boolean) {
        preferenceManager.setBoolean(VISIBILITY_FAVORITE_FRAGMENT, visible)
    }

    fun getVisibleFavoriteFragment(): Boolean =
        preferenceManager.getBoolean(VISIBILITY_FAVORITE_FRAGMENT)

    fun addFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.plus(nameCity)
        favoriteCitiesFiltered = filter(enteredTextInFavorites, favoriteCities)

        saveFavoriteList(favoriteCities)
    }

    fun removeFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.minus(nameCity)
        favoriteCitiesFiltered = favoriteCitiesFiltered.minus(nameCity)

        saveFavoriteList(favoriteCities)
    }

    fun findInFavorites(nameCity: String): Boolean =
        find(nameCity, favoriteCities)

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

    private fun saveEnteredText(saveKey: String, enteredText: String?) {
        preferenceManager.setString(saveKey, enteredText)
    }

    private fun getSavedEnteredText(saveKey: String): String? =
        preferenceManager.getString(saveKey)

    private fun saveFavoriteList(savedList: List<String>) {
        val hashSet: HashSet<String> = getHashSet(savedList)
        preferenceManager.setList(CITIES_FAVORITE_LIST, hashSet)
    }

    private fun getFavoriteSavedList(): List<String> {
        val hashSet: Set<String> = preferenceManager.getList(CITIES_FAVORITE_LIST)
        var list: List<String> = listOf()
        hashSet.forEach {
            val index: Int = it.toInt()
            list = list.plus(generalCities[index])
        }
        return list
    }

    private fun getHashSet(list: List<String>): HashSet<String> {
        val hashSet: HashSet<String> = HashSet()
        list.forEach {
            hashSet.add(generalCities.indexOf(it).toString())
        }
        return hashSet
    }
}
