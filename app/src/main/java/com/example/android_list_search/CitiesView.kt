package com.example.android_list_search

import android.view.View

interface CitiesView {
    fun showCitiesList(citiesList: List<String>)

    fun updateCitiesList(modifiedList: List<String>)

    fun showSlid(view: View)

    fun hideSlid(view: View)
}