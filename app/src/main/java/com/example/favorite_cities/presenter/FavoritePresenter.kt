package com.example.favorite_cities.presenter

import com.example.favorite_cities.contract.FavoriteCitiesContract
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R

open class FavoritePresenter(
    citiesView: FavoriteCitiesContract.View,
    citiesModel: CitiesModel,
    CitiesDialogCreator: DialogCreator
) : BaseCitiesPresenter<FavoriteCitiesContract.View>(citiesView, citiesModel, CitiesDialogCreator),
    FavoriteCitiesContract.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()
        model.getFavoriteEnteredText()?.let {
            view.setEnteredText(it)
        }
        showOrHideSearchError()
        view.showCitiesList(model.getFavoriteCitiesFiltered())
    }

    override fun onFragmentVisible() {
        model.setVisibleFavoriteFragment(true)
        showOrHideSearchError()
        view.updateCitiesList(model.getFavoriteCitiesFiltered())
    }

    override fun searchTextChanged(text: String?) {
        model.filterFavoriteList(text)
        showOrHideSearchError()
        if (model.isFavoriteCitiesNotEmpty()) {
            view.updateCitiesList(model.getFavoriteCitiesFiltered())
        }
    }

    override fun removeFavoriteCity(nameCity: String) {
        super.removeFavoriteCity(nameCity)
        showOrHideSearchError()
        view.updateCitiesList(model.getFavoriteCitiesFiltered())
    }

    private fun showOrHideSearchError() =
        when {
            model.isFavoriteCitiesEmpty() -> view.showSearchError(R.string.no_favorite_cities)
            model.isFavoriteCitiesFilteredEmpty() -> view.showSearchError(R.string.nothing_found)
            else -> view.hideSearchError()
        }
}