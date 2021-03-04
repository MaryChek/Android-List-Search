package com.example.favorite_cities.model

import android.content.res.Resources
import com.example.favorite_cities.App
import com.example.favorite_cities.R
import com.example.favorite_cities.model.sharedpreferences.PreferenceManager
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert
import org.junit.Test

class ModelTest {
    private val preferenceManager: PreferenceManager = mock()
    private val activity: App = mock()
    private var model: CitiesModel = CitiesModel(activity)

    private fun stubToGetTheFavoriteList() {
        val setOfIndexOfTheFavorites: Set<String> =
            model.getIndexHashSetOf(model.favoriteCities)
        whenever(preferenceManager.getList(KEY_FAVORITE_CITIES_LIST))
            .thenReturn(setOfIndexOfTheFavorites)
    }

    private fun updateFavoriteCities(simpleList: List<String>?) {
        simpleList?.let {
            model.updateFavoriteCities(it)
        }
    }

    private fun setSimpleFavoriteCities(simpleList: List<String>?) {
        simpleList?.forEach {
            model.addFavoriteCity(it)
        }
    }

    @Test
    fun `updateCitiesLists when update cities list then assert generalCities`() {
        val mockRes: Resources = mock()
        whenever(activity.resources)
            .thenReturn(mockRes)
        whenever(activity.resources.getStringArray(any()))
            .thenReturn(arrayOf<String>())
        whenever(activity.resources.getStringArray(R.array.cities).toList())
            .thenReturn(SIMPLE_CITIES_LIST)

        model.updateCitiesLists()

        val citiesListsExpected: List<String> = SIMPLE_CITIES_LIST
        val citiesListActual: List<String> = model.generalCities
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `updateCitiesLists when general entered text is null or empty then assert generalCitiesFiltered`() {
        model.setGeneralEnteredText(null)
        model.updateCitiesLists()

        val citiesListsExpected: List<String> = SIMPLE_CITIES_LIST
        val citiesListActual: List<String> = model.generalCitiesFiltered
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `updateCitiesLists when general entered text isn't null or empty then assert generalCitiesFiltered`() {
        model.setGeneralEnteredText(SIMPLE_ENTERED_TEXT)
        model.updateCitiesLists()

        val citiesListsExpected: List<String> = GENERAL_CITIES_BY_ENTERED_TEXT
        val citiesListActual: List<String> = model.generalCitiesFiltered
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `updateCitiesLists when saved favorite cities list is null then assert favoriteCities`() {
        setSimpleFavoriteCities(null)
        stubToGetTheFavoriteList()

        model.updateCitiesLists()

        val citiesListsExpected: List<String> = listOf()
        val citiesListActual: List<String> = model.favoriteCities
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `updateCitiesLists when saved favorite cities list is null then assert favoriteCitiesFiltered`() {
        setSimpleFavoriteCities(null)
        stubToGetTheFavoriteList()

        model.updateCitiesLists()

        val citiesListsExpected: List<String> = listOf()
        val citiesListActual: List<String> = model.favoriteCitiesFiltered
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `updateCitiesLists when saved favorite cities list isn't null then assert favoriteCities`() {
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)
        stubToGetTheFavoriteList()

        model.updateCitiesLists()

        val citiesListsExpected: List<String> = SIMPLE_FAVORITE_CITIES_LIST
        val citiesListActual: List<String> = model.favoriteCities
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `updateCitiesLists when favorite entered text is null or empty then assert favoriteCitiesFiltered`() {
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)
        stubToGetTheFavoriteList()

        model.setFavoriteEnteredText(null)
        model.updateCitiesLists()

        val citiesListsExpected: List<String> = SIMPLE_FAVORITE_CITIES_LIST
        val citiesListActual: List<String> = model.favoriteCitiesFiltered
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `updateCitiesLists when favorite entered text isn't null or empty then assert favoriteCitiesFiltered`() {
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)
        stubToGetTheFavoriteList()
        model.setFavoriteEnteredText(SIMPLE_ENTERED_TEXT)

        model.updateCitiesLists()

        val citiesListsExpected: List<String> = FAVORITE_CITIES_BY_ENTERED_TEXT
        val citiesListActual: List<String> = model.favoriteCitiesFiltered
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `filterGeneralList when filterGeneralList invoked, then assert general entered text`() {
        model.filterGeneralList(SIMPLE_ENTERED_TEXT)

        val enteredTextExpected: String = SIMPLE_ENTERED_TEXT
        val enteredTextActual: String? = model.generalEnteredText
        Assert.assertEquals(enteredTextExpected, enteredTextActual)
    }

    @Test
    fun `filterGeneralList when general text entered is blank, then assert general cities filtered`() {
        model.filterGeneralList(BLANK_ENTERED_TEXT)

        val citiesListsExpected: List<String> = SIMPLE_CITIES_LIST
        val citiesListActual: List<String> = model.generalCitiesFiltered
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `filterGeneralList when general text entered isn't blank, then assert general cities filtered`() {
        model.filterGeneralList(SIMPLE_ENTERED_TEXT)

        val citiesListsExpected: List<String> = GENERAL_CITIES_BY_ENTERED_TEXT
        val citiesListActual: List<String> = model.generalCitiesFiltered
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `filterFavoriteList when filterFavoriteList invoked, then assert favorite entered text`() {
        model.filterFavoriteList(SIMPLE_ENTERED_TEXT)

        val enteredTextExpected: String = SIMPLE_ENTERED_TEXT
        val enteredTextActual: String? = model.favoriteEnteredText
        Assert.assertEquals(enteredTextExpected, enteredTextActual)
    }

    @Test
    fun `filterFavoriteList when favorite cities list is null, then assert favorite cities filtered`() {
        setSimpleFavoriteCities(null)
        model.filterFavoriteList(SIMPLE_ENTERED_TEXT)

        val citiesListsExpected: List<String> = listOf()
        val citiesListActual: List<String> = model.favoriteCitiesFiltered
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `filterFavoriteList when favorite text entered is blank, then assert favorite cities filtered`() {
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)
        model.filterFavoriteList(BLANK_ENTERED_TEXT)

        val citiesListsExpected: List<String> = SIMPLE_FAVORITE_CITIES_LIST
        val citiesListActual: List<String> = model.favoriteCitiesFiltered
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `filterFavoriteList when favorite text entered isn't blank, then assert favorite cities filtered`() {
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)
        model.filterFavoriteList(SIMPLE_ENTERED_TEXT)

        val citiesListsExpected: List<String> = FAVORITE_CITIES_BY_ENTERED_TEXT
        val citiesListActual: List<String> = model.favoriteCitiesFiltered
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `addFavoriteCity when add favorite city then assert favorite cities`() {
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)
        model.addFavoriteCity(SIMPLE_CITY)

        val citiesListsExpected: List<String> = SIMPLE_FAVORITE_CITIES_LIST.plus(SIMPLE_CITY)
        val citiesListActual: List<String> = model.favoriteCities
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `addFavoriteCity when favorite entered text is blank then assert favorite cities filtered`() {
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)
        model.setFavoriteEnteredText(null)
        model.addFavoriteCity(SIMPLE_CITY)

        val citiesListsExpected: List<String> = SIMPLE_FAVORITE_CITIES_LIST.plus(SIMPLE_CITY)
        val citiesListActual: List<String> = model.favoriteCitiesFiltered
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `addFavoriteCity when favorite entered text isn't blank then assert favorite cities filtered`() {
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)
        model.setFavoriteEnteredText(SIMPLE_ENTERED_TEXT)
        model.addFavoriteCity(SIMPLE_CITY)

        val citiesListsExpected: List<String> = SIMPLE_FAVORITE_CITIES_LIST.plus(SIMPLE_CITY)
            .filter {
                it.contains(SIMPLE_ENTERED_TEXT, true)
            }
        val citiesListActual: List<String> = model.favoriteCitiesFiltered
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `addFavoriteCity when add favorite city then verify saveFavoriteList invoked`() {
        updateFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)
        model.addFavoriteCity(SIMPLE_CITY)

        val hashSetOfFavoriteCities: HashSet<String> = model.getIndexHashSetOf(model.favoriteCities)
        verify(preferenceManager).setList(KEY_FAVORITE_CITIES_LIST, hashSetOfFavoriteCities)
    }

    @Test
    fun `removeFavoriteCity when favorite cities was empty then assert favorite cities`() {
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)
        model.removeFavoriteCity(SIMPLE_FAVORITE_CITY)

        val citiesListsExpected: List<String> =
            SIMPLE_FAVORITE_CITIES_LIST.minus(SIMPLE_FAVORITE_CITY)
        val citiesListActual: List<String> = model.favoriteCities
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `removeFavoriteCity when favorite cities was empty then assert favorite cities filtered`() {
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)
        model.removeFavoriteCity(SIMPLE_FAVORITE_CITY)

        val citiesListsExpected: List<String> =
            FAVORITE_CITIES_BY_ENTERED_TEXT.minus(SIMPLE_FAVORITE_CITY)
        val citiesListActual: List<String> = model.favoriteCitiesFiltered
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `removeFavoriteCity when add favorite city then verify saveFavoriteList invoked`() {
        updateFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)
        model.removeFavoriteCity(SIMPLE_FAVORITE_CITY)

        val hashSetOfFavoriteCities: HashSet<String> =
            model.getIndexHashSetOf(model.favoriteCities)
        verify(preferenceManager, times(1))
            .setList(KEY_FAVORITE_CITIES_LIST, hashSetOfFavoriteCities)
    }

    @Test
    fun `findInFavorites when found city is not in the favorites, then assert returned value`() {
        val expected = false
        val actualValue: Boolean = model.findInFavorites(SIMPLE_CITY)
        Assert.assertEquals(expected, actualValue)
    }

    @Test
    fun `findInFavorites when found city in the favorites, then assert returned value`() {
        model.addFavoriteCity(SIMPLE_CITY)
        val expected = true
        val actualValue: Boolean = model.findInFavorites(SIMPLE_CITY)
        Assert.assertEquals(expected, actualValue)
    }

    companion object {
        private val SIMPLE_CITIES_LIST = listOf("Anapa", "Moscow", "Orel", "Omsk", "Khabarovsk")
        private val SIMPLE_CITY = SIMPLE_CITIES_LIST[0]
        private val SIMPLE_FAVORITE_CITIES_LIST = listOf("Anapa", "Moscow", "Orel")
        private val SIMPLE_FAVORITE_CITY = SIMPLE_FAVORITE_CITIES_LIST[0]
        private const val BLANK_ENTERED_TEXT = ""
        private const val SIMPLE_ENTERED_TEXT = "o"
        private val GENERAL_CITIES_BY_ENTERED_TEXT = SIMPLE_CITIES_LIST.filter {
            it.contains(SIMPLE_ENTERED_TEXT, true)
        }
        private val FAVORITE_CITIES_BY_ENTERED_TEXT = SIMPLE_FAVORITE_CITIES_LIST.filter {
            it.contains(SIMPLE_ENTERED_TEXT, true)
        }
        private const val KEY_FAVORITE_CITIES_LIST = "CitiesFavorite"
    }
}