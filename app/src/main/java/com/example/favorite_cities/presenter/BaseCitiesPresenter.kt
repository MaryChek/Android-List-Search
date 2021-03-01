package com.example.favorite_cities.presenter

import androidx.annotation.VisibleForTesting
import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CitiesContract
import com.example.favorite_cities.model.CitiesModel

open class BaseCitiesPresenter<V : CitiesContract.View>(
    newView: V,
    newModel: CitiesModel,
    newDialogCreator: DialogCreator
) : BasePresenter<V>(newView), CitiesContract.Presenter<V> {

    private val dialogCreator: DialogCreator = newDialogCreator
    protected val model: CitiesModel = newModel

    override fun onViewCreated() {
        initDialogCreator()
    }

    override fun onDestroy() {
        dialogCreator.onDestroy()
    }

    override fun onFragmentVisible() {

    }

    override fun onCityClicked(nameCity: String) {
        dialogCreator.setTitle(nameCity)
        when (model.findInFavorites(nameCity)) {
            true -> view.showDialogRemoving()
            false -> view.showDialogAdding()
        }
    }

    override fun searchTextChanged(text: String?) {

    }

    override fun addFavoriteCity(nameCity: String) =
        model.addFavoriteCity(nameCity)

    override fun removeFavoriteCity(nameCity: String) =
        model.removeFavoriteCity(nameCity)

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    open fun afterPositiveClickInDialog(positiveButtonId: Int, nameCity: String?) {
        nameCity?.let {
            when (positiveButtonId) {
                R.string.text_button_add -> {
                    addFavoriteCity(it)
                    view.showToastWithText(R.string.message_after_adding, it)
                }
                R.string.text_button_remove -> {
                    removeFavoriteCity(it)
                    view.showToastWithText(R.string.message_after_removal, it)
                }
            }
        }
    }

    private fun initDialogCreator() {
        dialogCreator.setFunctionOnPositive(this::afterPositiveClickInDialog)
        dialogCreator.setMessageBeforeAdding(R.string.message_favorite_city)
        dialogCreator.setButtonRemoveTitle(R.string.text_button_remove)
        dialogCreator.setMessageBeforeRemoving(R.string.message_unelected_city)
        dialogCreator.setButtonAddTitle(R.string.text_button_add)
        dialogCreator.setNegativeButtonTitle(R.string.button_cancel)
    }
}