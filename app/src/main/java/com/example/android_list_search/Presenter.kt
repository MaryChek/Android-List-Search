package com.example.android_list_search

import android.widget.Toast

class Presenter {
    private lateinit var view: MainActivity
    private lateinit var model: CitiesModel

    fun attachView(activity: MainActivity) {
        view = activity
    }

    fun viewIsReady() {
        val citiesList = getCitiesList()
        view.showCitiesList(citiesList)
        createCitiesModel(citiesList)
    }

    fun searchTextChanged(text: String?) {
        val filteredList = model.filter(text)
        if (filteredList.isEmpty()) {
            view.showSlidedNothingFound()
        } else {
            view.hideSlidedNothingFound()
        }
        view.updateCitiesList(filteredList)
    }

    fun onCityClicked(nameCity: String) {
        val toastText = view.resources.getString(R.string.message_selected_city, nameCity)
        Toast.makeText(view, toastText, Toast.LENGTH_SHORT).show()
    }

    private fun createCitiesModel(citiesList: List<String>) {
        model = CitiesModel(citiesList)
    }

    private fun getCitiesList(): List<String> =
        view.resources.getStringArray(R.array.cities).toList()

//    private fun getResourceString(id: Int, vararg: Any? = null) =
//        view.resources.getString(id, vararg)
}