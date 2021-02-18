package com.example.favorite_cities.model.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context?) {

    companion object {
        private const val STR_NAME_PREFERENCE = "SharedPreference"
    }

    private var preferences: SharedPreferences? = null

    init {
        preferences = context?.getSharedPreferences(STR_NAME_PREFERENCE, Context.MODE_PRIVATE)
    }

    fun setList(key: String, hashSet: HashSet<String>) {
        val editor: SharedPreferences.Editor? = preferences?.edit()
        editor?.putStringSet(key, hashSet)
        editor?.apply()
    }

    fun getList(key: String): Set<String> =
        preferences?.getStringSet(key, setOf()) ?: setOf()
}
