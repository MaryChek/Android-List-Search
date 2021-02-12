package com.example.favorite_cities.presenter

import com.example.favorite_cities.contract.BaseContract

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {
    protected var view: V? = null

    override fun onAttachView(view: V) {
        this.view = view
    }

    override fun onViewCreated() {

    }

    override fun onDestroy() {
        view = null
    }
}