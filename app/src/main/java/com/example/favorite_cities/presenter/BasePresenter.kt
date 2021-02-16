package com.example.favorite_cities.presenter

import com.example.favorite_cities.contract.BaseContract

abstract class BasePresenter<V : BaseContract.View>(newView: V) :
    BaseContract.Presenter<V> {

    protected val view: V = newView

    override fun onViewCreated() {

    }

    override fun onDestroy() {

    }
}