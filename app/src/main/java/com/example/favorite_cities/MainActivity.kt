package com.example.favorite_cities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.favorite_cities.fragments.CollectionFragment

class MainActivity : AppCompatActivity() {
    private val frManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragmentGeneral()
    }

    private fun createFragmentGeneral() {
        val transaction = frManager.beginTransaction()
        val collectionFragment = CollectionFragment()
        transaction.replace(R.id.frPlace, collectionFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}


