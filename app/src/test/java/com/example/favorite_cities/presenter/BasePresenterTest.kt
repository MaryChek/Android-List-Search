package com.example.favorite_cities.presenter

import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CitiesContract
import com.example.favorite_cities.model.CitiesModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

class BasePresenterTest {
    private var view: CitiesContract.View = mock()
    private var model: CitiesModel = mock()
    private val dialogCreator: DialogCreator = mock()
    private val presenter: BaseCitiesPresenter<CitiesContract.View> =
        BaseCitiesPresenter(view, model, dialogCreator)

//    @Test
//    fun `onViewCreated when view created then verify setFunctionOnPositive invoked`() {
//        presenter.onViewCreated()
//
//        verify(dialogCreator, times(1))
//            .setFunctionOnPositive(presenter::afterPositiveClickInDialog)
//    }

    @Test
    fun `onViewCreated when view created then verify setMessageBeforeAdding invoked`() {
        presenter.onViewCreated()

        verify(dialogCreator, times(1))
            .setMessageBeforeAdding(R.string.message_favorite_city)
    }

    @Test
    fun `onViewCreated when view created then verify setButtonRemoveTitle invoked`() {
        presenter.onViewCreated()

        verify(dialogCreator, times(1))
            .setButtonRemoveTitle(R.string.text_button_remove)
    }

    @Test
    fun `onViewCreated when view created then verify setMessageBeforeRemoving invoked`() {
        presenter.onViewCreated()

        verify(dialogCreator, times(1))
            .setMessageBeforeRemoving(R.string.message_unelected_city)
    }

    @Test
    fun `onViewCreated when view created then verify setNegativeButtonTitle invoked`() {
        presenter.onViewCreated()

        verify(dialogCreator, times(1))
            .setNegativeButtonTitle(R.string.button_cancel)
    }

    @Test
    fun `onViewCreated when view created then verify setButtonAddTitle invoked`() {
        presenter.onViewCreated()

        verify(dialogCreator, times(1))
            .setButtonAddTitle(R.string.text_button_add)
    }

    @Test
    fun `onDestroy when view destroy then verify dialogCreator onDestroy invoked`() {
        presenter.onDestroy()

        verify(dialogCreator, times(1))
            .onDestroy()
    }

    @Test
    fun `onCityClicked when on city clicked then verify setTitle invoked with this city`() {
        presenter.onCityClicked(SIMPLE_CITY)

        verify(dialogCreator, times(1))
            .setTitle(SIMPLE_CITY)
    }

    @Test
    fun `onCityClicked when on city clicked then verify findInFavorites invoked with this city`() {
        presenter.onCityClicked(SIMPLE_CITY)

        verify(model, times(1))
            .findInFavorites(SIMPLE_CITY)
    }

    @Test
    fun `onCityClicked when city found in favorites, then verify showDialogRemoving invoked`() {
        whenever(model.findInFavorites(FAVORITE_CITY))
            .thenReturn(true)

        presenter.onCityClicked(FAVORITE_CITY)

        verify(view, times(1))
            .showDialogRemoving()
    }

    @Test
    fun `onCityClicked when city not found in favorites, then verify showDialogRemoving invoked`() {
        whenever(model.findInFavorites(SIMPLE_CITY))
            .thenReturn(false)

        presenter.onCityClicked(SIMPLE_CITY)

        verify(view, times(1))
            .showDialogAdding()
    }

    @Test
    fun `addFavoriteCity when add favorite city then verify addFavoriteCity invoked`() {
        presenter.addFavoriteCity(FAVORITE_CITY)

        verify(model, times(1))
            .addFavoriteCity(FAVORITE_CITY)
    }

    @Test
    fun `removeFavoriteCity when remove favorite city then verify removeFavoriteCity invoked`() {
        presenter.removeFavoriteCity(FAVORITE_CITY)

        verify(model, times(1))
            .removeFavoriteCity(FAVORITE_CITY)
    }

    @Test
    fun `afterPositiveClickInDialog when clicked add button then verify addFavoriteCity invoked`() {
        presenter.afterPositiveClickInDialog(R.string.text_button_add, FAVORITE_CITY)

        verify(model, times(1))
            .addFavoriteCity(FAVORITE_CITY)
    }

    @Test
    fun `afterPositiveClickInDialog when clicked add button then verify showToastWithText invoked with message after adding `() {
        presenter.afterPositiveClickInDialog(R.string.text_button_add, FAVORITE_CITY)

        verify(view, times(1))
            .showToastWithText(R.string.message_after_adding, FAVORITE_CITY)
    }

    @Test
    fun `afterPositiveClickInDialog when clicked remove button then verify removeFavoriteCity invoked`() {
        presenter.afterPositiveClickInDialog(R.string.text_button_remove, FAVORITE_CITY)

        verify(model, times(1))
            .removeFavoriteCity(FAVORITE_CITY)
    }

    @Test
    fun `afterPositiveClickInDialog when clicked remove button then verify showToastWithText invoked with message after removal `() {
        presenter.afterPositiveClickInDialog(R.string.text_button_remove, FAVORITE_CITY)

        verify(view, times(1))
            .showToastWithText(R.string.message_after_removal, FAVORITE_CITY)
    }

    companion object {
        private const val FAVORITE_CITY = "Orel"
        private const val SIMPLE_CITY = "Moscow"
    }
}
