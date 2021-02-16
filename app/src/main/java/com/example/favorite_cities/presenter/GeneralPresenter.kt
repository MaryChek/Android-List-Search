package com.example.favorite_cities.presenter

import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.GeneralCitiesContract

class GeneralPresenter(
    citiesView: GeneralCitiesContract.View,
    private var model: CitiesModel,
    private val dialogCreator: DialogCreator
) : BasePresenter<GeneralCitiesContract.View>(citiesView),
    GeneralCitiesContract.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()
        val enteredText: String? = model.getEnteredTextInGeneral()
        val filteredCitiesList: List<String> =
            when (enteredText.isNullOrEmpty()) {
                true -> model.getGeneralCities()
                false -> model.getFilteredGeneralCities()
            } // getfilter... model решит, какой показать
        view.showCitiesList(filteredCitiesList)
        enteredText?.let {
            view.setEnteredText(it)
        }
        initDialogCreator()
    }

    override fun onDestroy() {
        super.onDestroy()
        dialogCreator.onDestroy()
    }

    override fun onFragmentVisible() =
        model.setVisibleFavoriteFragment(false)

    override fun searchTextChanged(text: String?) {
        val filteredCitiesList: List<String> = model.filterGeneralList(text)
        showOrHideSlideNothingFound()
        view.updateCitiesList(filteredCitiesList)
    }

    override fun onCityClicked(nameCity: String) =
        showDialogWithParameters(nameCity)

    override fun addFavoriteCity(nameCity: String) =
        model.addFavoriteCity(nameCity)

    override fun removeFavoriteCity(nameCity: String) =
        model.removeFavoriteCity(nameCity)

    override fun afterPositiveClickInDialog(positiveButtonId: Int, nameCity: String) {
        if (positiveButtonId == R.string.text_button_add) {
            addFavoriteCity(nameCity)
            view.showToastWithText(R.string.message_after_adding, nameCity)
        } else {
            removeFavoriteCity(nameCity)
            view.showToastWithText(R.string.message_after_removal, nameCity)
        }
    }

    private fun initDialogCreator() =
        dialogCreator.setFunctionOnPositive(this::afterPositiveClickInDialog)

    private fun showOrHideSlideNothingFound() =
        if (model.getFilteredGeneralCities().isEmpty()) {
            view.showSlideNothingFound(true)
        } else {
            view.showSlideNothingFound(false)
        }

    private fun showDialogWithParameters(nameCity: String) {
        val negativeButtonId: Int = R.string.button_cancel

        if (model.findInFavorites(nameCity)) {
            view.showDialog(
                nameCity,
                R.string.message_favorite_city,
                negativeButtonId,
                R.string.text_button_remove
            )
        } else {
            view.showDialog(
                nameCity,
                R.string.message_unelected_city,
                negativeButtonId,
                R.string.text_button_add
            )
        }
    }
}