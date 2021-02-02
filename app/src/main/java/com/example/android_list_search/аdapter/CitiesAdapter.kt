package com.example.android_list_search.аdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_list_search.аdapter.viewholder.CitiesViewHolder
import com.example.android_list_search.R

class CitiesAdapter(private var citiesList: List<String>, private val clicked: (String) -> Unit) :
    RecyclerView.Adapter<CitiesViewHolder>() {

    override fun getItemCount(): Int = citiesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder =
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_city, parent, false
        ).run {
            CitiesViewHolder(this, clicked)
        }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.tvCities.text = citiesList[position]
    }

    fun updateCitiesList(filterCitiesList: List<String>) {
        citiesList = filterCitiesList
        notifyDataSetChanged()
    }

}