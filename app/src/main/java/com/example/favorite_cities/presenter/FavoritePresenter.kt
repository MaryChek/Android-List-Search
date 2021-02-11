package com.example.favorite_cities.presenter

import com.example.favorite_cities.contract.FavoriteCitiesContract
import com.example.favorite_cities.CitiesModel
import com.example.favorite_cities.DialogCreator

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
        val dialogCreator = initDialogCreator(nameCity)
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

    private fun initDialogCreator(nameCity: String): DialogCreator {
        val messageForFavorite = view?.getResourceId("message_favorite_city")
        val messageAfterRemove = view?.getResourceId("text_button_remove")
        val removeButton = view?.getResourceId("text_button_remove")
        val cancel = view?.getResourceId("button_cancel")

        if (messageForFavorite == null || messageAfterRemove == null
            || removeButton == null || cancel == null
        )
            throw IllegalStateException("No such resource")

        return DialogCreator(
            nameCity,
            messageForFavorite,
            removeButton,
            messageAfterRemove,
            cancel,
            this::removeCity,
            this::showMessageAfterPositiveClick
        )
    }
}