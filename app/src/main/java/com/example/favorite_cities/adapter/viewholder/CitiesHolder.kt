package com.example.favorite_cities.adapter.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite_cities.databinding.ListItemCityBinding
import com.example.favorite_cities.model.CityAttributes

class CitiesHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding: ListItemCityBinding = ListItemCityBinding.bind(view)
    private val ibCityStar: AppCompatCheckBox = binding.ibCityStar
//    private val item: String? = null

    fun bind(data: CityAttributes, cityIconClickListener: (String) -> Unit) {
        binding.tvCities.text = data.name
        ibCityStar.buttonDrawable = data.drawable
        ibCityStar.setOnClickListener {
            binding.tvCities.text?.let { city ->
                cityIconClickListener(city.toString())
            }
        }
    }
}