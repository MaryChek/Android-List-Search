package com.example.favorite_cities.model

import android.app.Application
import android.content.Context
import com.example.favorite_cities.R
import com.example.favorite_cities.model.sharedpreferences.PreferenceManager

class Model : Application() {

    companion object {
        lateinit var model: CitiesModel
        fun onCreate(context: Context) {
            val res = context.resources.getStringArray(R.array.cities).toList()
            model =
                CitiesModel(
                    res,
                    PreferenceManager(context)
                )
        }
    }
}