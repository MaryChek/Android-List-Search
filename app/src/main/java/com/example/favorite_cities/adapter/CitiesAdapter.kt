package com.example.favorite_cities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite_cities.adapter.viewholder.CitiesViewHolder
import com.example.favorite_cities.R

class CitiesAdapter(
    private var citiesList: List<String>,
    private val cityClickListener: (String) -> Unit
) : RecyclerView.Adapter<CitiesViewHolder>() {

    override fun getItemCount(): Int =
        citiesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder =
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_city, parent, false
        ).run {
            CitiesViewHolder(this, cityClickListener)
        }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val item: String = citiesList[position]
        bindViewHolder(holder, item)
    }

    private fun bindViewHolder(holder: CitiesViewHolder, item: String) {
        holder.tvCities.text = item
        holder.item = item
    }

    fun updateList(filterCitiesList: List<String>) {
        citiesList = filterCitiesList
        notifyDataSetChanged()
    }
}