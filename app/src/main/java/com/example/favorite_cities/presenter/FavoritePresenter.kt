package com.example.favorite_cities.presenter

import com.example.favorite_cities.contract.FavoriteCitiesContract
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R

class FavoritePresenter(
    private var model: CitiesModel
) : BasePresenter<FavoriteCitiesContract.View>(), FavoriteCitiesContract.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()
        showCitiesListOnOpenFragment()
        model.getEnteredTextInFavorite()?.let {
            view?.setEnteredText(it)
        }
    }

    override fun searchTextChanged(text: String?) {
        val filteredList: List<String> = model.filterFavoriteList(text)
        if (model.getFavoriteCities().isNotEmpty()) {
            showOrHideSlideNothingFound()
            view?.updateCitiesList(filteredList)
        }
    }

    override fun onFragmentVisible() {
        model.setVisibleFavoriteFragment(true)
        showCitiesListOnOpenFragment()
    }

    override fun onCityClicked(nameCity: String) {
        dialogCreator = initDialogCreator(nameCity)
        view?.showDialogFragment(dialogCreator)
    }

    override fun removeCity(nameCity: String) {
        model.removeFavoriteCity(nameCity)
        val newCitiesList: List<String> =
            if (model.getFavoriteCities().isEmpty()) {
                showSlideWithText(R.string.no_favorite_cities, true)
                listOf()
            } else {
                showOrHideSlideNothingFound()
                model.getFilteredFavoriteCities()
            }
        view?.updateCitiesList(newCitiesList)
    }

    private fun showCitiesListOnOpenFragment() {
        if (model.getFavoriteCities().isNotEmpty()) {
            showSlideWithText(R.string.no_favorite_cities, false)
            showOrHideSlideNothingFound()
        } else {
            showSlideWithText(R.string.no_favorite_cities, true)
        }
        val newCitiesList: List<String> = selectListDependingOnTheEnteredText()
        view?.showCitiesList(newCitiesList)
    }

    private fun selectListDependingOnTheEnteredText(): List<String> =
        when (model.getEnteredTextInFavorite().isNullOrEmpty()) {
            true -> model.getFavoriteCities()
            false -> model.getFilteredFavoriteCities()
        }

    private fun showOrHideSlideNothingFound() {
        if (model.getFilteredFavoriteCities().isEmpty()) {
            showSlideWithText(R.string.nothing_found, true)
        } else {
            showSlideWithText(R.string.nothing_found, false)
        }
    }

    private fun showSlideWithText(idTextResource: Int, show: Boolean) {
        view?.showTextSlide(idTextResource, show)
    }

    private fun initDialogCreator(nameCity: String): DialogCreator =
        DialogCreator(
            nameCity,
            R.string.message_favorite_city,
            R.string.text_button_remove,
            R.string.message_after_removal,
            R.string.button_cancel,
            this::removeCity,
            this.view!!::showMessageAfterPositiveClick
        )
}