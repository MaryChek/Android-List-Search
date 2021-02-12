package com.example.favorite_cities

import android.app.Application
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.model.sharedpreferences.PreferenceManager

class App : Application() {
    private lateinit var app: Application
    lateinit var model: CitiesModel
        private set

    override fun onCreate() {
        super.onCreate()
        app = this
        val list = this.resources.getStringArray(R.array.cities).toList()
        model = CitiesModel(list, PreferenceManager(this))
    }

    fun upDateListInModel() {
        val list = app.resources.getStringArray(R.array.cities).toList()
        model = CitiesModel(list, PreferenceManager(app))
    }
}