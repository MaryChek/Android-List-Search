package com.example.favorite_cities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.favorite_cities.R
import com.example.favorite_cities.adapter.viewholder.CitiesHolder
import com.example.favorite_cities.model.CityAttributes

class CitiesListAdapter(
    private val cityIconClickListener: (String) -> Unit
) : ListAdapter<CityAttributes, CitiesHolder>(CitiesDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesHolder =
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_city, parent, false
        ).run {
            CitiesHolder(this)
        }

    override fun onBindViewHolder(holder: CitiesHolder, position: Int) {
        holder.bind(getItem(position), cityIconClickListener)
    }
}

class CitiesDiff : DiffUtil.ItemCallback<CityAttributes>() {
    override fun areContentsTheSame(oldItem: CityAttributes, newItem: CityAttributes): Boolean =
        oldItem.drawable?.constantState?.equals(newItem.drawable?.constantState) ?: false

    override fun areItemsTheSame(oldItem: CityAttributes, newItem: CityAttributes): Boolean =
        oldItem.name == newItem.name
}