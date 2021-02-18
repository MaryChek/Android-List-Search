package com.example.favorite_cities.contract

interface CollectionContract {

    interface View : BaseContract.View {
        fun showPagerWithFragments(countFragment: Int)

        fun setFavoriteFragmentAsCurrent()

        fun addPagerInTabLayout(fragmentsNamesId: MutableMap<Int, Int>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onFragmentsShown()
    }
}