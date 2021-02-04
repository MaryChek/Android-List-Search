package com.example.android_list_search

interface CitiesView {
    fun showCitiesList(citiesList: List<String>)

    fun updateCitiesList(filteredList: List<String>)

    fun showSlidedNothingFound()

    fun hideSlidedNothingFound()
}