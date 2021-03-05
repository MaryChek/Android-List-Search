package com.example.favorite_cities.model

import android.graphics.drawable.Drawable

data class City(
    var name: String = "",
    var favorite: Boolean = false
)

data class CityAttributes(
    var name: String = "",
    var drawable: Drawable? = null
)