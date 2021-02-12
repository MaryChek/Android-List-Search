package com.example.favorite_cities.contract


interface BaseContract {
    interface View

    interface Presenter<V : View> {

        fun onAttachView(view: V)

        fun onDestroy()

        fun onViewCreated()
    }
}