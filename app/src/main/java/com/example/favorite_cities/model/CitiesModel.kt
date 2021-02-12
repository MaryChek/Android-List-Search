package com.example.favorite_cities.model

import com.example.favorite_cities.model.sharedpreferences.PreferenceManager

class CitiesModel(
    private var generalCities: List<String>,
    private var preferenceManager: PreferenceManager
) {

    private var favoriteCities: List<String>
    private var generalFilteredList: List<String>
    private var favoriteFilteredList: List<String>

    private var currentlyEnteredTextInFavorites: String?
    private var currentlyEnteredTextInGeneral: String?

    init {
        currentlyEnteredTextInGeneral = getSavedEnteredText("General")
        currentlyEnteredTextInFavorites = getSavedEnteredText("Favorite")

        generalFilteredList = generalCities

        favoriteCities = getFavoriteSavedList()

        favoriteFilteredList =
            if (currentlyEnteredTextInFavorites.isNullOrEmpty()) {
                favoriteCities
            } else {
                filter(currentlyEnteredTextInFavorites, favoriteCities)
            }
    }

    fun getFavoriteCities(): List<String> =
        favoriteCities

    fun getFilteredFavoriteCities(): List<String> =
        favoriteFilteredList

    fun getFilteredGeneralCities(): List<String> =
        generalFilteredList

    fun getEnteredTextInFavorite(): String? =
        currentlyEnteredTextInFavorites

    fun getGeneralCities(): List<String> =
        generalCities

    fun getEnteredTextInGeneral(): String? =
        currentlyEnteredTextInGeneral

    fun setVisibleFavoriteFragment(visible: Boolean) {
        preferenceManager.setBoolean("Visible Favorite Fragment", visible)
    }

    fun getVisibleFavoriteFragment(): Boolean =
        preferenceManager.getBoolean("Visible Favorite Fragment")

    fun filterFavoriteList(enteredText: String?): List<String> {
        currentlyEnteredTextInFavorites = enteredText
        saveEnteredText("Favorite", enteredText)
        val filteredList = filter(enteredText, favoriteCities)
        favoriteFilteredList = filteredList
        return filteredList
    }

    fun filterGeneralList(enteredText: String?): List<String> {
        currentlyEnteredTextInGeneral = enteredText
        saveEnteredText("General", enteredText)
        val filteredList = filter(enteredText, generalCities)
        generalFilteredList = filteredList
        return filteredList
    }

    fun addFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.plus(nameCity)
        favoriteFilteredList = filter(currentlyEnteredTextInFavorites, favoriteCities)
        saveFavoriteList(favoriteCities)
    }

    fun removeFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.minus(nameCity)
        favoriteFilteredList = favoriteFilteredList.minus(nameCity)
        saveFavoriteList(favoriteCities)
    }

    fun findInFavorites(nameCity: String): Boolean =
        find(nameCity, favoriteCities)

    private fun filter(enteredText: String?, ListCities: List<String>): List<String> {
        val searchString = enteredText ?: ""
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
        val hashSet = getHashSet(savedList)
        preferenceManager.setList("CitiesFavorite", hashSet)
    }

    private fun getFavoriteSavedList(): List<String> {
        val hashSet: Set<String> = preferenceManager.getList("CitiesFavorite")
        var list = listOf<String>()
        hashSet.forEach {
            val index = it.toInt()
            list = list.plus(generalCities[index])
        }
        return list
    }

    private fun getHashSet(list: List<String>): HashSet<String> {
        val hashSet = HashSet<String>()
        list.forEach {
            hashSet.add(generalCities.indexOf(it).toString())
        }
        return hashSet
    }
}
