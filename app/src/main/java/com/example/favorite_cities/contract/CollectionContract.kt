package com.example.favorite_cities.contract

interface CollectionContract {

    interface View : BaseContract.View {
        fun showPagerWithFragments(fragmentsNames: MutableMap<String, CharSequence?>)

        fun setFavoriteFragmentAsCurrent()

        fun addPagerInTabLayout()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun fragmentsDisplayed()
    }
}