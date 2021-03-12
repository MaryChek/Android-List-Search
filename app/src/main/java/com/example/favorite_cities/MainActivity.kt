package com.example.favorite_cities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.favorite_cities.contract.ActivityContract
import com.example.favorite_cities.databinding.IconForActionBarBinding
import com.example.favorite_cities.fragments.PagerFragment

class MainActivity : AppCompatActivity(), ActivityContract.View {
    private var presenter: ActivityPresenter? = null
    private lateinit var pagerFragment: PagerFragment
    private var binding: IconForActionBarBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragmentPager()
        val model = (this.applicationContext as App).themeModel
        presenter = ActivityPresenter(model, this)
        presenter?.onViewCreated()
    }

    private fun createFragmentPager() {
        pagerFragment = PagerFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activityMain, pagerFragment).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item: MenuItem = menu.findItem(R.id.icon)
        binding = IconForActionBarBinding.bind(item.actionView)
        binding?.iconForActionBar?.setOnClickListener {
            presenter?.onActionBarIconClick()
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun changeThemeToDark() =
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

    override fun changeThemeToLight() =
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    override fun onBackPressed() {
        if (pagerFragment.onBackPressed()) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}