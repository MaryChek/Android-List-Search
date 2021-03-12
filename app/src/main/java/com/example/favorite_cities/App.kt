package com.example.favorite_cities

import android.app.Application
import com.example.favorite_cities.model.cities.CitiesModel
import com.example.favorite_cities.model.sharedpreferences.PreferenceManager
import com.example.favorite_cities.model.theme.ThemeModel

class App : Application() {
    lateinit var citiesModel: CitiesModel
        private set

    lateinit var themeModel: ThemeModel
        private set

    override fun onCreate() {
        super.onCreate()
        citiesModel = CitiesModel(this)
        themeModel = ThemeModel(
            PreferenceManager(this)
        )
    }
}