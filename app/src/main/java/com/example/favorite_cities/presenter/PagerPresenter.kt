package com.example.favorite_cities.presenter

import com.example.favorite_cities.R
import com.example.favorite_cities.contract.PagerContract
import com.example.favorite_cities.model.cities.CitiesModel

class PagerPresenter(
    private val model: CitiesModel,
    citiesView: PagerContract.View
) : BasePresenter<PagerContract.View>(citiesView), PagerContract.Presenter {

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