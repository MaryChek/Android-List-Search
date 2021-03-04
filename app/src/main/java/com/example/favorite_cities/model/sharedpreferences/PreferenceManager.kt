package com.example.favorite_cities.model.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(
    context: Context,
    private val preferences: SharedPreferences =
        context.getSharedPreferences(STR_NAME_PREFERENCE, Context.MODE_PRIVATE)
) {

    fun putSetByKey(key: String, set: Set<String>) {
        preferences.edit()
            .putStringSet(key, set)
            .apply()
    }

    fun getSetByKey(key: String): Set<String> =
        preferences.getStringSet(key, setOf()) ?: setOf()

    companion object {
        private const val STR_NAME_PREFERENCE = "SharedPreference"
    }
}
