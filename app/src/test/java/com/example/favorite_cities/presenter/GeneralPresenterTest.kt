package com.example.favorite_cities.presenter

import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CitiesContract
import com.example.favorite_cities.model.cities.CitiesModel
import com.example.favorite_cities.model.cities.CityIcon
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

class GeneralPresenterTest {

    private var view: CitiesContract.View = mock()
    private var model: CitiesModel = mock()
    private var presenter: GeneralPresenter = GeneralPresenter(view, model)

    private fun stubWhenGeneralCitiesFilteredIsEmpty(value: Boolean) {
        val list: List<CityIcon> =
            when (value) {
                true -> emptyList()
                false -> SIMPLE_LIST_OF_CITY_ICON
            }

        whenever(model.getGeneralCitiesIconFiltered())
            .thenReturn(list)
    }

    private fun verifyShowEmptyListHintWithNothingFound() {
        verify(view, times(1))
            .showEmptyListHint(R.string.nothing_found)
    }

    private fun verifyHideEmptyListHint() {
        verify(view, times(1))
            .hideEmptyListHint()
    }

    @Test
    fun `onViewCreated when generalEnteredText isn't null then verify setEnteredText invoked`() {
        whenever(model.generalEnteredText)
            .thenReturn(SIMPLE_ENTERED_TEXT)

        presenter.onViewCreated()

        verify(view, times(1))
            .setEnteredText(SIMPLE_ENTERED_TEXT)
    }

    @Test
    fun `onViewCreated when generalEnteredText is null then verify setEnteredText not invoked`() {
        whenever(model.generalEnteredText)
            .thenReturn(null)

        presenter.onViewCreated()

        verify(view, times(0))
            .setEnteredText(any())
    }

    @Test
    fun `onViewCreated when view created then verify updateCitiesList invoked`() {
        whenever(model.getGeneralCitiesIconFiltered())
            .thenReturn(SIMPLE_LIST_OF_CITY_ICON)

        presenter.onViewCreated()

        verify(view, times(1))
            .updateCitiesList(SIMPLE_LIST_OF_CITY_ICON)
    }

    @Test
    fun `onTabVisible when tab is visible then verify updateCitiesList invoked`() {
        whenever(model.getGeneralCitiesIconFiltered())
            .thenReturn(SIMPLE_LIST_OF_CITY_ICON)

        presenter.onTabVisible()

        verify(view, times(1))
            .updateCitiesList(SIMPLE_LIST_OF_CITY_ICON)
    }

    @Test
    fun `onSearchTextChanged when search text is change then verify filterGeneralList invoked`() {
        presenter.onSearchTextChanged(SIMPLE_ENTERED_TEXT)

        verify(model, times(1))
            .filterGeneralList(SIMPLE_ENTERED_TEXT)
    }

    @Test
    fun `onSearchTextChanged when general filtered list is empty then verify showEmptyListHint with text "nothing found" invoked`() {
        stubWhenGeneralCitiesFilteredIsEmpty(true)

        presenter.onSearchTextChanged(any())

        verifyShowEmptyListHintWithNothingFound()
    }

    @Test
    fun `onSearchTextChanged when general filtered list isn't empty then verify hideEmptyListHint invoked`() {
        stubWhenGeneralCitiesFilteredIsEmpty(false)

        presenter.onSearchTextChanged(any())

        verifyHideEmptyListHint()
    }

    @Test
    fun `onSearchTextChanged when search text is change then verify updateCitiesList invoked`() {
        whenever(model.getGeneralCitiesIconFiltered())
            .thenReturn(SIMPLE_LIST_OF_CITY_ICON)

        presenter.onSearchTextChanged(any())

        verify(view, times(1))
            .updateCitiesList(SIMPLE_LIST_OF_CITY_ICON)
    }

    @Test
    fun `onAddToFavoriteClick when click to add favorite city then verify updateCitiesList invoked`() {
        whenever(model.getGeneralCitiesIconFiltered())
            .thenReturn(SIMPLE_LIST_OF_CITY_ICON)

        presenter.onAddToFavoriteClick(SIMPLE_CITY)

        verify(view, times(1))
            .updateCitiesList(SIMPLE_LIST_OF_CITY_ICON)
    }

    @Test
    fun `onRemoveFromFavoriteClick when click to remove favorite city then verify updateCitiesList invoked`() {
        whenever(model.getGeneralCitiesIconFiltered())
            .thenReturn(SIMPLE_LIST_OF_CITY_ICON)

        presenter.onRemoveFromFavoriteClick(SIMPLE_CITY)

        verify(view, times(1))
            .updateCitiesList(SIMPLE_LIST_OF_CITY_ICON)
    }

    companion object {
        private val SIMPLE_LIST_OF_CITY_ICON = listOf(CityIcon("Moscow", 0))
        private const val SIMPLE_ENTERED_TEXT = "o"
        private const val SIMPLE_CITY = "Orel"
    }
}