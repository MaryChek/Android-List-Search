package com.example.favorite_cities

import com.example.favorite_cities.sharedpreferences.PreferenceManager

class CitiesModel(
    private var generalCities: List<String>,
    private var preferenceManager: PreferenceManager
) {

    private var favoriteCities = preferenceManager.getList(generalCities)
    private var generalFilteredList: List<String>
    private var favoriteFilteredList: List<String>

    private var currentlyEnteredTextInFavorites: String? = null

    init {
        generalFilteredList = generalCities
        favoriteFilteredList = favoriteCities
    }

//    fun initGeneralList(list: List<String>) {
//        generalCities = list
//    }

    fun getFavoriteCities(): List<String> =
        favoriteCities

    fun getFilterFavoriteCities(): List<String> =
        favoriteFilteredList

    fun getGeneralCities(): List<String> =
        generalCities

    fun filterFavoriteList(enteredText: String?): List<String> {
        currentlyEnteredTextInFavorites = enteredText
        val filteredList = filter(enteredText, favoriteCities)
        favoriteFilteredList = filteredList
        return filteredList
    }

    fun filterGeneralList(enteredText: String?): List<String> {
        val filteredList = filter(enteredText, generalCities)
        generalFilteredList = filteredList
        return filteredList
    }

    fun addFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.plus(nameCity)

        favoriteFilteredList = filter(currentlyEnteredTextInFavorites, favoriteCities)

        preferenceManager.setList(favoriteCities, generalCities)
    }

    fun removeFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.minus(nameCity)
        favoriteFilteredList = favoriteFilteredList.minus(nameCity)

        preferenceManager.setList(favoriteCities, generalCities)
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
}
