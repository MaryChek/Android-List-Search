package com.example.favorite_cities

import com.example.favorite_cities.contract.GeneralCitiesContract
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.presenter.GeneralPresenter
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.mockito.Mockito.times
import java.util.logging.Filter

class FavoritePresenterTest {

    private var view: GeneralCitiesContract.View = mock()
    private var model: CitiesModel = mock()
    private val dialogCreator: DialogCreator = mock()
    private var presenter: GeneralPresenter = GeneralPresenter(view, model, dialogCreator)

    @Test
    fun `searchTextChanged when text is incorrect, and verify showSearchError`() {
//        model.updateGeneralCitiesList(SIMPLE_CITIES_LIST)
        val list: List<String> = mock()
//        whenever(model.filter(INCORRECT_CITY, SIMPLE_CITIES_LIST)).thenReturn(list)
        whenever(model.filterGeneralList(INCORRECT_CITY)).thenReturn(list)
        whenever(model.isGeneralCitiesFilteredEmpty()).thenReturn(true)
        presenter.searchTextChanged(INCORRECT_CITY)

       verify(view, times(1))
            .showSearchError(R.string.nothing_found)
    }

//    @Test
//    fun `onCityClicked clicked on the favorite city, and verify showDialog with button remove`() {
//        presenter.onCityClicked(FAVORITE_CITY)
//        verify(view, times(1))
//    }
//
//    @Test
//    fun `onCityClicked clicked not on the favorite city, and verify showDialog with button add`() {
//
//    }

    companion object {
        private const val INCORRECT_CITY = "Paris"
        private const val FAVORITE_CITY = "Anapa"
        private val SIMPLE_CITIES_LIST = listOf("Anapa", "Moscow", "Orel")
    }
}