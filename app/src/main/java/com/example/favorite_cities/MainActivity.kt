package com.example.favorite_cities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var pagerFragment: PagerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createFragmentPager()
        setContentView(R.layout.activity_main)
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