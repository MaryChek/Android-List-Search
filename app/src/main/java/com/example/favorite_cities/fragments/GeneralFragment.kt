package com.example.favorite_cities.fragments

import android.os.Bundle
import com.example.favorite_cities.contract.CitiesContract
import com.example.favorite_cities.presenter.GeneralPresenter

class GeneralFragment :
    BaseCitiesFragment<CitiesContract.Presenter<CitiesContract.View>>(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = GeneralPresenter(this, model)
    }
}