package com.example.favorite_cities.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.favorite_cities.view.CityAttributes

class CitiesItemDiff : DiffUtil.ItemCallback<CityAttributes>() {
    override fun areContentsTheSame(oldItem: CityAttributes, newItem: CityAttributes): Boolean =
        oldItem.drawable?.constantState?.equals(newItem.drawable?.constantState) ?: false

    override fun areItemsTheSame(oldItem: CityAttributes, newItem: CityAttributes): Boolean =
        oldItem.name == newItem.name
}