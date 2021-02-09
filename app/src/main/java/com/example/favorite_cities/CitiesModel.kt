package com.example.favorite_cities

class CitiesModel(private var listCities: List<String>) {

    private var filteredList: List<String>

    init {
        filteredList = listCities
    }


    fun filter(enteredText: String?): List<String> {
        val searchString = enteredText ?: ""
        return if (searchString.isBlank()) {
            filteredList = listCities
            filteredList
        } else {
            filteredList = listCities.filter {
                it.contains(searchString, true)
            }
            filteredList
        }
    }

    fun getListCities(): List<String> =
        this.listCities

    fun find(nameCity: String): Boolean =
        listCities.any {
            it == nameCity
        }

    fun addCity(nameCity: String): List<String> {
        listCities = listCities.plus(nameCity)
        filteredList = filteredList.plus(nameCity)
        return filteredList
    }

    fun removeCity(nameCity: String): List<String> {
        listCities = listCities.minus(nameCity)
        filteredList = filteredList.minus(nameCity)
        return filteredList
    }
}
