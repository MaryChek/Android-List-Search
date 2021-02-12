package com.example.favorite_cities.presenter

import com.example.favorite_cities.contract.CollectionContract
import com.example.favorite_cities.model.CitiesModel

class CollectionPresenter(private val model: CitiesModel) :
    BasePresenter<CollectionContract.View>(), CollectionContract.Presenter {
    override fun onViewCreated() {
        super.onViewCreated()
        val mapFragmentsNames = getMapNames()
        view?.showPagerWithFragments(mapFragmentsNames)
    }

    override fun fragmentsDisplayed() {
        if (model.getVisibleFavoriteFragment())
            view?.setFavoriteFragmentAsCurrent()
        view?.addPagerInTabLayout()
    }

    private fun getMapNames(): MutableMap<String, CharSequence?> =
        mutableMapOf(
            "Favorite" to view?.getFavoriteFragmentNameId(),
            "General" to view?.getGeneralFragmentNameId()
        )

}