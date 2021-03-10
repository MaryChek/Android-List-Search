package com.example.favorite_cities.contract

import androidx.annotation.StringRes
import com.example.favorite_cities.model.CityIcon

interface CitiesContract {
    interface View : BaseContract.View {
        fun updateCitiesList(modifiedListCities: List<CityIcon>)

        fun showDialogAdding(nameCity: String)

        fun showDialogRemoving(nameCity: String)

        fun showEmptyListHint(@StringRes textId: Int)

        fun hideEmptyListHint()

        fun showUserMessage(@StringRes stringId: Int, nameCity: String)

        fun setEnteredText(text: CharSequence)
    }

    interface Presenter<V: View> : BaseContract.Presenter<V> {
        fun onViewCreated()

        fun onTabVisible()

        fun onCityIconClicked(nameCity: String)

        fun onSearchTextChanged(text: String?)

        fun onAddButtonClick(nameCity: String)

        fun onRemoveButtonClick(nameCity: String)

        fun onViewPause()
    }
}