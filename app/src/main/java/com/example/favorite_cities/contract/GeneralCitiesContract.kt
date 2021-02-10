package com.example.favorite_cities.contract

interface GeneralCitiesContract {

    interface View : BaseContract.View {
        fun showSlidNothingFound(show: Boolean)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun addCityToFavorite(nameCity: String)

        fun removeCityFromFavorite(nameCity: String)
    }
}