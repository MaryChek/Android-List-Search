package com.example.favorite_cities.contract

interface FavoriteCitiesContract {

    interface View : BaseContract.View {

        fun showSlideNothingFound(show: Boolean)

        fun showSlideNoFavorites(show: Boolean)

        fun getEnteredText(): String
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onFragmentVisible()

        fun removeCity(nameCity: String)
    }
}