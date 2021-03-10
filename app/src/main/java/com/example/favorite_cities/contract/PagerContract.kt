package com.example.favorite_cities.contract

import androidx.annotation.StringRes

interface PagerContract {

    interface View : BaseContract.View {
        fun selectItemOnPager(position: Int)

        fun setTitlesInTabLayout(@StringRes tabTitleResIds: List<Int>)
    }

    interface Presenter : BaseContract.Presenter<View> {
//        fun onActivityStart()

        fun onPageSelected(position: Int)
    }
}