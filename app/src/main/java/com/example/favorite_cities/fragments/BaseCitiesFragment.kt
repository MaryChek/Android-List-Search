package com.example.favorite_cities.fragments

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite_cities.*
import com.example.favorite_cities.adapter.CitiesAdapter
import com.example.favorite_cities.contract.CitiesContract
import com.example.favorite_cities.model.CitiesModel
import kotlinx.android.synthetic.main.tv_search_error.*

abstract class BaseCitiesFragment<V : CitiesContract.View, T : CitiesContract.Presenter<V>> :
    Fragment(), CitiesContract.View {
    protected var presenter: T? = null
    protected lateinit var rvCities: RecyclerView
    protected lateinit var svCity: SearchView
    protected val dialogCreator: DialogCreator = DialogCreator()
    protected lateinit var model: CitiesModel
    private var adapter: CitiesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity: Activity = activity as MainActivity
        val app: App = activity.applicationContext as App
        model = app.model
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            presenter?.onFragmentVisible()
        }
    }

    override fun showCitiesList(citiesList: List<String>) {
        initList(citiesList)
        initListener()
    }

    override fun updateCitiesList(modifiedList: List<String>) {
        adapter?.updateList(modifiedList)
    }

    override fun showDialogAdding() {
        dialogCreator.showDialogAdding(activity)
    }

    override fun showDialogRemoving() {
        dialogCreator.showDialogRemoving(activity)
    }

    override fun showSearchError(textId: Int) {
        val slide: TextView = tvSearchError
        slide.text = resources.getString(textId)
        slide.visibility = View.VISIBLE
    }

    override fun hideSearchError() {
        tvSearchError.visibility = View.GONE
    }

    override fun showToastWithText(stringId: Int, nameCity: String) =
        Toast.makeText(
            activity, resources.getString(stringId, nameCity), Toast.LENGTH_LONG
        ).show()

    override fun setEnteredText(text: CharSequence) =
        svCity.setQuery(text, false)

    private fun initList(citiesList: List<String>) {
        adapter = CitiesAdapter(citiesList, this::onCityClicked)
        rvCities.adapter = adapter
        itemDecorate()
    }

    private fun initListener() {
        svCity.setOnQueryTextListener(
            object : SimpleSearchTextListener() {
                override fun onQueryTextChange(enteredText: String?): Boolean {
                    presenter?.searchTextChanged(enteredText)
                    return false
                }
            })
    }

    private fun itemDecorate() {
        val dividerItem = DividerItemDecoration(activity, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_cities, context?.theme)?.let {
            dividerItem.setDrawable(it)
            rvCities.addItemDecoration(dividerItem)
        }
    }

    private fun onCityClicked(nameCity: String) {
        presenter?.onCityClicked(nameCity)
    }
}