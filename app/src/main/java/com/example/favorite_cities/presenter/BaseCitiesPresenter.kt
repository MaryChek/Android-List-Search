package com.example.favorite_cities.presenter

import com.example.favorite_cities.DialogCreator
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.CitiesContract
import com.example.favorite_cities.model.CitiesModel

abstract class BaseCitiesPresenter<V : CitiesContract.View>(
    newView: V,
    newModel: CitiesModel,
    newDialogCreator: DialogCreator
) : BasePresenter<V>(newView), CitiesContract.Presenter<V> {

    private val dialogCreator: DialogCreator = newDialogCreator
    protected val model: CitiesModel = newModel

    override fun onViewCreated() {
        initDialogCreator()
        model.getGeneralEnteredText()?.let {
            view.setEnteredText(it)
        }
    }

    override fun onDestroy() {
        dialogCreator.onDestroy()
    }

    override fun onFragmentVisible() {

    }

    override fun onCityClicked(nameCity: String) =
        showDialogWithParameters(nameCity)

    override fun searchTextChanged(text: String?) {

    }

    override fun addFavoriteCity(nameCity: String) =
        model.addFavoriteCity(nameCity)

    override fun removeFavoriteCity(nameCity: String) =
        model.removeFavoriteCity(nameCity)

    private fun showDialogWithParameters(nameCity: String) {
        val negativeButtonId: Int = R.string.button_cancel

        when (model.findInFavorites(nameCity)) {
            true -> view.showDialog(
                nameCity,
                R.string.message_favorite_city,
                negativeButtonId,
                R.string.text_button_remove
            )
            false -> view.showDialog(
                nameCity,
                R.string.message_unelected_city,
                negativeButtonId,
                R.string.text_button_add
            )
        }
    }

    private fun afterPositiveClickInDialog(positiveButtonId: Int, nameCity: String) {
        when (positiveButtonId) {
            R.string.text_button_add -> {
                addFavoriteCity(nameCity)
                view.showToastWithText(R.string.message_after_adding, nameCity)
            }
            R.string.text_button_remove -> {
                removeFavoriteCity(nameCity)
                view.showToastWithText(R.string.message_after_removal, nameCity)
            }
        }
    }

    private fun initDialogCreator() =
        dialogCreator.setFunctionOnPositive(this::afterPositiveClickInDialog)
}