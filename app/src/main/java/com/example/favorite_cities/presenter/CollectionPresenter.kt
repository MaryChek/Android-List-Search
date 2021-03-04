package com.example.favorite_cities.presenter

import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CollectionContract
import com.example.favorite_cities.model.CitiesModel

class CollectionPresenter(
    private val model: CitiesModel,
    citiesView: CollectionContract.View
) : BasePresenter<CollectionContract.View>(citiesView), CollectionContract.Presenter {
    override fun onViewCreated() {
        view.showPagerWithFragments()
    }

    override fun onFragmentsShown() {
        view.setCurrentFragmentByPosition(model.positionOfTheCurrentFragment)
        view.setTitlesInTabLayout(getTabTitleResIds())
    }

    override fun onPageSelected(position: Int) {
        model.positionOfTheCurrentFragment = position
    }

    private fun getTabTitleResIds(): List<Int> =
        listOf(
            R.string.name_fragment_general,
            R.string.name_fragment_favorite
        )
}