package com.example.android_list_search.presenter

import com.example.android_list_search.CitiesView

interface PresenterMain {
    fun attachView(activity: CitiesView)

    fun viewIsReady()

    fun searchTextChanged(text: String?)

    fun findCityInFavoriteModel(nameCity: String): Boolean

    fun getResourceString(id: Int, vararg formatArgs: Any?): String

    fun addCityToFavorite(nameCity: String)

    fun removeCityFromFavorite(nameCity: String)
}