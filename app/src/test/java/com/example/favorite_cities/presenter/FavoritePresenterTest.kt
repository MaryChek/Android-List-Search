package com.example.favorite_cities.presenter

import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.FavoriteCitiesContract
import com.example.favorite_cities.model.CitiesModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Test
import org.mockito.ArgumentMatchers

open class FavoritePresenterTest {

    private var view: FavoriteCitiesContract.View = mock()
    private var model: CitiesModel = mock()
    private val dialogCreator: DialogCreator = mock()
    private var presenter: FavoritePresenter = FavoritePresenter(view, model, dialogCreator)

    private fun stubWhenFavoriteCitiesIsEmpty(value: Boolean) {
        whenever(model.isFavoriteCitiesEmpty())
            .thenReturn(value)
    }

    private fun stubWhenFavoriteCitiesFilteredIsEmpty(value: Boolean) {
        whenever(model.isFavoriteCitiesFilteredEmpty())
            .thenReturn(value)
    }

    private fun verifyShowSearchErrorWithNoFavoriteCities() {
        verify(view, times(1))
            .showSearchError(R.string.no_favorite_cities)
    }

    private fun verifyShowSearchErrorWithNothingFound() {
        verify(view, times(1))
            .showSearchError(R.string.nothing_found)
    }

    private fun verifyHideSearchError() {
        verify(view, times(1))
            .hideSearchError()
    }

    @Test
    fun `onViewCreated when view created then verify getFavoriteEnteredText invoked`() {
        presenter.onViewCreated()

        verify(model, times(1))
            .favoriteEnteredText
    }

    @Test
    fun `onViewCreated when getFavoriteEnteredText returned not null then verify setEnteredText invoked`() {
        whenever(model.favoriteEnteredText)
            .thenReturn(SIMPLE_ENTERED_TEXT)

        presenter.onViewCreated()

        verify(view, times(1))
            .setEnteredText(SIMPLE_ENTERED_TEXT)
    }

    @Test
    fun `onViewCreated when getFavoriteEnteredText returned null then verify setEnteredText not invoked`() {
        whenever(model.favoriteEnteredText)
            .thenReturn(null)

        presenter.onViewCreated()

        verify(view, times(0))
            .setEnteredText(any())
    }

    @Test
    fun `onViewCreated when favorite cities is empty then verify showSearchError with text "no favorite cities" invoked`() {
        stubWhenFavoriteCitiesIsEmpty(true)

        presenter.onViewCreated()

        verifyShowSearchErrorWithNoFavoriteCities()
    }

    @Test
    fun `onViewCreated when favorite cities isn't empty but filtered is empty then verify showSearchError with text "nothing found" invoked`() {
        stubWhenFavoriteCitiesIsEmpty(false)
        stubWhenFavoriteCitiesFilteredIsEmpty(true)

        presenter.onViewCreated()

        verifyShowSearchErrorWithNothingFound()
    }

    @Test
    fun `onViewCreated when favorite cities and filtered aren't empty then verify hideSearchError invoked`() {
        stubWhenFavoriteCitiesIsEmpty(false)
        stubWhenFavoriteCitiesFilteredIsEmpty(false)

        presenter.onViewCreated()

        verifyHideSearchError()
    }

    @Test
    fun `onViewCreated when view created then verify updateCitiesList invoked`() {
        presenter.onViewCreated()

        verify(view, times(1))
            .updateCitiesList(ArgumentMatchers.anyList())
    }

    @Test
    fun `onViewCreated when view created then verify getFavoriteCitiesFiltered invoked`() {
        presenter.onViewCreated()

        verify(model, times(1))
            .getFavoriteCitiesFiltered()
    }

    @Test
    fun `onTabVisible when tab is visible then verify positionOfTheCurrentFragment with position favorite fragment invoked`() {
        presenter.onTabVisible()

        verify(model, times(1))
            .positionOfTheCurrentFragment = POSITION_OF_FRAGMENT
    }

    @Test
    fun `onTabVisible when favorite cities is empty then verify showSearchError with text "no favorite cities" invoked`() {
        stubWhenFavoriteCitiesIsEmpty(true)

        presenter.onTabVisible()

        verifyShowSearchErrorWithNoFavoriteCities()
    }

    @Test
    fun `onTabVisible when favorite cities isn't empty but filtered is empty then verify showSearchError with text "nothing found" invoked`() {
        stubWhenFavoriteCitiesIsEmpty(false)
        stubWhenFavoriteCitiesFilteredIsEmpty(true)

        presenter.onTabVisible()

        verifyShowSearchErrorWithNothingFound()
    }

    @Test
    fun `onTabVisible when favorite cities and filtered lists aren't empty then verify hideSearchError invoked`() {
        stubWhenFavoriteCitiesIsEmpty(false)
        stubWhenFavoriteCitiesFilteredIsEmpty(false)

        presenter.onTabVisible()

        verifyHideSearchError()
    }

