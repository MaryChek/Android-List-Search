package com.example.favorite_cities.model.cities

import androidx.annotation.DrawableRes

data class CityIcon(
    var name: String,
    @DrawableRes var iconId: Int
)