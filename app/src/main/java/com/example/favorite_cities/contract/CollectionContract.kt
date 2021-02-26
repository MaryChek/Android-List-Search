package com.example.favorite_cities.contract

interface CollectionContract {

    interface View : BaseContract.View {
        fun showPagerWithFragments(countFragment: Int)

        fun setFavoriteFragmentAsCurrent(currentFragmentPosition: Int)

        fun addTitlesInTabLayout(fragmentsNamesId: Array<Int>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onFragmentsShown()
    }
}