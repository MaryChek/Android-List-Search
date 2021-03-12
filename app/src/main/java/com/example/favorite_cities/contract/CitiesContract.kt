package com.example.favorite_cities.contract

import androidx.annotation.StringRes
import com.example.favorite_cities.model.cities.CityIcon

interface CitiesContract {
    interface View : BaseContract.View {
        fun updateCitiesList(modifiedListCities: List<CityIcon>)

        fun showAddToFavoriteActionConfirmation(nameCity: String)

        fun showRemoveFromFavoriteActionConfirmation(nameCity: String)

        fun showEmptyListHint(@StringRes textId: Int)

        fun hideEmptyListHint()

        fun showUserMessage(@StringRes stringId: Int, nameCity: String)

        fun setEnteredText(text: CharSequence)
    }

    interface Presenter<V: View> : BaseContract.Presenter<V> {
//        fun onViewCreated()

        fun onTabVisible()

        fun onCityIconClicked(nameCity: String)

        fun onSearchTextChanged(text: String?)

        fun onAddToFavoriteClick(nameCity: String)

        fun onRemoveFromFavoriteClick(nameCity: String)

        fun onViewPause()
    }
}