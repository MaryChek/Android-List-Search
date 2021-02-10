package com.example.favorite_cities.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context?) {
    
    private var preferences: SharedPreferences? = null

    init {
        preferences = context?.getSharedPreferences("SharedPreference", Context.MODE_PRIVATE)
    }

    fun setList(list: List<String>) {
        val hashSet = list.toHashSet()
        val editor = preferences?.edit()
        editor?.putStringSet("CitiesFavorite", hashSet)
        editor?.apply()
    }

    fun getList(): List<String> {
        val hashSet = preferences?.getStringSet("CitiesFavorite", null)
        return hashSet?.toList() ?: listOf()
    }
}
