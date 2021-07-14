package com.example.favorite_cities.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.favorite_cities.presenter.ActivityPresenter
import com.example.favorite_cities.App
import com.example.favorite_cities.R
import com.example.favorite_cities.contract.ActivityContract
import com.example.favorite_cities.databinding.ActivityMainBinding
import com.example.favorite_cities.databinding.IconForActionBarBinding
import com.example.favorite_cities.view.fragments.PagerFragment

class MainActivity : AppCompatActivity(), ActivityContract.View {
    private var presenter: ActivityPresenter? = null
    private lateinit var pagerFragment: PagerFragment
    private var bindingIcon: IconForActionBarBinding? = null
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        createFragmentPager()
        val model = (this.applicationContext as App).themeModel
        presenter = ActivityPresenter(model, this)
        presenter?.onViewCreated()
    }

    private fun createFragmentPager() {
        pagerFragment = PagerFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.activityMain, pagerFragment).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item: MenuItem = menu.findItem(R.id.icon)
        bindingIcon = IconForActionBarBinding.bind(item.actionView)
        bindingIcon?.iconForActionBar?.setOnClickListener {
            presenter?.onActionBarIconClick()
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun changeThemeToDark() =
        setDefaultTheme(AppCompatDelegate.MODE_NIGHT_YES)

    override fun changeThemeToLight() =
        setDefaultTheme(AppCompatDelegate.MODE_NIGHT_NO)

    private fun setDefaultTheme(mode: Int) =
        AppCompatDelegate.setDefaultNightMode(mode)

    override fun onBackPressed() {
        if (pagerFragment.onBackPressed()) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingIcon = null
    }
}