package com.example.favorite_cities.contract

import com.example.favorite_cities.DialogCreator

interface CitiesContract {
    interface View: BaseContract.View {

        fun showCitiesList(citiesList: List<String>)

        fun updateCitiesList(modifiedList: List<String>)

//        fun initList(citiesList: List<String>) // privet

//        fun initListener()

//        fun itemDecorate()

//        fun onCityClicked(nameCity: String)

        fun getResourceString(id: Int, vararg formatArgs: Any?): String

        fun showDialogFragment(dialogCreator: DialogCreator)

        fun getResourceId(nameString: String): Int?

        fun setEnteredText(text: CharSequence)
    }

    interface Presenter<V: View>: BaseContract.Presenter<V> {
        fun onFragmentVisible()

        fun searchTextChanged(text: String?)

        fun findCityInFavoriteModel(nameCity: String): Boolean

        fun showMessageAfterPositiveClick(id: Int, nameCity: String): String?

        fun onCityClicked(nameCity: String)
    }
}