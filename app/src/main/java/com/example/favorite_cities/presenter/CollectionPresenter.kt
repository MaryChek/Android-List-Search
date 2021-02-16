package com.example.favorite_cities.presenter

import android.content.res.Resources
import com.example.favorite_cities.CitiesFragmentsParams
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CollectionContract
import com.example.favorite_cities.model.CitiesModel

class CollectionPresenter(
    private val model: CitiesModel,
    private val resources: Resources
) : BasePresenter<CollectionContract.View>(), CollectionContract.Presenter {
    override fun onViewCreated() {
        super.onViewCreated()
        view?.showPagerWithFragments(getMapNames())
    }

    override fun fragmentsDisplayed() {
        if (model.getVisibleFavoriteFragment())
            view?.setFavoriteFragmentAsCurrent()
        view?.addPagerInTabLayout()
    }

    private fun getMapNames(): MutableMap<String, CharSequence?> =
        mutableMapOf(
            CitiesFragmentsParams.FAVORITE to resources.getString(R.string.name_fragment_favorite),
            CitiesFragmentsParams.GENERAL to resources.getString(R.string.name_fragment_general)
        )
}