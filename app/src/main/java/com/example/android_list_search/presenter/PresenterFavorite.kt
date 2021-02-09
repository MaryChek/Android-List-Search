package com.example.android_list_search.presenter

import com.example.android_list_search.CitiesModel
import com.example.android_list_search.CitiesView
import com.example.android_list_search.fragments.DialogFragmentAddRemoveOnFavorite
import com.example.android_list_search.fragments.FragmentFavorites
import com.example.android_list_search.sharedpreferences.PreferenceManager
import kotlinx.android.synthetic.main.fragment_fragment_favorites.*

class PresenterFavorite(
    presenterCommon: PresenterCommon,
    private var preferencesManager: PreferenceManager
) : PresenterMain {
    private lateinit var view: FragmentFavorites
    private lateinit var model: CitiesModel

    init {
        presenterCommon.attachPresenterFavorite(this)
    }

    override fun attachView(activity: CitiesView) {
        view = activity as FragmentFavorites
    }

    override fun viewIsReady() {
        val citiesList = preferencesManager.getList()
        if (citiesList.isNotEmpty()) {
            view.hideSlid(view.slideNoFavoriteCities)
        }
        view.showCitiesList(citiesList)
        createCitiesModel(citiesList)
    }

    override fun searchTextChanged(text: String?) {
        if (model.getListCities().isNotEmpty()) {
            val filteredList = model.filter(text)
            if (filteredList.isEmpty()) {
                view.showSlid(view.slideNothingFoundInFavorite)
            } else {
                view.hideSlid(view.slideNothingFoundInFavorite)
            }
            view.updateCitiesList(filteredList)
        }
    }

    override fun getResourceString(id: Int, vararg formatArgs: Any?): String =
        view.resources.getString(id, *formatArgs)

    fun onCityClicked(nameCity: String) {
        val dialog = DialogFragmentAddRemoveOnFavorite(this, nameCity)
        val manager = view.activity!!.supportFragmentManager
        dialog.show(manager, "Dialog")
    }

    override fun addCityToFavorite(nameCity: String) {
        if (model.getListCities().isEmpty()) {
            view.hideSlid(view.slideNoFavoriteCities)
        }
        val newList = model.addCity(nameCity)
        view.updateCitiesList(newList)
        preferencesManager.setList(newList)
    }

    override fun removeCityFromFavorite(nameCity: String) {
        val newList = model.removeCity(nameCity)
        if (model.getListCities().isEmpty())
            view.showSlid(view.slideNoFavoriteCities)
        view.updateCitiesList(newList)
        preferencesManager.setList(newList)
    }

    override fun findCityInFavoriteModel(nameCity: String): Boolean =
        model.find(nameCity)

    private fun createCitiesModel(citiesList: List<String>) {
        model = CitiesModel(citiesList)
    }
}