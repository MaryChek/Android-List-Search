package com.example.favorite_cities.adapter.viewholder

import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite_cities.databinding.ListItemCityBinding

class CitiesViewHolder(
    view: View, cityIconClickListener: (String) -> Unit
) : RecyclerView.ViewHolder(view) {
    val binding: ListItemCityBinding = ListItemCityBinding.bind(view)
    val ibCityStar: AppCompatCheckBox = binding.ibCityStar

    var item: String? = null

    init {
        ibCityStar.setOnClickListener {
            item?.let { city ->
                cityIconClickListener(city)
            }
        }
    }
}
