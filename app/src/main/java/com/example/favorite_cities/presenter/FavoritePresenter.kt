package com.example.favorite_cities.presenter

import com.example.favorite_cities.contract.FavoriteCitiesContract
import com.example.favorite_cities.CitiesModel
import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R

class FavoritePresenter(
    private var model: CitiesModel
) : BasePresenter<FavoriteCitiesContract.View>(), FavoriteCitiesContract.Presenter {

    override fun onViewCreated() {
        val citiesList = model.getFavoriteCities()
        if (citiesList.isNotEmpty()) {
            view?.showSlideNoFavorites(false)
        }
        view?.showCitiesList(citiesList)
    }

    override fun searchTextChanged(text: String?) {
        if (model.getFavoriteCities().isNotEmpty()) {
            val filteredList = model.filterFavoriteList(text)
            showOrHideSlideNothingFound()
            view?.updateCitiesList(filteredList)
        }
    }

    override fun onFragmentVisible() {
        if (model.getFavoriteCities().isNotEmpty()) {
            view?.showSlideNoFavorites(false)
            showOrHideSlideNothingFound()
        }
        val nameCity =
            when (view?.getEnteredText().isNullOrEmpty()) {
                true -> model.getFavoriteCities()
                false -> model.getFilterFavoriteCities()
            }
        view?.updateCitiesList(nameCity)
    }

    override fun showMessageAfterPositiveClick(id: Int, nameCity: String): String? =
        view?.getResourceString(id, nameCity)

    override fun onCityClicked(nameCity: String) {
        val dialogCreator = DialogCreator(
            nameCity,
            R.string.message_favorite_city,
            R.string.text_button_remove,
            R.string.message_after_removal,
            R.string.button_cancel,
            this::removeCity,
            this::showMessageAfterPositiveClick
        )
        view?.showDialogFragment(dialogCreator)
    }

    override fun removeCity(nameCity: String) {
        model.removeFavoriteCity(nameCity)

        val newList =
            if (model.getFavoriteCities().isEmpty()) {
                view?.showSlideNoFavorites(true)
                model.getFavoriteCities()
            } else {
                showOrHideSlideNothingFound()
                model.getFilterFavoriteCities()
            }
        view?.updateCitiesList(newList)
    }

    override fun findCityInFavoriteModel(nameCity: String): Boolean =
        model.findInFavorites(nameCity)

    private fun showOrHideSlideNothingFound() {
        if (model.getFilterFavoriteCities().isEmpty()) {
            view?.showSlideNothingFound(true)
        } else {
            view?.showSlideNothingFound(false)
        }
    }
}