package com.example.favorite_cities

import android.app.Application
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.model.sharedpreferences.PreferenceManager

class App : Application() {
    lateinit var model: CitiesModel
        private set

    override fun onCreate() {
        super.onCreate()
        val list: List<String> = resources.getStringArray(R.array.cities).toList()
        model = CitiesModel(list, PreferenceManager(this))
    }

    fun updateListInModel() {
        val newList: List<String> = resources.getStringArray(R.array.cities).toList()
        model.updateCitiesLists(newList)
    }
}