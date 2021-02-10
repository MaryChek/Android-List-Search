package com.example.favorite_cities.contract

import com.example.favorite_cities.DialogCreator

interface BaseContract {
    interface View {

        fun showCitiesList(citiesList: List<String>)

        fun updateCitiesList(modifiedList: List<String>)

        fun initList(citiesList: List<String>)

        fun initListener()

        fun itemDecorate()

        fun onCityClicked(nameCity: String)

        fun getResourceString(id: Int, vararg formatArgs: Any?): String

        fun showDialogFragment(dialogCreator: DialogCreator)
    }

    interface Presenter<V : View> {

        fun onAttachView(view: V)

        fun onViewCreated()

        fun onDestroy()

        fun searchTextChanged(text: String?)

        fun findCityInFavoriteModel(nameCity: String): Boolean

        fun showMessageAfterPositiveClick(id: Int, nameCity: String): String?

        fun onCityClicked(nameCity: String)
    }
}