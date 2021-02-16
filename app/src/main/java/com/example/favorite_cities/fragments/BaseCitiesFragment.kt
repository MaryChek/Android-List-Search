package com.example.favorite_cities.fragments

import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite_cities.CustomCitiesSearchView
import com.example.favorite_cities.adapter.CitiesAdapter
import com.example.favorite_cities.contract.CitiesContract

abstract class BaseCitiesFragment<V : CitiesContract.View, T : CitiesContract.Presenter<V>> :
    Fragment() {
    protected open lateinit var rvCities: RecyclerView
    protected lateinit var svCity: SearchView
    protected var adapter: CitiesAdapter? = null
    protected var presenter: T? = null

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            presenter?.onFragmentVisible()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    protected open fun init() {

    }

    protected fun initList(citiesList: List<String>) {
        itemDecorate()
        adapter = CitiesAdapter(citiesList, this::onCityClicked)
        rvCities.adapter = adapter
    }

    protected fun initListener() {
        svCity.setOnQueryTextListener(
            object : CustomCitiesSearchView() {
                override fun onQueryTextChange(enteredText: String?): Boolean {
                    presenter?.searchTextChanged(enteredText)
                    return false
                }
            })
    }

    private fun itemDecorate() {
        val dividerItem = DividerItemDecoration(activity, RecyclerView.VERTICAL)
        rvCities.addItemDecoration(dividerItem)
    }

    private fun onCityClicked(nameCity: String) {
        presenter?.onCityClicked(nameCity)
    }
}