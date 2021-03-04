package com.example.favorite_cities

import android.app.Application
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.model.sharedpreferences.PreferenceManager

class App : Application() {
    lateinit var model: CitiesModel
        private set

    override fun onCreate() {
        super.onCreate()
        model = CitiesModel(this)
    }
}