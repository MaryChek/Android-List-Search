package com.example.favorite_cities.contract

interface FavoriteCitiesContract {

    interface View : CitiesContract.View

    interface Presenter : CitiesContract.Presenter<View> {

//        fun removeCity(nameCity: String)
    }
}