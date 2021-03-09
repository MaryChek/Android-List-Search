package com.example.favorite_cities.presenter

import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CollectionContract
import com.example.favorite_cities.model.CitiesModel

class CollectionPresenter(
    private val model: CitiesModel,
    citiesView: CollectionContract.View
) : BasePresenter<CollectionContract.View>(citiesView), CollectionContract.Presenter {
    override fun onViewCreated() {
        view.selectItemOnPager(model.positionOfTheCurrentPage)
        view.setTitlesInTabLayout(getTabTitleResIds())
    }

    private fun getTabTitleResIds(): List<Int> =
        listOf(
            R.string.name_fragment_general,
            R.string.name_fragment_favorite
        )

    override fun onPageSelected(position: Int) {
        model.positionOfTheCurrentPage = position
    }
}