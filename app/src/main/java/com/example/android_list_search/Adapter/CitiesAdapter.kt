package com.example.android_list_search.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_list_search.Adapter.ViewHolder.CitiesViewHolder
import com.example.android_list_search.R
import kotlin.collections.ArrayList

class CitiesAdapter (
    private var citiesList: ArrayList<String>,
    private val clicked: (String) -> Unit) :
    RecyclerView.Adapter<CitiesViewHolder>() {

    override fun getItemCount(): Int = citiesList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CitiesViewHolder =
        LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_item,
                parent,
                false).run{
            CitiesViewHolder(this, clicked)
        }

    override fun onBindViewHolder(
        holder: CitiesViewHolder,
        position: Int) {
        holder.tvCities.text =  citiesList[position]
    }

    fun updateCitiesList(filterCitiesList: ArrayList<String>) {
        this.citiesList = filterCitiesList
        notifyDataSetChanged()
    }

}