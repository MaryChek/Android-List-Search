package com.example.favorite_cities

import android.widget.SearchView

abstract class SimpleSearchTextListener : SearchView.OnQueryTextListener {

    override fun onQueryTextChange(enteredText: String?): Boolean =
        false

    override fun onQueryTextSubmit(query: String?): Boolean =
        true
}