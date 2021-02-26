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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(
            R.layout.fragment_fragment_general, container, false
        )
        rvCities = rootView.findViewById(R.id.rvGeneralCities)
        svCity = rootView.findViewById(R.id.svCity)
        return rootView
    }
}