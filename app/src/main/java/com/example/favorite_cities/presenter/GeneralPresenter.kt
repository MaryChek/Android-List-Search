package com.example.favorite_cities.presenter

import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.FavoriteCitiesContract
import com.example.favorite_cities.contract.GeneralCitiesContract

open class GeneralPresenter(
    citiesView: GeneralCitiesContract.View,
    citiesModel: CitiesModel,
    CitiesDialogCreator: DialogCreator
) : BaseCitiesPresenter<GeneralCitiesContract.View>(citiesView, citiesModel, CitiesDialogCreator),
    GeneralCitiesContract.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()
        model.getGeneralEnteredText()?.let {
            view.setEnteredText(it)
        }
        view.showCitiesList(model.getGeneralCitiesFiltered())
    }

    override fun onFragmentVisible() {
        model.setVisibleFavoriteFragment(false)
    }

    override fun searchTextChanged(text: String?) {
        model.filterGeneralList(text)
        showOrHideSearchError()
        view.updateCitiesList(model.getGeneralCitiesFiltered())
    }

    private fun showOrHideSearchError() =
        when (model.isGeneralCitiesFilteredEmpty()) {
            true -> view.showSearchError(R.string.nothing_found)
            false -> view.hideSearchError()
        }
}