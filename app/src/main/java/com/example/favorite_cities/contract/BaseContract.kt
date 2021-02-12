package com.example.favorite_cities.contract


interface BaseContract {
    interface View {
        fun init()
    }

    interface Presenter<V : View> {

        fun onAttachView(view: V)

        fun onDestroy()

        fun onViewCreated()
    }
}