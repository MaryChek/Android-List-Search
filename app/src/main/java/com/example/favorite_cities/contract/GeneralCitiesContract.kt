package com.example.favorite_cities.contract

interface GeneralCitiesContract {

    interface View : CitiesContract.View

    interface Presenter : CitiesContract.Presenter<View> {

//        fun addFavoriteCity(nameCity: String)
//
//        fun removeFavoriteCity(nameCity: String)
    }
}