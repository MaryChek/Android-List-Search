package com.example.favorite_cities.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.favorite_cities.*
import com.example.favorite_cities.contract.GeneralCitiesContract
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.presenter.GeneralPresenter
import kotlinx.android.synthetic.main.tv_search_error.*

class GeneralFragment :
    BaseCitiesFragment<GeneralCitiesContract.View, GeneralCitiesContract.Presenter>(),
    GeneralCitiesContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun init() {
        val activity: Activity = activity as MainActivity
        val app: App = activity.applicationContext as App
        val model: CitiesModel = app.model
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onViewCreated()
    }

    override fun showCitiesList(citiesList: List<String>) {
        initList(citiesList)
        initListener()
    }

    override fun updateCitiesList(modifiedList: List<String>) {
        adapter?.updateList(modifiedList)
    }

    override fun showDialog(
        title: String,
        messageId: Int,
        positiveButtonId: Int,
        negativeButtonId: Int
    ) = dialogCreator.show(
        activity, title, messageId, negativeButtonId, positiveButtonId
    )

    override fun showSlideNothingFound(show: Boolean) {
        val slide = tvSearchError
        if (show) {
            slide.visibility = View.VISIBLE
        } else {
            slide.visibility = View.GONE
        }
    }

    override fun showToastWithText(stringId: Int, nameCity: String) =
        Toast.makeText(
            activity, resources.getString(stringId, nameCity), Toast.LENGTH_LONG
        ).show()

    override fun setEnteredText(text: CharSequence) =
        svCity.setQuery(text, false)
}