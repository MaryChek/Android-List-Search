package com.example.favorite_cities.presenter

import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.GeneralCitiesContract
import com.example.favorite_cities.model.CitiesModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Test
import org.mockito.ArgumentMatchers

class GeneralPresenterTest {

    private var view: GeneralCitiesContract.View = mock()
    private var model: CitiesModel = mock()
    private val dialogCreator: DialogCreator = mock()
    private var presenter: GeneralPresenter = GeneralPresenter(view, model, dialogCreator)

    private fun stubWhenGeneralCitiesFilteredIsEmpty(value: Boolean) {
        whenever(model.isGeneralCitiesFilteredEmpty())
            .thenReturn(value)
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
    fun `onViewCreated when view created then verify getGeneralEnteredText invoked`() {
        presenter.onViewCreated()

        verify(model, times(1))
            .getGeneralEnteredText()
    }

    @Test
    fun `onViewCreated when getGeneralEnteredText returned not null then verify setEnteredText invoked`() {
        whenever(model.getGeneralEnteredText())
            .thenReturn(SIMPLE_ENTERED_TEXT)

        presenter.onViewCreated()

        verify(view, times(1))
            .setEnteredText(SIMPLE_ENTERED_TEXT)
    }

    @Test
    fun `onViewCreated when getGeneralEnteredText returned null then verify setEnteredText not invoked`() {
        whenever(model.getGeneralEnteredText())
            .thenReturn(null)

        presenter.onViewCreated()

        verify(view, times(0))
            .setEnteredText(any())
    }

    @Test
    fun `onViewCreated when view created then verify showCitiesList invoked`() {
        presenter.onViewCreated()

        verify(view, times(1))
            .showCitiesList(ArgumentMatchers.anyList())
    }

    @Test
    fun `onViewCreated when view created then verify getGeneralCitiesFiltered invoked`() {
        presenter.onViewCreated()

        verify(model, times(1))
            .getGeneralCitiesFiltered()
    }

    @Test
    fun `onFragmentVisible when fragment visible then verify setPositionOfTheCurrentFragment with position general fragment invoked`() {
        presenter.onFragmentVisible()

        verify(model, times(1))
            .setPositionOfTheCurrentFragment(POSITION_OF_FRAGMENT)
    }

    @Test
    fun `searchTextChanged when search text changed to some text then verify filterGeneralList invoked with this`() {
        presenter.searchTextChanged(SIMPLE_ENTERED_TEXT)

        verify(model, times(1))
            .filterGeneralList(SIMPLE_ENTERED_TEXT)
    }

    @Test
    fun `searchTextChanged when general cities filtered is empty then verify showSearchError with text "nothing found" invoked`() {
        stubWhenGeneralCitiesFilteredIsEmpty(true)

        presenter.searchTextChanged(any())

        verifyShowSearchErrorWithNothingFound()
    }

    @Test
    fun `searchTextChanged when general cities filtered isn't empty then verify hideSearchError invoked`() {
        stubWhenGeneralCitiesFilteredIsEmpty(false)

        presenter.searchTextChanged(any())

        verifyHideSearchError()
    }

    @Test
    fun `searchTextChanged when search text changed to some text then verify updateCitiesList invoked`() {
        presenter.searchTextChanged(any())

        verify(view, times(1))
            .updateCitiesList(any())
    }

    @Test
    fun `searchTextChanged when search text changed to some text then verify getGeneralCitiesFiltered invoked`() {
        presenter.searchTextChanged(any())

        verify(model, times(1))
            .getGeneralCitiesFiltered()
    }

    companion object {
        private const val SIMPLE_ENTERED_TEXT = "o"
        private const val POSITION_OF_FRAGMENT = 0
    }
}