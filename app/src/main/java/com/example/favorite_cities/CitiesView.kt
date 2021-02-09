package com.example.favorite_cities

import android.view.View

interface CitiesView {
    fun showCitiesList(citiesList: List<String>)

    fun updateCitiesList(modifiedList: List<String>)

    fun showSlid(view: View)

    fun hideSlid(view: View)

    fun initList(citiesList: List<String>)

    fun initListener()

    fun itemDecorate()

    fun onCityClicked(nameCity: String)
}