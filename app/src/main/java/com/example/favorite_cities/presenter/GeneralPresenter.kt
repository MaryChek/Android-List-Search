package com.example.favorite_cities.presenter

import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.GeneralCitiesContract

class GeneralPresenter(
    private var model: CitiesModel
) : BasePresenter<GeneralCitiesContract.View>(), GeneralCitiesContract.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()
        val enteredText: String? = model.getEnteredTextInGeneral()
        val citiesList: List<String> =
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
        val filteredList: List<String> = model.filterGeneralList(text)
        showOrHideSlideNothingFound()
        view?.updateCitiesList(filteredList)
    }

    override fun onCityClicked(nameCity: String) {
        dialogCreator = initDialogCreator(nameCity)
        view?.showDialogFragment(dialogCreator)
    }

    override fun addCityToFavorite(nameCity: String) {
        model.addFavoriteCity(nameCity)
        println(model.getFilteredFavoriteCities())
        println(model.getEnteredTextInFavorite())
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

    private fun initDialogCreator(nameCity: String): DialogCreator =
        when (model.findInFavorites(nameCity)) {
            true -> DialogCreator(
                nameCity,
                R.string.message_favorite_city,
                R.string.text_button_remove,
                R.string.message_after_removal,
                R.string.button_cancel,
                this::removeCityFromFavorite,
                this.view!!::showMessageAfterPositiveClick
            )
            false -> DialogCreator(
                nameCity,
                R.string.message_unelected_city,
                R.string.text_button_add,
                R.string.message_after_adding,
                R.string.button_cancel,
                this::addCityToFavorite,
                this.view!!::showMessageAfterPositiveClick
            )
        }
}