package com.example.favorite_cities.presenter

import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.contract.GeneralCitiesContract

class GeneralPresenter(
    private var model: CitiesModel
) : BasePresenter<GeneralCitiesContract.View>(), GeneralCitiesContract.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()
        val enteredText = model.getEnteredTextInGeneral()
        val citiesList =
            when (enteredText.isNullOrEmpty()) {
                true -> model.getGeneralCities()
                false -> model.getFilteredGeneralCities()
            }
        view?.showCitiesList(citiesList)
        enteredText?.let {
            view?.setEnteredText(it)
        }
    }

    override fun onFragmentVisible() {
        model.setVisibleFavoriteFragment(false)
    }

    override fun searchTextChanged(text: String?) {
        val filteredList = model.filterGeneralList(text)
        showOrHideSlideNothingFound()
        view?.updateCitiesList(filteredList)
    }

    override fun onCityClicked(nameCity: String) {
        val dialogCreator = initDialogCreator(nameCity)
        view?.showDialogFragment(dialogCreator)
    }

    override fun findCityInFavoriteModel(nameCity: String): Boolean =
        model.findInFavorites(nameCity)

    override fun showMessageAfterPositiveClick(id: Int, nameCity: String): String? =
        view?.getResourceString(id, nameCity)

    override fun addCityToFavorite(nameCity: String) {
        model.addFavoriteCity(nameCity)
    }

    override fun removeCityFromFavorite(nameCity: String) {
        model.removeFavoriteCity(nameCity)
    }

    private fun showOrHideSlideNothingFound() {
        if (model.getFilteredGeneralCities().isEmpty()) {
            view?.showSlideNothingFound(true)
        } else {
            view?.showSlideNothingFound(false)
        }
    }

    private fun initDialogCreator(nameCity: String): DialogCreator {
        val messageForFavorite = view?.getResourceId("message_favorite_city")
        val messageForUnelected = view?.getResourceId("message_unelected_city")
        val messageAfterAdd = view?.getResourceId("message_after_adding")
        val messageAfterRemove = view?.getResourceId("text_button_remove")
        val addButton = view?.getResourceId("text_button_add")
        val removeButton = view?.getResourceId("text_button_remove")
        val cancel = view?.getResourceId("button_cancel")

        if (messageForFavorite == null || messageForUnelected == null
            || messageAfterAdd == null || messageAfterRemove == null
            || removeButton == null || addButton == null || cancel == null
        )
            throw IllegalStateException("No such resource")

        return when (model.findInFavorites(nameCity)) {
            true -> {
                DialogCreator(
                    nameCity,
                    messageForFavorite,
                    removeButton,
                    messageAfterRemove,
                    cancel,
                    this::removeCityFromFavorite,
                    this::showMessageAfterPositiveClick
                )
            }
            false -> DialogCreator(
                nameCity,
                messageForUnelected,
                addButton,
                messageAfterAdd,
                cancel,
                this::addCityToFavorite,
                this::showMessageAfterPositiveClick
            )
        }
    }
}