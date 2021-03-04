package com.example.favorite_cities.presenter

import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.GeneralCitiesContract

class GeneralPresenter(
    citiesView: GeneralCitiesContract.View,
    citiesModel: CitiesModel,
    CitiesDialogCreator: DialogCreator
) : BaseCitiesPresenter<GeneralCitiesContract.View>(citiesView, citiesModel, CitiesDialogCreator),
    GeneralCitiesContract.Presenter {

    override fun getCitiesCollection(): CitiesKey =
        CitiesKey.GENERAL
}