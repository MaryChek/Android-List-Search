package com.example.favorite_cities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.favorite_cities.fragments.CollectionFragment
import com.example.favorite_cities.model.Model

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Model.onCreate(this.applicationContext)
        val app = this.applicationContext as App
        app.setListToModel(resources.getStringArray(R.array.cities).toList())
        createFragmentGeneral()
        setContentView(R.layout.activity_main)
    }

    private fun createFragmentGeneral() {
        val frManager = supportFragmentManager
        val transaction = frManager.beginTransaction()
        val collectionFragment = CollectionFragment()
        transaction.replace(R.id.frPlace, collectionFragment)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

