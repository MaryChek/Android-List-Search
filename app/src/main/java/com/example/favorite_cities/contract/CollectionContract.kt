package com.example.favorite_cities.contract

import androidx.annotation.StringRes

interface CollectionContract {

    interface View : BaseContract.View {
        fun showPagerWithFragments()

        fun setCurrentFragmentByPosition(position: Int)

        fun setTitlesInTabLayout(@StringRes tabTitleResIds: List<Int>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onFragmentsShown()

        fun onPageSelected(position: Int)
    }
}