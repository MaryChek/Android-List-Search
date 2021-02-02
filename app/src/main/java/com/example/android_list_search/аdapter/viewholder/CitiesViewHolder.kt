package com.example.android_list_search.аdapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_list_search.R

class CitiesViewHolder(
    view: View,
    citiesClickListener: (String) -> Unit
) : RecyclerView.ViewHolder(view) {
    val tvCities: TextView = view.findViewById(R.id.textViewLarge)

    init {
        tvCities.setOnClickListener {
            tvCities.text?.run {
                citiesClickListener(this.toString())
            }
        }
    }
}
