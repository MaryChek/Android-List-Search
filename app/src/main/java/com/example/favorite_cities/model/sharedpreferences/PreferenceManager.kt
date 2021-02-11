package com.example.favorite_cities.model.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context?) {

    private var preferences: SharedPreferences? = null

    init {
        preferences = context?.getSharedPreferences("SharedPreference", Context.MODE_PRIVATE)
    }

    fun setString(key: String, string: String?) {
        val editor = preferences?.edit()
        editor?.putString(key, string)
        editor?.apply()
    }

    fun setList(key: String, hashSet: HashSet<String>) {
        val editor = preferences?.edit()
        editor?.putStringSet(key, hashSet)
        editor?.apply()
    }

    fun getString(key: String): String? =
        preferences?.getString(key, null)

    fun getList(key: String): Set<String> =
        preferences?.getStringSet(key, setOf()) ?: setOf()

    fun setBoolean(key: String, boolean: Boolean) {
        val editor = preferences?.edit()
        editor?.putBoolean(key, boolean)
        editor?.apply()
    }

    fun getBoolean(key: String): Boolean =
        preferences?.getBoolean(key, false) ?: false
}
