package com.example.favorite_cities.contract

interface CitiesContract {
    interface View : BaseContract.View {
        fun showCitiesList(citiesList: List<String>)

        fun updateCitiesList(modifiedList: List<String>)

        fun showDialogAdding()

        fun showDialogRemoving()

        fun showSearchError(textId: Int)

        fun hideSearchError()

        fun showToastWithText(stringId: Int, nameCity: String)

        fun setEnteredText(text: CharSequence)
    }

    interface Presenter<V : View> : BaseContract.Presenter<V> {
        fun onFragmentVisible()

        fun onCityClicked(nameCity: String)

        fun searchTextChanged(text: String?)

        fun addFavoriteCity(nameCity: String)

        fun removeFavoriteCity(nameCity: String)
    }
}