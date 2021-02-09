package com.example.android_list_search.presenter

import com.example.android_list_search.CitiesModel
import com.example.android_list_search.R
import com.example.android_list_search.fragments.DialogFragmentAddRemoveOnFavorite
import com.example.android_list_search.fragments.FragmentGeneral
import kotlinx.android.synthetic.main.fragment_fragment_general.*
import com.example.android_list_search.CitiesView

class PresenterGeneral(private var presenterCommon: PresenterCommon) : PresenterMain {
    private lateinit var view: FragmentGeneral
    private lateinit var model: CitiesModel

    init {
        presenterCommon.attachPresenterGeneral(this)
    }

    override fun attachView(activity: CitiesView) {
        view = activity as FragmentGeneral
    }

    override fun viewIsReady() {
        val citiesList = getCitiesList()
        view.showCitiesList(citiesList)
        createCitiesModel(citiesList)
    }

    override fun searchTextChanged(text: String?) {
        val filteredList = model.filter(text)
        if (filteredList.isEmpty()) {
            view.showSlid(view.slideNothingFoundInGeneral)
        } else {
            view.hideSlid(view.slideNothingFoundInGeneral)
        }
        view.updateCitiesList(filteredList)
    }

    fun onCityClicked(nameCity: String) {
        val dialog = DialogFragmentAddRemoveOnFavorite(this, nameCity)
        val manager = view.activity!!.supportFragmentManager
        dialog.show(manager, "myDialog")
    }

    override fun findCityInFavoriteModel(nameCity: String): Boolean =
        presenterCommon.findCityInFavoriteModel(nameCity)

    override fun getResourceString(id: Int, vararg formatArgs: Any?): String =
        view.resources.getString(id, *formatArgs)

    private fun createCitiesModel(citiesList: List<String>) {
        model = CitiesModel(citiesList)
    }

    override fun addCityToFavorite(nameCity: String) {
        presenterCommon.addCityToFavorite(nameCity)
    }

    override fun removeCityFromFavorite(nameCity: String) {
        presenterCommon.removeCityToFavorite(nameCity)
    }

    private fun getCitiesList(): List<String> =
        view.resources.getStringArray(R.array.cities).toList()
}