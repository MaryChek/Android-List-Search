package com.example.favorite_cities.model

import android.content.res.Resources
import com.example.favorite_cities.App
import com.example.favorite_cities.model.sharedpreferences.PreferenceManager
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert
import org.junit.Test

class CitiesModelTest {
    private val preferenceManager: PreferenceManager = mock()
    private val activity: App = mock()
    private val resources: Resources = mock()
    private val model: CitiesModel = CitiesModel(activity, SIMPLE_CITIES_LIST, preferenceManager)

    private fun stubToGetStringArray() {
        whenever(resources.getStringArray(any()))
            .thenReturn(SIMPLE_CITIES_ARRAY)
        whenever(activity.resources)
            .thenReturn(resources)
    }

    private fun setSimpleFavoriteCities(simpleList: List<String>?) {
        simpleList?.forEach {
            model.addFavoriteCity(it)
        }
    }

    private fun getGeneralCitiesIconList(): List<CityIcon> {
        val generalCitiesIconList: MutableList<CityIcon> = mutableListOf()
        generalCitiesIconList.add(CityIcon("Anapa", DRAWABLE_FOR_FAVORITE_IN_GENERAL))
        generalCitiesIconList.add(CityIcon("Moscow", DRAWABLE_FOR_FAVORITE_IN_GENERAL))
        generalCitiesIconList.add(CityIcon("Orel", DRAWABLE_FOR_FAVORITE_IN_GENERAL))
        generalCitiesIconList.add(CityIcon("Omsk", DRAWABLE_FOR_GENERAL))
        generalCitiesIconList.add(CityIcon("Khabarovsk", DRAWABLE_FOR_GENERAL))
        return generalCitiesIconList
    }

    private fun getGeneralCitiesIconListByEnteredText(): List<CityIcon> {
        val generalCitiesIconList: MutableList<CityIcon> = mutableListOf()
        generalCitiesIconList.add(CityIcon("Moscow", DRAWABLE_FOR_FAVORITE_IN_GENERAL))
        generalCitiesIconList.add(CityIcon("Orel", DRAWABLE_FOR_FAVORITE_IN_GENERAL))
        generalCitiesIconList.add(CityIcon("Omsk", DRAWABLE_FOR_GENERAL))
        generalCitiesIconList.add(CityIcon("Khabarovsk", DRAWABLE_FOR_GENERAL))
        return generalCitiesIconList
    }

    private fun getFavoriteCitiesIconList(): List<CityIcon> {
        val favoriteCitiesIconList: MutableList<CityIcon> = mutableListOf()
        favoriteCitiesIconList.add(CityIcon("Anapa", DRAWABLE_FOR_FAVORITE))
        favoriteCitiesIconList.add(CityIcon("Moscow", DRAWABLE_FOR_FAVORITE))
        favoriteCitiesIconList.add(CityIcon("Orel", DRAWABLE_FOR_FAVORITE))
        return favoriteCitiesIconList
    }

    private fun getFavoriteCitiesIconListByEnteredText(): List<CityIcon> {
        val favoriteCitiesIconList: MutableList<CityIcon> = mutableListOf()
        favoriteCitiesIconList.add(CityIcon("Moscow", DRAWABLE_FOR_FAVORITE))
        favoriteCitiesIconList.add(CityIcon("Orel", DRAWABLE_FOR_FAVORITE))
        return favoriteCitiesIconList
    }

    @Test
    fun `updateCitiesLists when update cities list then assert generalCities`() {
        stubToGetStringArray()

        model.updateCitiesLists()

        val citiesListsExpected: List<String> = SIMPLE_CITIES_LIST
        val citiesListActual: List<String> = model.generalCities
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `updateCitiesLists when update cities list then verify getSetByKey invoked`() {
        stubToGetStringArray()

        model.updateCitiesLists()

        verify(preferenceManager, times(2))
            .getSetByKey(KEY_INDEXES_OF_FAVORITE)
    }

    @Test
    fun `updateCitiesLists when update cities list then assert favoriteCities`() {
        stubToGetStringArray()
        whenever(preferenceManager.getSetByKey(KEY_INDEXES_OF_FAVORITE))
            .thenReturn(SET_OF_INDEXES_OF_FAVORITE)

        model.updateCitiesLists()

        val citiesListsExpected: List<String> = SIMPLE_FAVORITE_CITIES_LIST
        val citiesListActual: List<String> = model.favoriteCities
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `getGeneralCitiesIconFiltered when entered text is empty invoked then assert returned value`() {
        model.getGeneralCitiesIconFiltered()

        val citiesIconListsExpected: List<CityIcon> = getGeneralCitiesIconList()
        val citiesIconListActual: List<CityIcon> = model.getGeneralCitiesIconFiltered()
        Assert.assertEquals(citiesIconListsExpected, citiesIconListActual)
    }

    @Test
    fun `getGeneralCitiesIconFiltered when entered text isn't empty invoked then assert returned value`() {
        model.generalEnteredText = SIMPLE_ENTERED_TEXT

        model.getGeneralCitiesIconFiltered()

        val citiesIconListsExpected: List<CityIcon> = getGeneralCitiesIconListByEnteredText()
        val citiesIconListActual: List<CityIcon> = model.getGeneralCitiesIconFiltered()
        Assert.assertEquals(citiesIconListsExpected, citiesIconListActual)
    }

    @Test
    fun `getFavoriteCitiesIconFiltered when entered text is empty invoked then assert returned value`() {
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)

        model.getFavoriteCitiesIconFiltered()

        val citiesIconListsExpected: List<CityIcon> = getFavoriteCitiesIconList()
        val citiesIconListActual: List<CityIcon> = model.getFavoriteCitiesIconFiltered()
        Assert.assertEquals(citiesIconListsExpected, citiesIconListActual)
    }

    @Test
    fun `getFavoriteCitiesIconFiltered when entered text isn't empty invoked then assert returned value`() {
        model.favoriteEnteredText = SIMPLE_ENTERED_TEXT
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)

        model.getFavoriteCitiesIconFiltered()

        val citiesIconListsExpected: List<CityIcon> = getFavoriteCitiesIconListByEnteredText()
        val citiesIconListActual: List<CityIcon> = model.getFavoriteCitiesIconFiltered()
        Assert.assertEquals(citiesIconListsExpected, citiesIconListActual)
    }

