package com.example.favorite_cities.model

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

data class CityIcon(
    var name: String,
    @DrawableRes var iconId: Int
)

data class CityAttributes(
    var name: String = "",
    var drawable: Drawable? = null
)