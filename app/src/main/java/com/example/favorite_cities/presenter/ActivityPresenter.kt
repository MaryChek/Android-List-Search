package com.example.favorite_cities.presenter

import com.example.favorite_cities.contract.ActivityContract
import com.example.favorite_cities.model.theme.Theme
import com.example.favorite_cities.model.theme.ThemeModel

class ActivityPresenter(
    private val model: ThemeModel,
    view: ActivityContract.View
) : BasePresenter<ActivityContract.View>(view), ActivityContract.Presenter {

    override fun onViewCreated() =
        setTheme()

    override fun onActionBarIconClick() {
        changeThemeInModel()
        setTheme()
    }

    private fun setTheme() =
        when (model.theme) {
            Theme.LIGHT -> view.changeThemeToLight()
            Theme.DARK -> view.changeThemeToDark()
        }

    private fun changeThemeInModel() {
        when (model.theme) {
            Theme.LIGHT -> model.theme = Theme.DARK
            Theme.DARK -> model.theme = Theme.LIGHT
        }
        model.saveTheme()
    }
}