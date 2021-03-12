package com.example.favorite_cities.model

import com.example.favorite_cities.model.sharedpreferences.PreferenceManager

class ThemeModel(
    private val preferenceManager: PreferenceManager
) {
    var theme: Theme = getSavedTheme()

    private fun getSavedTheme(): Theme =
        when (preferenceManager.getIntByKey(KEY_FOR_THEME)) {
            INDEX_DARK_THEME -> Theme.DARK
            else -> Theme.LIGHT
        }

    fun saveTheme() =
        when (theme) {
            Theme.LIGHT -> preferenceManager.putIntByKey(KEY_FOR_THEME, INDEX_LIGHT_THEME)
            Theme.DARK -> preferenceManager.putIntByKey(KEY_FOR_THEME, INDEX_DARK_THEME)
        }

    companion object {
        private const val INDEX_LIGHT_THEME = 0
        private const val INDEX_DARK_THEME = 1
        private const val KEY_FOR_THEME = "Theme"
    }
}