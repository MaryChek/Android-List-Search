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

    private fun getFragmentsNamesId(): MutableMap<Int, Int> =
        mutableMapOf(
            FIRST to R.string.name_fragment_general,
            SECOND to R.string.name_fragment_favorite
        )

    companion object {
        private const val COUNT_FRAGMENTS = 2
        private const val FIRST = 0
        private const val SECOND = 1
        const val FAVORITE_FRAGMENT_POSITION = 1
    }
}