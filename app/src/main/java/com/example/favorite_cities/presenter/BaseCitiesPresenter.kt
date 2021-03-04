package com.example.favorite_cities.presenter

import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CitiesContract
import com.example.favorite_cities.model.CitiesModel

open class BaseCitiesPresenter<V : CitiesContract.View>(
    newView: V,
    newModel: CitiesModel,
    newDialogCreator: DialogCreator
) : BasePresenter<V>(newView), CitiesContract.Presenter<V> {

    protected val model: CitiesModel = newModel
    private val dialogCreator: DialogCreator = newDialogCreator

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

    override fun onDestroy() {
        dialogCreator.onDestroy()
    }

    override fun onTabVisible() {
        if (isCollectionFavorite()) {
            showOrHideEmptyListHint()
            view.updateCitiesList(model.getFavoriteCitiesFiltered())
        }
    }

    override fun onCityClicked(nameCity: String) {
        dialogCreator.setTitle(nameCity)
        when (model.findInFavorites(nameCity)) {
            true -> view.showDialogRemoving(nameCity)
            false -> view.showDialogAdding(nameCity)
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

    private fun getFilteredList(): List<String> =
        when (getCitiesCollection()) {
            CitiesKey.FAVORITE -> model.getFavoriteCitiesFiltered()
            CitiesKey.GENERAL -> model.getGeneralCitiesFiltered()
        }

    override fun onAddButtonClick(nameCity: String) {
        addFavoriteCity(nameCity)
        view.showUserMessage(R.string.message_after_adding, nameCity)
    }

    override fun onRemoveButtonClick(nameCity: String) {
        removeFavoriteCity(nameCity)
        view.showUserMessage(R.string.message_after_removal, nameCity)
    }

    private fun addFavoriteCity(nameCity: String) =
        model.addFavoriteCity(nameCity)

    protected open fun removeFavoriteCity(nameCity: String) {
        model.removeFavoriteCity(nameCity)
        if (isCollectionFavorite()) {
            showOrHideEmptyListHint()
            view.updateCitiesList(getFilteredList())
        }
    }

    protected open fun showOrHideEmptyListHint() =
        if (isCollectionFavorite() && model.isFavoriteCitiesEmpty()) {
            view.showEmptyListHint(R.string.no_favorite_cities)
        } else if (getFilteredList().isEmpty()) {
            view.showEmptyListHint(R.string.nothing_found)
        } else {
            view.hideEmptyListHint()
        }

    private fun isCollectionFavorite(): Boolean =
        getCitiesCollection() == CitiesKey.FAVORITE

    protected enum class CitiesKey {
        GENERAL, FAVORITE
    }
}