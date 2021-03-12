package com.example.favorite_cities

import android.app.Application
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.model.ThemeModel
import com.example.favorite_cities.model.sharedpreferences.PreferenceManager

class App : Application() {
    lateinit var citiesModel: CitiesModel
        private set

    lateinit var themeModel: ThemeModel
        private set

    override fun onCreate() {
        super.onCreate()
        citiesModel = CitiesModel(this)
        themeModel = ThemeModel(PreferenceManager(this))
    }
}