package com.example.favorite_cities.contract

interface CitiesContract {
    interface View : BaseContract.View {

        fun showCitiesList(citiesList: List<String>)

        fun updateCitiesList(modifiedList: List<String>)

        fun showDialog(title: String, messageId: Int, positiveButtonId: Int, negativeButtonId: Int)

        fun setEnteredText(text: CharSequence)

        fun showToastWithText(stringId: Int, nameCity: String)
    }

    interface Presenter<V : View> : BaseContract.Presenter<V> {
        fun onFragmentVisible()

        fun searchTextChanged(text: String?)

        fun onCityClicked(nameCity: String)

        fun afterPositiveClickInDialog(positiveButtonId: Int, nameCity: String)
//
//        fun afterPositiveClickInDialog(nameCity: String)
    }
}