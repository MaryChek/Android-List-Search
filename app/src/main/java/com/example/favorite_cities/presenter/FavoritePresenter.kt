package com.example.favorite_cities.presenter

import com.example.favorite_cities.contract.FavoriteCitiesContract
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R

class FavoritePresenter(
    citiesView: FavoriteCitiesContract.View,
    citiesModel: CitiesModel,
    CitiesDialogCreator: DialogCreator
) : BaseCitiesPresenter<FavoriteCitiesContract.View>(citiesView, citiesModel, CitiesDialogCreator),
    FavoriteCitiesContract.Presenter {

    override fun getCitiesCollection(): CitiesKey =
        CitiesKey.FAVORITE
}