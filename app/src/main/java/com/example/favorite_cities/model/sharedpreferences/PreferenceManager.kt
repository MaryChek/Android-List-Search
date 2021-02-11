package com.example.favorite_cities.model.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context?) {
    
    private var preferences: SharedPreferences? = null

    init {
        preferences = context?.getSharedPreferences("SharedPreference", Context.MODE_PRIVATE)
    }

    fun setList(list: List<String>, generalList: List<String>) {
        val hashSet = HashSet<String>()
        list.forEach {
            hashSet.add(generalList.indexOf(it).toString())
        }
        val editor = preferences?.edit()
        editor?.putStringSet("CitiesFavorite", hashSet)
        editor?.apply()
    }

    fun getList(generalList: List<String>): List<String> {
        val hashSet = preferences?.getStringSet("CitiesFavorite", null)
        var list = listOf<String>()
            hashSet?.forEach {
                val index = it.toInt()
                list = list.plus(generalList[index])
            }
        return list
    }
}
