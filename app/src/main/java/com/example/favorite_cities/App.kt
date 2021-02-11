package com.example.favorite_cities

import android.app.Application
import android.content.Context
import com.example.favorite_cities.sharedpreferences.PreferenceManager

class App : Application() {
    lateinit var model: CitiesModel
        private set

    override fun onCreate() {
        super.onCreate()
        val list = this.resources.getStringArray(R.array.cities).toList()
        model = CitiesModel(list, PreferenceManager(this))
    }
}