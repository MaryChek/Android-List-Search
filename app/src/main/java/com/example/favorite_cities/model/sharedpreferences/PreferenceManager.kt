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

    fun setString(key: String, string: String?) {
        val editor: SharedPreferences.Editor? = preferences?.edit()
        editor?.putString(key, string)
        editor?.apply()
    }

    fun setList(key: String, hashSet: HashSet<String>) {
        val editor: SharedPreferences.Editor? = preferences?.edit()
        editor?.putStringSet(key, hashSet)
        editor?.apply()
    }

    fun getString(key: String): String? =
        preferences?.getString(key, null)

    fun getList(key: String): Set<String> =
        preferences?.getStringSet(key, setOf()) ?: setOf()

    fun setBoolean(key: String, boolean: Boolean) {
        val editor: SharedPreferences.Editor? = preferences?.edit()
        editor?.putBoolean(key, boolean)
        editor?.apply()
    }

    fun getBoolean(key: String): Boolean =
        preferences?.getBoolean(key, false) ?: false
}
