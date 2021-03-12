package com.example.favorite_cities.presenter

import com.example.favorite_cities.model.cities.CitiesModel
import com.example.favorite_cities.contract.CitiesContract

class GeneralPresenter(
    citiesView: CitiesContract.View,
    citiesModel: CitiesModel
) : BaseCitiesPresenter<CitiesContract.View>(citiesView, citiesModel) {

    override fun getCitiesCollection(): CitiesKey =
        CitiesKey.GENERAL
}