package com.example.favorite_cities.contract

import androidx.annotation.StringRes

interface CollectionContract {

    interface View : BaseContract.View {
        fun setCurrentPageByPosition(position: Int)

        fun setTitlesInTabLayout(@StringRes tabTitleResIds: List<Int>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onPageSelected(position: Int)
    }
}