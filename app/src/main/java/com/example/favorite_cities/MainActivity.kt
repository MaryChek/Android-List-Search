package com.example.favorite_cities

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.MenuItemCompat
import com.example.favorite_cities.databinding.IconForActionBarBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.icon_for_action_bar.*


class MainActivity : AppCompatActivity() {

    private lateinit var pagerFragment: PagerFragment
    private var binding: IconForActionBarBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragmentPager()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item: MenuItem = menu.findItem(R.id.icon)
        binding = IconForActionBarBinding.bind(item.actionView)
        val moonButton: FloatingActionButton? = binding?.iconForActionBar
        moonButton?.setOnClickListener {
           changeLocalTheme()
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun changeLocalTheme() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                AppCompatDelegate.setDefaultNightMode (
                    AppCompatDelegate.MODE_NIGHT_YES)
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                AppCompatDelegate.setDefaultNightMode (
                    AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun createFragmentPager() {
        pagerFragment = PagerFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activityMain, pagerFragment).commit()
    }

    override fun onBackPressed() {
        if (pagerFragment.onBackPressed()) {
            super.onBackPressed()
        }
    }
}