package com.example.favorite_cities.presenter

import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CitiesContract
import com.example.favorite_cities.model.cities.CitiesModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

class BasePresenterTest {
    private var view: CitiesContract.View = mock()
    private var model: CitiesModel = mock()
    private val presenter: BaseCitiesPresenter<CitiesContract.View> =
        BaseCitiesPresenter(view, model)

    @Test
    fun `onCityIconClicked when city found in favorites, then verify showRemoveFromFavoriteActionConfirmation invoked`() {
        whenever(model.findInFavorites(SIMPLE_CITY))
            .thenReturn(true)

        presenter.onCityIconClicked(SIMPLE_CITY)

        verify(view, times(1))
            .showRemoveFromFavoriteActionConfirmation(SIMPLE_CITY)
    }

    @Test
    fun `onCityIconClicked when city not found in favorites, then verify showAddToFavoriteActionConfirmation invoked`() {
        whenever(model.findInFavorites(SIMPLE_CITY))
            .thenReturn(false)

        presenter.onCityIconClicked(SIMPLE_CITY)

        verify(view, times(1))
            .showAddToFavoriteActionConfirmation(SIMPLE_CITY)
    }

    @Test
    fun `onAddToFavoriteClick when click to add favorite city then verify addFavoriteCity invoked`() {
        presenter.onAddToFavoriteClick(SIMPLE_CITY)

        verify(model, times(1))
            .addFavoriteCity(SIMPLE_CITY)
    }

    @Test
    fun `onAddToFavoriteClick when click to add favorite city then verify showUserMessage invoked`() {
        presenter.onAddToFavoriteClick(SIMPLE_CITY)

        verify(view, times(1))
            .showUserMessage(R.string.message_after_adding, SIMPLE_CITY)
    }

    @Test
    fun `onRemoveFromFavoriteClick when click to remove favorite city then verify removeFavoriteCity invoked`() {
        presenter.onRemoveFromFavoriteClick(SIMPLE_CITY)

        verify(model, times(1))
            .removeFavoriteCity(SIMPLE_CITY)
    }

    @Test
    fun `onRemoveFromFavoriteClick when click to remove favorite city then verify showUserMessage invoked`() {
        presenter.onRemoveFromFavoriteClick(SIMPLE_CITY)

        verify(view, times(1))
            .showUserMessage(R.string.message_after_removal, SIMPLE_CITY)
    }

    @Test
    fun `onViewPause when view pause then verify saveCitiesLists invoked`() {
        presenter.onViewPause()

        verify(model, times(1))
            .saveFavoriteList()
    }

    companion object {
        private const val SIMPLE_CITY = "Moscow"
    }
}