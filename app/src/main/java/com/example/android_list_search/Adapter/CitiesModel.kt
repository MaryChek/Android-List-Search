package com.example.android_list_search.Adapter

import java.util.*
import kotlin.collections.ArrayList

class CitiesModel(private val listCities: ArrayList<String>) {
    var filterList = ArrayList<String>()

    init {
        filterList = listCities
    }

    fun filter(constraint: CharSequence?) : ArrayList<String> {
        val charSearch = constraint.toString()
        when (charSearch.isEmpty()) {
            true -> filterList = listCities
            false -> filterList = listCities.filter {
                charSearch.toLowerCase(Locale.ROOT) in it.toLowerCase(Locale.ROOT)
            } as ArrayList<String>
        }
        return filterList
    }

}