    @Test
    fun `filterGeneralList when filterGeneralList invoked, then assert general entered text`() {
        model.filterGeneralList(SIMPLE_ENTERED_TEXT)

        val enteredTextExpected: String = SIMPLE_ENTERED_TEXT
        val enteredTextActual: String? = model.generalEnteredText
        Assert.assertEquals(enteredTextExpected, enteredTextActual)
    }

    @Test
    fun `filterFavoriteList when filterFavoriteList invoked, then assert favorite entered text`() {
        model.filterFavoriteList(SIMPLE_ENTERED_TEXT)

        val enteredTextExpected: String = SIMPLE_ENTERED_TEXT
        val enteredTextActual: String? = model.favoriteEnteredText
        Assert.assertEquals(enteredTextExpected, enteredTextActual)
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
    fun `removeFavoriteCity when remove favorite city then assert favorite cities`() {
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)
        model.removeFavoriteCity(SIMPLE_FAVORITE_CITY)

        val citiesListsExpected: List<String> =
            SIMPLE_FAVORITE_CITIES_LIST.minus(SIMPLE_FAVORITE_CITY)
        val citiesListActual: List<String> = model.favoriteCities
        Assert.assertEquals(citiesListsExpected, citiesListActual)
    }

    @Test
    fun `findInFavorites when city not found in favorites, then assert returned value`() {
        val expected = false
        val actualValue: Boolean = model.findInFavorites(SIMPLE_CITY)
        Assert.assertEquals(expected, actualValue)
    }

    @Test
    fun `findInFavorites when city found in favorites, then assert returned value`() {
        model.addFavoriteCity(SIMPLE_CITY)
        val expected = true
        val actualValue: Boolean = model.findInFavorites(SIMPLE_CITY)
        Assert.assertEquals(expected, actualValue)
    }

    @Test
    fun `saveFavoriteList when save favorite list then verify putSetByKey invoked`() {
        setSimpleFavoriteCities(SIMPLE_FAVORITE_CITIES_LIST)

        model.saveFavoriteList()

        verify(preferenceManager, times(1))
            .putSetByKey(KEY_INDEXES_OF_FAVORITE, SET_OF_INDEXES_OF_FAVORITE)
    }

    companion object {
        private val SIMPLE_CITIES_ARRAY = arrayOf("Anapa", "Moscow", "Orel", "Omsk", "Khabarovsk")
        private val SIMPLE_CITIES_LIST = listOf("Anapa", "Moscow", "Orel", "Omsk", "Khabarovsk")
        private val SET_OF_INDEXES_OF_FAVORITE = setOf("0", "1", "2")
        private val SIMPLE_FAVORITE_CITIES_LIST = listOf("Anapa", "Moscow", "Orel")
        private val SIMPLE_CITY = SIMPLE_CITIES_LIST[0]
        private val SIMPLE_FAVORITE_CITY = SIMPLE_FAVORITE_CITIES_LIST[0]
        private const val SIMPLE_ENTERED_TEXT = "o"
        private const val KEY_INDEXES_OF_FAVORITE = "CitiesFavorite"
        private const val DRAWABLE_FOR_FAVORITE_IN_GENERAL = android.R.drawable.btn_star_big_on
        private const val DRAWABLE_FOR_GENERAL = android.R.drawable.btn_star_big_off
        private const val DRAWABLE_FOR_FAVORITE = android.R.drawable.ic_menu_delete
    }
}