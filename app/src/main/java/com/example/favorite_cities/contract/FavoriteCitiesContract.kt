package com.example.favorite_cities.contract

interface FavoriteCitiesContract {

    interface View : CitiesContract.View {

        fun showTextSlide(text: String, show: Boolean)
    }

    interface Presenter : CitiesContract.Presenter<View> {

        fun removeCity(nameCity: String)
    }
}