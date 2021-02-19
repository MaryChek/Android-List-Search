package com.example.favorite_cities.presenter

import com.example.favorite_cities.contract.FavoriteCitiesContract
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R

class FavoritePresenter(
    citiesView: FavoriteCitiesContract.View,
    citiesModel: CitiesModel,
    CitiesDialogCreator: DialogCreator
) : BaseCitiesPresenter<FavoriteCitiesContract.View>(citiesView, citiesModel, CitiesDialogCreator),
    FavoriteCitiesContract.Presenter{

    override fun onViewCreated() {
        super.onViewCreated()
        model.getFavoriteEnteredText()?.let {
            view.setEnteredText(it)
        }
        showOrHideSearchError()
        val filteredCities: List<String> = model.getFavoriteCitiesFiltered()
        view.showCitiesList(filteredCities)
    }

    override fun onFragmentVisible() {
        model.setVisibleFavoriteFragment(true)
        showOrHideSearchError()
        view.updateCitiesList(model.getFavoriteCitiesFiltered())
    }

    override fun searchTextChanged(text: String?) {
        val filteredCities: List<String> = model.filterFavoriteList(text)
        if (model.isFavoriteCitiesNotEmpty()) {
            showOrHideSearchError()
            view.updateCitiesList(filteredCities)
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