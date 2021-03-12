package com.example.favorite_cities.presenter

import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CitiesContract
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.model.CityIcon

open class BaseCitiesPresenter<V : CitiesContract.View>(
    view: V,
    protected val model: CitiesModel
) : BasePresenter<V>(view), CitiesContract.Presenter<V> {

    protected open fun getCitiesCollection(): CitiesKey =
        CitiesKey.GENERAL

    override fun onViewCreated() {
        getEnteredText()?.let {
            view.setEnteredText(it)
        }
        view.updateCitiesList(getFilteredList())
    }

    private fun getEnteredText(): String? =
        when (getCitiesCollection()) {
            CitiesKey.FAVORITE -> model.favoriteEnteredText
            CitiesKey.GENERAL -> model.generalEnteredText
        }

    override fun onTabVisible() {
        if (isCollectionFavorite()) {
            showOrHideEmptyListHint()
        }
        view.updateCitiesList(getFilteredList())
    }

    override fun onCityIconClicked(nameCity: String) {
        when (model.findInFavorites(nameCity)) {
            true -> view.showRemoveFromFavoriteActionConfirmation(nameCity)
            false -> view.showAddToFavoriteActionConfirmation(nameCity)
        }
    }

    override fun onSearchTextChanged(text: String?) {
        filter(text)
        showOrHideEmptyListHint()
        view.updateCitiesList(getFilteredList())
    }

    private fun filter(text: String?) =
        when (getCitiesCollection()) {
            CitiesKey.FAVORITE -> model.filterFavoriteList(text)
            CitiesKey.GENERAL -> model.filterGeneralList(text)
        }

    private fun getFilteredList(): List<CityIcon> =
        when (getCitiesCollection()) {
            CitiesKey.FAVORITE -> model.getFavoriteCitiesIconFiltered()
            CitiesKey.GENERAL -> model.getGeneralCitiesIconFiltered()
        }

    override fun onAddToFavoriteClick(nameCity: String) {
        addFavoriteCity(nameCity)
        view.showUserMessage(R.string.message_after_adding, nameCity)
    }

    private fun addFavoriteCity(nameCity: String) {
        model.addFavoriteCity(nameCity)
        if (isCollectionFavorite()) {
            showOrHideEmptyListHint()
        }
        view.updateCitiesList(getFilteredList())
    }

    override fun onRemoveFromFavoriteClick(nameCity: String) {
        removeFavoriteCity(nameCity)
        view.showUserMessage(R.string.message_after_removal, nameCity)
    }

    private fun removeFavoriteCity(nameCity: String) {
        model.removeFavoriteCity(nameCity)
        if (isCollectionFavorite()) {
            showOrHideEmptyListHint()
        }
        view.updateCitiesList(getFilteredList())
    }

    private fun showOrHideEmptyListHint() =
        if (isCollectionFavorite() && model.isFavoriteCitiesEmpty()) {
            view.showEmptyListHint(R.string.no_favorite_cities)
        } else if (getFilteredList().isEmpty()) {
            view.showEmptyListHint(R.string.nothing_found)
        } else {
            view.hideEmptyListHint()
        }

    private fun isCollectionFavorite(): Boolean =
        getCitiesCollection() == CitiesKey.FAVORITE

    override fun onViewPause() {
        model.saveFavoriteList()
    }

    protected enum class CitiesKey {
        GENERAL, FAVORITE
    }
}