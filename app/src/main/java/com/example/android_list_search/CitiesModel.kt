package com.example.android_list_search

class CitiesModel(private val listCities: List<String>) {

    fun filter(enteredText: String?): List<String> {
        val searchString = enteredText ?: ""
        return if (searchString.isBlank()) {
            listCities
        } else {
            listCities.filter {
                it.contains(searchString, true)
            }
        }
    }
}
