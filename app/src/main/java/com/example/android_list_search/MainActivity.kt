package com.example.android_list_search

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_list_search.Adapter.CitiesAdapter
import com.example.android_list_search.Adapter.CitiesModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val citiesModel = CitiesModel(getCitiesList())
        searchOnQueryText(citiesModel)
        adapter = CitiesAdapter(citiesModel.filterList, this::onClick)
        recyclerView.adapter = adapter
    }

    private fun searchOnQueryText(citiesModel: CitiesModel) {
        searchView2.setOnQueryTextListener(
            object: SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.updateCitiesList(citiesModel.filter(newText))
                    return false
                }
            })
    }

    private fun getCitiesList(): ArrayList<String> {
        val arrayListCities = ArrayList<String>()
        val arrayCities = resources.getStringArray(R.array.cities)

        arrayCities.forEach {
            arrayListCities.add(it)
        }
        return arrayListCities
    }

    private fun onClick(string: String) {
        Toast.makeText(
            this,
            resources.getString(R.string.toast_string) + " " + string,
            Toast.LENGTH_SHORT).show()
    }
}


