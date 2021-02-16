package com.example.favorite_cities.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.favorite_cities.*
import com.example.favorite_cities.presenter.FavoritePresenter
import com.example.favorite_cities.contract.FavoriteCitiesContract
import com.example.favorite_cities.model.CitiesModel
import kotlinx.android.synthetic.main.tv_search_error.*

class FavoritesFragment :
    BaseCitiesFragment<FavoriteCitiesContract.View, FavoriteCitiesContract.Presenter>(),
    FavoriteCitiesContract.View {

    private lateinit var textSlide: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun init() {
        val activity: Activity = activity as MainActivity
        val app: App = activity.applicationContext as App
        val model: CitiesModel = app.model
        presenter = FavoritePresenter(model)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(
            R.layout.fragment_fragment_favorites, container, false
        )
        rvCities = rootView.findViewById(R.id.rvFavoriteCities)
        svCity = rootView.findViewById(R.id.svCity)
        textSlide = rootView.findViewById(R.id.tvSearchError)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onAttachView(this)
        presenter?.onViewCreated()
    }

    override fun showCitiesList(citiesList: List<String>) {
        initList(citiesList)
        initListener()
    }

    override fun showDialogFragment(dialogCreator: DialogCreator?) {
        dialogCreator?.show(activity)
    }

    override fun updateCitiesList(modifiedList: List<String>) {
        adapter?.updateList(modifiedList)
    }

    override fun showTextSlide(idText: Int, show: Boolean) {
        val slide: TextView = tvSearchError
        if (show) {
            slide.text = resources.getString(idText)
            slide.visibility = View.VISIBLE
        } else {
            slide.visibility = View.GONE
        }
    }

    override fun setEnteredText(text: CharSequence) {
        svCity.setQuery(text, false)
    }

    override fun showMessageAfterPositiveClick(stringId: Int, nameCity: String): String =
        resources.getString(stringId, nameCity)
}

