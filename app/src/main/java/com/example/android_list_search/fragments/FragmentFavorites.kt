package com.example.android_list_search.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.android_list_search.CitiesView
import com.example.android_list_search.R
import com.example.android_list_search.presenter.PresenterCommon
import com.example.android_list_search.presenter.PresenterFavorite
import com.example.android_list_search.sharedpreferences.PreferenceManager
import com.example.android_list_search.аdapter.CitiesAdapter

class FragmentFavorites(private val presenterCommon: PresenterCommon) : Fragment(), CitiesView {
    private var adapter: CitiesAdapter? = null
    private lateinit var presenterFavorite: PresenterFavorite
    private lateinit var rvFavoriteCities: RecyclerView
    private lateinit var svCity: SearchView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val preferenceManager = PreferenceManager(activity?.applicationContext)
        presenterFavorite = PresenterFavorite(presenterCommon, preferenceManager)
        presenterFavorite.attachView(this)
        presenterFavorite.viewIsReady()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.fragment_fragment_favorites, container, false
        )

        rvFavoriteCities = rootView.findViewById(R.id.rvFavoriteCities)
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

    private fun initList(citiesList: List<String>) {
        itemDecorate()
        adapter = CitiesAdapter(citiesList, this::onCityClicked)
        rvFavoriteCities.adapter = adapter
    }

    private fun initListener() {
        svCity.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(enteredText: String?): Boolean {
                    presenterFavorite.searchTextChanged(enteredText)
                    return false
                }
            })
    }

    private fun itemDecorate() {
        val dividerItem = DividerItemDecoration(activity, RecyclerView.VERTICAL)
        rvFavoriteCities.addItemDecoration(dividerItem)
    }

    private fun onCityClicked(nameCity: String) {
        presenterFavorite.onCityClicked(nameCity)
    }
}
