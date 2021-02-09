package com.example.android_list_search.presenter

class PresenterCommon {
    private lateinit var presenterFavorite: PresenterFavorite
    private lateinit var presenterGeneral: PresenterGeneral

    fun attachPresenterGeneral(presenter: PresenterGeneral) {
        presenterGeneral = presenter
    }

    fun attachPresenterFavorite(presenter: PresenterFavorite) {
        presenterFavorite = presenter
    }

    fun findCityInFavoriteModel(nameCity: String): Boolean =
        presenterFavorite.findCityInFavoriteModel(nameCity)

    fun addCityToFavorite(nameCity: String) {
        presenterFavorite.addCityToFavorite(nameCity)
    }

    fun removeCityToFavorite(nameCity: String) {
        presenterFavorite.removeCityFromFavorite(nameCity)
    }
}