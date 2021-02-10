package com.example.favorite_cities.presenter

import com.example.favorite_cities.CitiesModel
import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.GeneralCitiesContract

class GeneralPresenter(
    private var model: CitiesModel
) : BasePresenter<GeneralCitiesContract.View>(), GeneralCitiesContract.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()
        view?.showCitiesList(model.getGeneralCities())
    }

    override fun searchTextChanged(text: String?) {
        val filteredList = model.filterGeneralList(text)
        if (filteredList.isEmpty()) {
            view?.showSlidNothingFound(true)
        } else {
            view?.showSlidNothingFound(false)
        }
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

    private fun initDialogCreator(nameCity: String): DialogCreator =
        when (model.findInFavorites(nameCity)) {
            true -> DialogCreator(
                nameCity,
                R.string.message_favorite_city,
                R.string.text_button_remove,
                R.string.message_after_removal,
                R.string.button_cancel,
                this::removeCityFromFavorite,
                this::showMessageAfterPositiveClick
            )
            false -> DialogCreator(
                nameCity,
                R.string.message_unelected_city,
                R.string.text_button_add,
                R.string.message_after_adding,
                R.string.button_cancel,
                this::addCityToFavorite,
                this::showMessageAfterPositiveClick
            )
        }
}