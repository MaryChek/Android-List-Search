package com.example.favorite_cities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.favorite_cities.R
import com.example.favorite_cities.adapter.viewholder.CitiesViewHolder
import com.example.favorite_cities.model.CityAttributes

class CitiesListAdapter(
    private val cityIconClickListener: (String) -> Unit
) : ListAdapter<CityAttributes, CitiesViewHolder>(CitiesDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder =
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_city, parent, false
        ).run {
            CitiesViewHolder(this)
        }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.bind(getItem(position), cityIconClickListener)
    }
}