    @Test
    fun `onTabVisible when tab is visible then verify updateCitiesList invoked`() {
        presenter.onTabVisible()

        verify(view, times(1))
            .updateCitiesList(ArgumentMatchers.anyList())
    }

    @Test
    fun `onTabVisible when fragment visible then verify getFavoriteCitiesFiltered invoked`() {
        presenter.onTabVisible()

        verify(model, times(1))
            .getFavoriteCitiesFiltered()
    }

    @Test
    fun `searchTextChanged when search text changed to some text then verify filterFavoriteList invoked with this`() {
        presenter.onSearchTextChanged(SIMPLE_ENTERED_TEXT)

        verify(model, times(1))
            .filterFavoriteList(SIMPLE_ENTERED_TEXT)
    }

    @Test
    fun `searchTextChanged when favorite cities is empty then verify showSearchError with text "no favorite cities" invoked`() {
        stubWhenFavoriteCitiesIsEmpty(true)

        presenter.onSearchTextChanged(any())

        verifyShowSearchErrorWithNoFavoriteCities()
    }

    @Test
    fun `searchTextChanged when favorite cities isn't empty but filtered is empty then verify showSearchError with text "nothing found" invoked`() {
        stubWhenFavoriteCitiesIsEmpty(false)
        stubWhenFavoriteCitiesFilteredIsEmpty(true)

        presenter.onSearchTextChanged(any())

        verifyShowSearchErrorWithNothingFound()
    }

    @Test
    fun `searchTextChanged when favorite cities and filtered lists aren't empty then verify hideSearchError invoked`() {
        stubWhenFavoriteCitiesIsEmpty(false)
        stubWhenFavoriteCitiesFilteredIsEmpty(false)

        presenter.onSearchTextChanged(any())

        verifyHideSearchError()
    }

    @Test
    fun `searchTextChanged when favorite list is empty, then verify updateCitiesList for zero calls`() {
        whenever(model.isFavoriteCitiesNotEmpty())
            .thenReturn(false)

        presenter.onSearchTextChanged(any())

        verify(view, times(0))
            .updateCitiesList(any())
    }

    @Test
    fun `searchTextChanged when favorite list isn't empty then verify updateCitiesList invoked`() {
        whenever(model.isFavoriteCitiesNotEmpty())
            .thenReturn(true)

        presenter.onSearchTextChanged(any())

        verify(view, times(1))
            .updateCitiesList(ArgumentMatchers.anyList())
    }

    @Test
    fun `searchTextChanged when favorite list isn't empty then verify getFavoriteCitiesFiltered invoked`() {
        whenever(model.isFavoriteCitiesNotEmpty())
            .thenReturn(true)

        presenter.onSearchTextChanged(any())

        verify(model, times(1))
            .getFavoriteCitiesFiltered()
    }

//    @Test
//    fun `removeFavoriteCity when the favorite list became empty, then verify showSearchError with text "no favorite cities"`() {
//        stubWhenFavoriteCitiesIsEmpty(true)
//
//        presenter.removeFavoriteCity(FAVORITE_CITY)
//        verifyShowSearchErrorWithNoFavoriteCities()
//    }
//
//    @Test
//    fun `removeFavoriteCity when the favorite list is still not empty, but filtered became empty then verify showSearchError with text "nothing found"`() {
//        stubWhenFavoriteCitiesIsEmpty(false)
//        stubWhenFavoriteCitiesFilteredIsEmpty(true)
//
//        presenter.removeFavoriteCity(FAVORITE_CITY)
//        verifyShowSearchErrorWithNothingFound()
//    }
//
//    @Test
//    fun `removeFavoriteCity when the favorite and filtered lists are still not empty, then verify showSearchError with text "nothing found"`() {
//        stubWhenFavoriteCitiesIsEmpty(false)
//        stubWhenFavoriteCitiesFilteredIsEmpty(false)
//
//        presenter.removeFavoriteCity(FAVORITE_CITY)
//        verifyHideSearchError()
//    }
//
//    @Test
//    fun `removeFavoriteCity when remove favorite city then verify updateCitiesList invoked`() {
//        presenter.removeFavoriteCity(FAVORITE_CITY)
//
//        verify(view, times(1))
//            .updateCitiesList(ArgumentMatchers.anyList())
//    }
//
//    @Test
//    fun `removeFavoriteCity when remove favorite city then verify getFavoriteCitiesFiltered invoked`() {
//        presenter.removeFavoriteCity(FAVORITE_CITY)
//
//        verify(model, times(1))
//            .getFavoriteCitiesFiltered()
//    }

    companion object {
        private const val SIMPLE_ENTERED_TEXT = "o"
        private const val FAVORITE_CITY = "Orel"
        private const val POSITION_OF_FRAGMENT = 1
    }
}