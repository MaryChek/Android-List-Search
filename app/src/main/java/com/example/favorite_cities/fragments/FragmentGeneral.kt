package com.example.favorite_cities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite_cities.CitiesView
import com.example.favorite_cities.presenter.PresenterGeneral
import com.example.favorite_cities.R
import com.example.favorite_cities.presenter.PresenterCommon
import com.example.favorite_cities.adapter.CitiesAdapter

class FragmentGeneral(private val presenterCommon: PresenterCommon) : Fragment(), CitiesView {

    private var adapter: CitiesAdapter? = null
    private lateinit var presenterGeneral: PresenterGeneral
    private lateinit var rvGeneralCities: RecyclerView
    private lateinit var svCity: SearchView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenterGeneral = PresenterGeneral(presenterCommon)
        presenterGeneral.attachView(this)
        presenterGeneral.viewIsReady()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =
            inflater.inflate(R.layout.fragment_fragment_general, container, false)

        rvGeneralCities = rootView.findViewById(R.id.rvGeneralCities)
        svCity = rootView.findViewById(R.id.svCity)
        return rootView
    }

    override fun showCitiesList(citiesList: List<String>) {
        initList(citiesList)
        initListener()
    }

    override fun updateCitiesList(modifiedList: List<String>) {
        adapter?.updateList(modifiedList)
    }

    override fun showSlid(view: View) {
        if (view.visibility == View.GONE)
            view.visibility = View.VISIBLE
    }

    override fun hideSlid(view: View) {
        if (view.visibility == View.VISIBLE)
            view.visibility = View.GONE
    }

    override fun initList(citiesList: List<String>) {
        itemDecorate()
        adapter = CitiesAdapter(citiesList, this::onCityClicked)
        rvGeneralCities.adapter = adapter
    }

    override fun initListener() {
        svCity.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(enteredText: String?): Boolean {
                    presenterGeneral.searchTextChanged(enteredText)
                    return false
                }
            })
    }

    override fun itemDecorate() {
        val dividerItem = DividerItemDecoration(activity, RecyclerView.VERTICAL)
        rvGeneralCities.addItemDecoration(dividerItem)
    }

    override fun onCityClicked(nameCity: String) {
        presenterGeneral.onCityClicked(nameCity)
    }
}