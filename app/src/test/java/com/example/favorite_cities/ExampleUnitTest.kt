package com.example.favorite_cities

import com.example.favorite_cities.contract.GeneralCitiesContract
import com.example.favorite_cities.fragments.GeneralFragment
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.model.sharedpreferences.PreferenceManager
import com.example.favorite_cities.presenter.GeneralPresenter
//import com.google.common.truth.Truth.assertThat
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
//import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Spy
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class FavoritePresenterTest {

//    private val citiesList: List<String> =
//        listOf("Moscow", "Volgograd", "Perm", "Khabarovsk", "Murmansk")

    @Mock
    private lateinit var view: GeneralCitiesContract.View

    @Mock
    private lateinit var model: CitiesModel

    @Mock
    private val dialogCreator = DialogCreator()

    @Spy
    private var presenter: GeneralPresenter = GeneralPresenter(view, model, dialogCreator)

//    @Before
//    fun setUp() {
//        presenter
//    }

    @Test
    fun `test_searchTextChanged_incorrectTextEntered_showSearchError` () {
        Mockito.`when`(model.filterGeneralList("Omsk"))
            .thenReturn(listOf())

        Mockito.`when`(model.isGeneralCitiesFilteredEmpty())
            .thenReturn(true)

        val incorrectTextEntered = "Omsk"
        presenter.searchTextChanged(incorrectTextEntered)

        Mockito.verify(view, times(1))
            .showSearchError(R.string.nothing_found)
    }
}