package com.example.android_list_search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val frManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createFragmentGeneral()
    }

    private fun createFragmentGeneral() {
        val transaction = frManager.beginTransaction()
        val fragment = FragmentGeneral()
        transaction.replace(R.id.frPlace,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}


