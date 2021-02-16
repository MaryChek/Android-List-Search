package com.example.favorite_cities

import android.widget.SearchView

abstract class CustomCitiesSearchView : SearchView.OnQueryTextListener {

    override fun onQueryTextChange(enteredText: String?): Boolean {
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }
}