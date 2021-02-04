package com.example.android_list_search

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.android_list_search.аdapter.CitiesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CitiesView {
    private var adapter: CitiesAdapter? = null
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter()
        presenter.attachView(this)
        presenter.viewIsReady()
    }

    override fun showCitiesList(citiesList: List<String>) {
        initList(citiesList)
        initListener()
    }

    override fun updateCitiesList(filteredList: List<String>) {
        adapter?.updateList(filteredList)
    }

    override fun showSlidedNothingFound() {
        slideNothingFound.visibility = View.VISIBLE
    }

    override fun hideSlidedNothingFound() {
        slideNothingFound.visibility = View.GONE
    }

    private fun initList(citiesList: List<String>) {
        val rvCities: RecyclerView = findViewById(R.id.rvCities)
        itemDecorate(rvCities)
        adapter = CitiesAdapter(citiesList, this::onCityClicked)
        rvCities.adapter = adapter
    }

    private fun itemDecorate(rvCities: RecyclerView) {
        val dividerItem = DividerItemDecoration(this, VERTICAL)
        rvCities.addItemDecoration(dividerItem)
    }

    private fun initListener() {
        svCity.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(enteredText: String?): Boolean {
                    presenter.searchTextChanged(enteredText)
                    return false
                }
            })
    }

    private fun onCityClicked(nameCity: String) {
        presenter.onCityClicked(nameCity)
    }
}


