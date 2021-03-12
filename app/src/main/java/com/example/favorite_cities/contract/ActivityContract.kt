package com.example.favorite_cities.contract

interface ActivityContract {
    interface View : BaseContract.View {
        fun changeThemeToDark()

        fun changeThemeToLight()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onActionBarIconClick()
    }
}