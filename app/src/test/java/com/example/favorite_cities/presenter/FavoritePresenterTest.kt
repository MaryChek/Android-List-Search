package com.example.favorite_cities.presenter

import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CitiesContract
import com.example.favorite_cities.model.cities.CitiesModel
import com.example.favorite_cities.model.cities.CityIcon
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

open class FavoritePresenterTest {

    private var view: CitiesContract.View = mock()
    private var model: CitiesModel = mock()
    private var presenter: FavoritePresenter = FavoritePresenter(view, model)

    private fun stubWhenFavoriteCitiesIsEmpty(value: Boolean) {
        whenever(model.isFavoriteCitiesEmpty())
            .thenReturn(value)
    }

    private fun stubWhenFavoriteCitiesFilteredIsEmpty(value: Boolean) {
        val list: List<CityIcon> =
            when (value) {
                true -> emptyList()
                false -> SIMPLE_LIST_OF_CITY_ICON
            }

        whenever(model.getFavoriteCitiesIconFiltered())
            .thenReturn(list)
    }

    private fun verifyShowEmptyListHintWithNoFavoriteCities() {
        verify(view, times(1))
            .showEmptyListHint(R.string.no_favorite_cities)
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
    fun `onViewCreated when favoriteEnteredText isn't null then verify setEnteredText invoked`() {
        whenever(model.favoriteEnteredText)
            .thenReturn(SIMPLE_ENTERED_TEXT)

        presenter.onViewCreated()

        verify(view, times(1))
            .setEnteredText(SIMPLE_ENTERED_TEXT)
    }

    @Test
    fun `onViewCreated when favoriteEnteredText is null then verify setEnteredText not invoked`() {
        whenever(model.favoriteEnteredText)
            .thenReturn(null)

        presenter.onViewCreated()

        verify(view, times(0))
            .setEnteredText(any())
    }

    @Test
    fun `onViewCreated when view created then verify updateCitiesList invoked`() {
        whenever(model.getFavoriteCitiesIconFiltered())
            .thenReturn(SIMPLE_LIST_OF_CITY_ICON)

        presenter.onViewCreated()

        verify(view, times(1))
            .updateCitiesList(SIMPLE_LIST_OF_CITY_ICON)
    }

    @Test
    fun `onTabVisible when favorite cities is empty then verify showEmptyListHint with text "no favorite cities" invoked`() {
        stubWhenFavoriteCitiesIsEmpty(true)

        presenter.onTabVisible()

        verifyShowEmptyListHintWithNoFavoriteCities()
    }

    @Test
    fun `onTabVisible when favorite cities isn't empty but filtered is empty then verify showEmptyListHint with text "nothing found" invoked`() {
        stubWhenFavoriteCitiesIsEmpty(false)
        stubWhenFavoriteCitiesFilteredIsEmpty(true)

        presenter.onTabVisible()

        verifyShowEmptyListHintWithNothingFound()
    }

    @Test
    fun `onTabVisible when favorite cities and filtered lists aren't empty then verify hideEmptyListHint invoked`() {
        stubWhenFavoriteCitiesIsEmpty(false)
        stubWhenFavoriteCitiesFilteredIsEmpty(false)

        presenter.onTabVisible()

        verifyHideEmptyListHint()
    }

    @Test
    fun `onTabVisible when tab is visible then verify updateCitiesList invoked`() {
        whenever(model.getFavoriteCitiesIconFiltered())
            .thenReturn(SIMPLE_LIST_OF_CITY_ICON)

        presenter.onTabVisible()

        verify(view, times(1))
            .updateCitiesList(SIMPLE_LIST_OF_CITY_ICON)
    }

    @Test
    fun `onSearchTextChanged when search text is change then verify filterFavoriteList invoked`() {
        presenter.onSearchTextChanged(SIMPLE_ENTERED_TEXT)

        verify(model, times(1))
            .filterFavoriteList(SIMPLE_ENTERED_TEXT)
    }

    @Test
    fun `onSearchTextChanged when favorite cities is empty then verify showEmptyListHint with text "no favorite cities" invoked`() {
        stubWhenFavoriteCitiesIsEmpty(true)

        presenter.onSearchTextChanged(any())

        verifyShowEmptyListHintWithNoFavoriteCities()
    }

    @Test
    fun `onSearchTextChanged when favorite cities isn't empty but filtered is empty then verify showEmptyListHint with text "nothing found" invoked`() {
        stubWhenFavoriteCitiesIsEmpty(false)
        stubWhenFavoriteCitiesFilteredIsEmpty(true)

        presenter.onSearchTextChanged(any())

        verifyShowEmptyListHintWithNothingFound()
    }

    @Test
    fun `onSearchTextChanged when favorite cities and filtered lists aren't empty then verify hideEmptyListHint invoked`() {
        stubWhenFavoriteCitiesIsEmpty(false)
        stubWhenFavoriteCitiesFilteredIsEmpty(false)

        presenter.onSearchTextChanged(any())

        verifyHideEmptyListHint()
    }

    @Test
    fun `onSearchTextChanged when search text is change then verify updateCitiesList invoked`() {
        whenever(model.getFavoriteCitiesIconFiltered())
            .thenReturn(SIMPLE_LIST_OF_CITY_ICON)

        presenter.onSearchTextChanged(any())

        verify(view, times(1))
            .updateCitiesList(SIMPLE_LIST_OF_CITY_ICON)
    }

    @Test
    fun `onRemoveFromFavoriteClick when the favorite list is empty, then verify showEmptyListHint with text "no favorite cities" invoked`() {
        stubWhenFavoriteCitiesIsEmpty(true)

        presenter.onRemoveFromFavoriteClick(FAVORITE_CITY)
        verifyShowEmptyListHintWithNoFavoriteCities()
    }

    @Test
    fun `onRemoveFromFavoriteClick when the favorite list isn't empty, but filtered is empty then verify showEmptyListHint with text "nothing found" invoked`() {
        stubWhenFavoriteCitiesIsEmpty(false)
        stubWhenFavoriteCitiesFilteredIsEmpty(true)

        presenter.onRemoveFromFavoriteClick(FAVORITE_CITY)
        verifyShowEmptyListHintWithNothingFound()
    }

    @Test
    fun `onRemoveFromFavoriteClick when the favorite and filtered lists aren't empty, then verify hideEmptyListHint invoked`() {
        stubWhenFavoriteCitiesIsEmpty(false)
        stubWhenFavoriteCitiesFilteredIsEmpty(false)

        presenter.onRemoveFromFavoriteClick(FAVORITE_CITY)
        verifyHideEmptyListHint()
    }

    @Test
    fun `onRemoveFromFavoriteClick when click to remove favorite city then verify updateCitiesList invoked`() {
        whenever(model.getFavoriteCitiesIconFiltered())
            .thenReturn(SIMPLE_LIST_OF_CITY_ICON)

        presenter.onRemoveFromFavoriteClick(FAVORITE_CITY)

        verify(view, times(1))
            .updateCitiesList(SIMPLE_LIST_OF_CITY_ICON)
    }

    companion object {
        private const val SIMPLE_ENTERED_TEXT = "o"
        private const val FAVORITE_CITY = "Orel"
        private val SIMPLE_LIST_OF_CITY_ICON = listOf(CityIcon("Moscow", 0))
    }
}