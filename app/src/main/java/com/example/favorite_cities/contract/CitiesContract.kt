package com.example.favorite_cities.contract

import androidx.annotation.StringRes

interface CitiesContract {
    interface View : BaseContract.View {
        fun updateCitiesList(modifiedList: List<String>)

        fun showDialogAdding(nameCity: String)

        fun showDialogRemoving(nameCity: String)

        fun showEmptyListHint(@StringRes textId: Int)

        fun hideEmptyListHint()

        fun showUserMessage(@StringRes stringId: Int, nameCity: String)

        fun setEnteredText(text: CharSequence)
    }

    interface Presenter<V : View> : BaseContract.Presenter<V> {
        fun onTabVisible()

        fun onCityClicked(nameCity: String)

        fun onSearchTextChanged(text: String?)

        fun onAddButtonClick(nameCity: String)

        fun onRemoveButtonClick(nameCity: String)
    }
}