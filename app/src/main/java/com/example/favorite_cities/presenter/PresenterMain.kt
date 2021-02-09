package com.example.favorite_cities.presenter

import com.example.favorite_cities.CitiesView

interface PresenterMain {
    fun attachView(activity: CitiesView)

    fun viewIsReady()

    fun searchTextChanged(text: String?)

    fun findCityInFavoriteModel(nameCity: String): Boolean

    fun getResourceString(id: Int, vararg formatArgs: Any?): String

    fun addCityToFavorite(nameCity: String)

    fun removeCityFromFavorite(nameCity: String)

    fun createCitiesModel(citiesList: List<String>)

    fun onCityClicked(nameCity: String)
}