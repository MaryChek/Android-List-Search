package com.example.favorite_cities.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite_cities.adapter.viewholder.CitiesViewHolder
import com.example.favorite_cities.R
import com.example.favorite_cities.model.CityAttributes

class CitiesAdapter(
    private var citiesList: List<CityAttributes>,
    private val cityIconClickListener: (String) -> Unit
) : RecyclerView.Adapter<CitiesViewHolder>() {

    override fun getItemCount(): Int =
        citiesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder =
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_city, parent, false
        ).run {
            CitiesViewHolder(this, cityIconClickListener)
        }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val item: String = citiesList[position].name
        bindViewHolder(holder, item, citiesList[position].drawable)
    }

    private fun bindViewHolder(holder: CitiesViewHolder, item: String, iconDrawable: Drawable?) {
        with(holder) {
            binding.tvCities.text = item
            holder.item = item
            iconDrawable?.let {
                holder.ibCityStar.buttonDrawable = it
            }
        }
    }

    fun updateList(filterCitiesList: List<CityAttributes>) {
        citiesList = filterCitiesList
        notifyDataSetChanged()
    }
}