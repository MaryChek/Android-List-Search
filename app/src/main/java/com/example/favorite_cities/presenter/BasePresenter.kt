package com.example.favorite_cities.presenter

import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.contract.BaseContract

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {
    protected var view: V? = null
    protected var dialogCreator: DialogCreator? = null

    override fun onAttachView(view: V) {
        this.view = view
    }

    override fun onViewCreated() {

    }

    override fun onDestroy() {
        view = null
        dialogCreator?.onDestroy()
    }
}