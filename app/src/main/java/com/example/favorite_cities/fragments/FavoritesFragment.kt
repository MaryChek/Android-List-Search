package com.example.favorite_cities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.favorite_cities.*
import com.example.favorite_cities.presenter.FavoritePresenter
import com.example.favorite_cities.contract.FavoriteCitiesContract

class FavoritesFragment :
    BaseCitiesFragment<FavoriteCitiesContract.View, FavoriteCitiesContract.Presenter>(),
    FavoriteCitiesContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = FavoritePresenter(this, model, dialogCreator)
    }
}