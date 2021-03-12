package com.example.favorite_cities.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite_cities.databinding.ListItemCityBinding
import com.example.favorite_cities.model.CityAttributes

class CitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding: ListItemCityBinding = ListItemCityBinding.bind(view)

    fun bind(data: CityAttributes, cityIconClickListener: (String) -> Unit) {
        binding.tvCities.text = data.name
        binding.ibCityStar.buttonDrawable = data.drawable

        binding.ibCityStar.setOnClickListener {
            binding.tvCities.text?.let { city ->
                cityIconClickListener(city.toString())
            }
        }
    }
}