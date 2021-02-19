package com.example.favorite_cities.model

import com.example.favorite_cities.model.sharedpreferences.PreferenceManager

open class CitiesModel(
    private var generalCities: List<String>,
    private var preferenceManager: PreferenceManager
) {

    companion object {
        private const val KEY_SAVED_CITIES_FAVORITE = "CitiesFavorite"
    }

    private var generalCitiesFiltered: List<String>
    private var favoriteCities: List<String>
    private var favoriteCitiesFiltered: List<String>

    private var generalEnteredText: String? = null
    private var favoriteEnteredText: String? = null

    private var favoritesFragmentVisibility: Boolean = false

    init {
        generalCitiesFiltered = generalCities
        favoriteCities = getFavoriteSavedList()
        favoriteCitiesFiltered = favoriteCities
    }

    fun updateGeneralCitiesList(newCitiesList: List<String>) {
        generalCities = newCitiesList
        generalCitiesFiltered = getCurrentCitiesFiltered(generalCities, generalEnteredText)
        favoriteCities = getFavoriteSavedList()
        favoriteCitiesFiltered = getCurrentCitiesFiltered(favoriteCities, favoriteEnteredText)
    }

    fun getGeneralCitiesFiltered(): List<String> =
        generalCitiesFiltered

    fun isGeneralCitiesFilteredEmpty(): Boolean =
        generalCitiesFiltered.isEmpty()

    fun getGeneralEnteredText(): String? =
        generalEnteredText

    fun filterGeneralList(enteredText: String?): List<String> {
        generalEnteredText = enteredText

        val filteredList: List<String> = filter(enteredText, generalCities)
        generalCitiesFiltered = filteredList
        return filteredList
    }

    fun isFavoriteCitiesEmpty(): Boolean =
        favoriteCities.isEmpty()

    fun isFavoriteCitiesNotEmpty(): Boolean =
        favoriteCities.isNotEmpty()

    fun getFavoriteCitiesFiltered(): List<String> =
        favoriteCitiesFiltered

    fun isFavoriteCitiesFilteredEmpty(): Boolean =
        favoriteCitiesFiltered.isEmpty()

    fun getFavoriteEnteredText(): String? =
        favoriteEnteredText

    fun filterFavoriteList(enteredText: String?): List<String> {
        favoriteEnteredText = enteredText
        val filteredList: List<String> = filter(enteredText, favoriteCities)
        favoriteCitiesFiltered = filteredList
        return filteredList
    }

    fun setVisibleFavoriteFragment(visible: Boolean) {
        favoritesFragmentVisibility = visible
    }

    fun getVisibleFavoriteFragment(): Boolean =
        favoritesFragmentVisibility

    fun addFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.plus(nameCity)
        favoriteCitiesFiltered = filter(favoriteEnteredText, favoriteCities)

        saveFavoriteList(favoriteCities)
    }

    fun removeFavoriteCity(nameCity: String) {
        favoriteCities = favoriteCities.minus(nameCity)
        favoriteCitiesFiltered = favoriteCitiesFiltered.minus(nameCity)

        saveFavoriteList(favoriteCities)
    }

    fun findInFavorites(nameCity: String): Boolean =
        find(nameCity, favoriteCities)

    private fun getCurrentCitiesFiltered(fullCitiesList: List<String>, enteredText: String?) =
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
        val hashSet: HashSet<String> = getHashSet(savedList)
        preferenceManager.setList(KEY_SAVED_CITIES_FAVORITE, hashSet)
    }

    private fun getFavoriteSavedList(): List<String> {
        val hashSet: Set<String> = preferenceManager.getList(KEY_SAVED_CITIES_FAVORITE)
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