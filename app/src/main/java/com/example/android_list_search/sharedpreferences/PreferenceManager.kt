package com.example.android_list_search.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

private const val PREFERENCE_NAME = "SharedPreference"

class PreferenceManager(context: Context?) {
    private var preferences: SharedPreferences? = null

    init {
        preferences = context?.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
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
