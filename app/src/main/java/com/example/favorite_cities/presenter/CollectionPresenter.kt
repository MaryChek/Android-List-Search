package com.example.favorite_cities.presenter

import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CollectionContract
import com.example.favorite_cities.model.CitiesModel

class CollectionPresenter(
    private val model: CitiesModel,
    citiesView: CollectionContract.View
) : BasePresenter<CollectionContract.View>(citiesView), CollectionContract.Presenter {
    override fun onViewCreated() {
        view.showPagerWithFragments(COUNT_FRAGMENTS)
    }

    override fun onFragmentsShown() {
        if (model.getVisibleFavoriteFragment())
            view.setFavoriteFragmentAsCurrent(FAVORITE_FRAGMENT_POSITION)
        view.addTitlesInTabLayout(getFragmentsNamesId())
    }

    private fun getFragmentsNamesId(): Array<Int> =
        arrayOf(
            R.string.name_fragment_general,
            R.string.name_fragment_favorite
        )

    companion object {
        private const val COUNT_FRAGMENTS = 2
        const val FAVORITE_FRAGMENT_POSITION = 1
    }
}