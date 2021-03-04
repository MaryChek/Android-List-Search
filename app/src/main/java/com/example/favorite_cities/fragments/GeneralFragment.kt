package com.example.favorite_cities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.favorite_cities.*
import com.example.favorite_cities.contract.GeneralCitiesContract
import com.example.favorite_cities.presenter.GeneralPresenter

class GeneralFragment :
    BaseCitiesFragment<GeneralCitiesContract.View, GeneralCitiesContract.Presenter>(),
    GeneralCitiesContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = GeneralPresenter(this, model, dialogCreator)
    }
}