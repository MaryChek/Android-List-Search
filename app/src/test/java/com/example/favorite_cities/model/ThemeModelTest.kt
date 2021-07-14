package com.example.favorite_cities.model

import com.example.favorite_cities.model.sharedpreferences.PreferenceManager
import com.example.favorite_cities.model.theme.Theme
import com.example.favorite_cities.model.theme.ThemeModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert
import org.junit.Test

class ThemeModelTest {
    private val preferenceManager: PreferenceManager = mock()

    private fun initModel(stabValueForPreference: Int): ThemeModel {
        whenever(preferenceManager.getIntByKey(KEY_FOR_THEME))
            .thenReturn(stabValueForPreference)
        return ThemeModel(preferenceManager)
    }

    @Test
    fun `init when model init then verify getIntByKey invoked`() {
        initModel(any())

        verify(preferenceManager, times(1))
            .getIntByKey(KEY_FOR_THEME)
    }

    @Test
    fun `getTheme when preferenceManager getIntByKey returned index of dark theme then assert theme`() {
        val model = initModel(INDEX_DARK_THEME)

        val actualTheme = model.theme

        Assert.assertEquals(Theme.DARK, actualTheme)
    }

    @Test
    fun `getTheme when preferenceManager getIntByKey returned index of light theme then assert theme`() {
        val model = initModel(INDEX_LIGHT_THEME)

        val actualTheme = model.theme

        Assert.assertEquals(Theme.LIGHT, actualTheme)
    }

    @Test
    fun `saveTheme when theme is light invoked then verify putIntByKey with index light theme invoked`() {
        val model = initModel(INDEX_LIGHT_THEME)

        model.saveTheme()

        verify(preferenceManager, times(1))
            .putIntByKey(KEY_FOR_THEME, INDEX_LIGHT_THEME)
    }

    @Test
    fun `saveTheme when theme is dark invoked then verify putIntByKey with index dark theme invoked`() {
        val model = initModel(INDEX_DARK_THEME)

        model.saveTheme()

        verify(preferenceManager, times(1))
            .putIntByKey(KEY_FOR_THEME, INDEX_DARK_THEME)
    }

    companion object {
        private const val INDEX_LIGHT_THEME = 0
        private const val INDEX_DARK_THEME = 1
        private const val KEY_FOR_THEME = "Theme"
    }
}