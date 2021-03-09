package com.example.favorite_cities.contract

interface BaseContract {
    interface View

    interface Presenter<V : View>
}