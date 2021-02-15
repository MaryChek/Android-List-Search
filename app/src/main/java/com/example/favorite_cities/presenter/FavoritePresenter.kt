package com.example.favorite_cities.presenter

import com.example.favorite_cities.contract.FavoriteCitiesContract
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.DialogCreator

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
        if (model.getFavoriteCities().isNotEmpty()) {
            val filteredList = model.filterFavoriteList(text)
            showOrHideSlideNothingFound()
            view?.updateCitiesList(filteredList)
        }
    }

    override fun onFragmentVisible() {
        model.setVisibleFavoriteFragment(true)
        showCitiesListOnOpenFragment()
    }

    override fun showMessageAfterPositiveClick(id: Int, nameCity: String): String? =
        view?.getResourceString(id, nameCity)

    override fun onCityClicked(nameCity: String) {
        val dialogCreator = initDialogCreator(nameCity)
        view?.showDialogFragment(dialogCreator)
    }

    override fun removeCity(nameCity: String) {
        model.removeFavoriteCity(nameCity)
        val newCitiesList =
            if (model.getFavoriteCities().isEmpty()) {
                showSlideWithText("No favorite cities", true)
                listOf()
            } else {
                showOrHideSlideNothingFound()
                model.getFilteredFavoriteCities()
            }
        view?.updateCitiesList(newCitiesList)
    }

    override fun findCityInFavoriteModel(nameCity: String): Boolean =
        model.findInFavorites(nameCity)

    private fun showCitiesListOnOpenFragment() {
        if (model.getFavoriteCities().isNotEmpty()) {
            showSlideWithText("No favorite cities", false)
            showOrHideSlideNothingFound()
        }
        val newCitiesList = selectListDependingOnTheEnteredText()
        view?.showCitiesList(newCitiesList)
    }

    private fun selectListDependingOnTheEnteredText(): List<String> =
        when (model.getEnteredTextInFavorite().isNullOrEmpty()) {
            true -> model.getFavoriteCities()
            false -> model.getFilteredFavoriteCities()
        }

    private fun showOrHideSlideNothingFound() {
        if (model.getFilteredFavoriteCities().isEmpty()) {
            showSlideWithText("Nothing found", true)
        } else {
            showSlideWithText("Nothing found", false)
        }
    }

    private fun showSlideWithText(textResource: String, show: Boolean) {
        val textId = view?.getResourceId(textResource)
        textId?.let {
            view?.getResourceString(it)?.let { text ->
                view?.showTextSlide(text, show)
            }
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