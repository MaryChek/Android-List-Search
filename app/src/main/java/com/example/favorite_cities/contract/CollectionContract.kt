package com.example.favorite_cities.contract

import com.example.favorite_cities.FragmentKeys

interface CollectionContract {

    interface View : BaseContract.View {
        fun showPagerWithFragments(fragmentsNamesId: MutableMap<FragmentKeys, Int>)

        fun setFavoriteFragmentAsCurrent()

        fun addPagerInTabLayout()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun fragmentsDisplayed()
    }
}