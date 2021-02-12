package com.example.favorite_cities.contract

interface CollectionContract {
    interface View: BaseContract.View {
        fun showPagerWithFragments(fragmentsNames: MutableMap<String, CharSequence?>)

        fun setFavoriteFragmentAsCurrent()

        fun getFavoriteFragmentNameId(): CharSequence

        fun getGeneralFragmentNameId(): CharSequence

        fun addPagerInTabLayout()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun fragmentsDisplayed()
    }
}