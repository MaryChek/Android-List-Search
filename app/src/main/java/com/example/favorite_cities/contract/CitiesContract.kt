package com.example.favorite_cities.contract

interface CitiesContract {
    interface View : BaseContract.View {

        fun showCitiesList(citiesList: List<String>)

        fun updateCitiesList(modifiedList: List<String>)

        fun showDialog(title: String, messageId: Int, positiveButtonId: Int, negativeButtonId: Int)

        fun showToastWithText(stringId: Int, nameCity: String)

        fun setEnteredText(text: CharSequence)
    }

    interface Presenter<V : View> : BaseContract.Presenter<V> {
        fun searchTextChanged(text: String?)

        fun onFragmentVisible()

        fun onCityClicked(nameCity: String)

        fun afterPositiveClickInDialog(positiveButtonId: Int, nameCity: String)
    }
}