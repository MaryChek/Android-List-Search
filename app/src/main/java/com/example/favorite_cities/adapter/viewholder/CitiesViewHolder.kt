package com.example.favorite_cities.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite_cities.R

class CitiesViewHolder(
    view: View, cityClickListener: (String) -> Unit
) : RecyclerView.ViewHolder(view) {
    val tvCities: TextView = view.findViewById(R.id.tvCities)

    var item: String? = null


    init {
        tvCities.setOnClickListener {
            item?.let { city ->
                cityClickListener(city)
            }
        }
    }
}
