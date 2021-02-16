package com.example.favorite_cities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.favorite_cities.fragments.CollectionFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createFragmentGeneral()
        setContentView(R.layout.activity_main)
    }

    private fun createFragmentGeneral() {
        val fragmentManager = supportFragmentManager
        val collectionFragment = CollectionFragment()
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, collectionFragment)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }
}