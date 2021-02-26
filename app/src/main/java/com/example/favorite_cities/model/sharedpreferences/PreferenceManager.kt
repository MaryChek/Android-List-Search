package com.example.favorite_cities.model.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

open class PreferenceManager(context: Context?) {

    companion object {
        private const val STR_NAME_PREFERENCE = "SharedPreference"
    }

    private var preferences: SharedPreferences? = null

    init {
        preferences = context?.getSharedPreferences(STR_NAME_PREFERENCE, Context.MODE_PRIVATE)
    }

    open fun setList(key: String, set: HashSet<String>) {
        val editor: SharedPreferences.Editor? = preferences?.edit()
        editor?.putStringSet(key, set)
        editor?.apply()
    }

    open fun getList(key: String): Set<String> =
        preferences?.getStringSet(key, setOf()) ?: setOf()
}
