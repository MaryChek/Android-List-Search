package com.example.android_list_search

class CitiesModel(private val listCities: List<String>) {

    fun filter(charSequence: CharSequence?) : List<String> {
        val searchString = charSequence ?: ""
        return if (searchString.isBlank())
            listCities
        else
            listCities.filter { it.contains(searchString, true)}
    }

}