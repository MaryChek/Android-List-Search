package com.example.favorite_cities.presenter

import com.example.favorite_cities.contract.ActivityContract
import com.example.favorite_cities.model.theme.Theme
import com.example.favorite_cities.model.theme.ThemeModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

class ActivityPresenterTest {
    private val view: ActivityContract.View = mock()
    private val model: ThemeModel = mock()
    private val presenter: ActivityPresenter =
        ActivityPresenter(model, view)

    @Test
    fun `onViewCreated when theme is light then verify changeThemeToLight invoked`() {
        whenever(model.theme)
            .thenReturn(Theme.LIGHT)

        presenter.onViewCreated()

        verify(view, times(1))
            .changeThemeToLight()
    }

    @Test
    fun `onViewCreated when theme is dark then verify changeThemeToDark invoked`() {
        whenever(model.theme)
            .thenReturn(Theme.DARK)

        presenter.onViewCreated()

        verify(view, times(1))
            .changeThemeToDark()
    }

    @Test
    fun `onActionBarIconClick when theme is light then verify changeThemeToDark invoked`() {
        whenever(model.theme)
            .thenReturn(Theme.LIGHT)

        presenter.onActionBarIconClick()

        verify(view, times(1))
            .changeThemeToDark()
    }

    @Test
    fun `onActionBarIconClick when theme is light then set Theme DARK in model theme`() {
        whenever(model.theme)
            .thenReturn(Theme.LIGHT)

        presenter.onActionBarIconClick()

        verify(model, times(1))
            .theme = Theme.DARK
    }

    @Test
    fun `onActionBarIconClick when theme is dark then verify changeThemeToLight invoked`() {
        whenever(model.theme)
            .thenReturn(Theme.DARK)

        presenter.onActionBarIconClick()

        verify(view, times(1))
            .changeThemeToLight()
    }

    @Test
    fun `onActionBarIconClick when theme is dark then set Theme LIGHT in model theme`() {
        whenever(model.theme)
            .thenReturn(Theme.DARK)

        presenter.onActionBarIconClick()

        verify(model, times(1))
            .theme = Theme.LIGHT
    }

    @Test
    fun `onActionBarIconClick when action bar icon clicked then verify saveTheme invoked`() {
        presenter.onActionBarIconClick()

        verify(model, times(1))
            .saveTheme()
    }
}