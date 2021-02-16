package com.example.favorite_cities.contract

import com.example.favorite_cities.DialogCreator

interface CitiesContract {
    interface View : BaseContract.View {

        fun showCitiesList(citiesList: List<String>)

        fun updateCitiesList(modifiedList: List<String>)

        fun showDialogFragment(dialogCreator: DialogCreator?)

        fun setEnteredText(text: CharSequence)

        fun showMessageAfterPositiveClick(stringId: Int, nameCity: String): String
    }

    interface Presenter<V : View> : BaseContract.Presenter<V> {
        fun onFragmentVisible()

        fun searchTextChanged(text: String?)

        fun onCityClicked(nameCity: String)
    }
}