package com.example.favorite_cities.presenter

import com.example.favorite_cities.contract.ActivityContract
import com.example.favorite_cities.model.theme.Theme
import com.example.favorite_cities.model.theme.ThemeModel

class ActivityPresenter(
    private val model: ThemeModel,
    view: ActivityContract.View
) : BasePresenter<ActivityContract.View>(view), ActivityContract.Presenter {

    override fun onViewCreated() {
        setTheme(model.theme)
    }

    override fun onActionBarIconClick() {
        changeTheme(model.theme)
    }

    private fun setTheme(theme: Theme) =
        when (theme) {
            Theme.LIGHT -> view.changeThemeToLight()
            Theme.DARK -> view.changeThemeToDark()
        }

    private fun changeTheme(theme: Theme?) {
        when (theme) {
            Theme.LIGHT -> {
                view.changeThemeToDark()
                model.theme = Theme.DARK
            }
            Theme.DARK -> {
                view.changeThemeToLight()
                model.theme = Theme.LIGHT
            }
        }
        model.saveTheme()
    }
}