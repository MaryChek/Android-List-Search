package com.example.favorite_cities.presenter

import com.example.favorite_cities.CitiesFragmentsParams
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CollectionContract
import com.example.favorite_cities.model.CitiesModel

class CollectionPresenter(
    private val model: CitiesModel,
    citiesView: CollectionContract.View
) : BasePresenter<CollectionContract.View>(citiesView), CollectionContract.Presenter {
    override fun onViewCreated() {
        super.onViewCreated()
        view.showPagerWithFragments(getMapNames())
    }

    override fun fragmentsDisplayed() {
        if (model.getVisibleFavoriteFragment())
            view.setFavoriteFragmentAsCurrent()
        view.addPagerInTabLayout()
    }

    private fun getMapNames(): MutableMap<String, Int> =
        mutableMapOf(
            CitiesFragmentsParams.FAVORITE to R.string.name_fragment_favorite,
            CitiesFragmentsParams.GENERAL to R.string.name_fragment_general
        )
}