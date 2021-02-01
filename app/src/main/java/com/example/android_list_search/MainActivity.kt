package com.example.android_list_search

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_list_search.аdapter.CitiesAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var rvCities: RecyclerView? = null
    private var adapter: CitiesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindView()

        val citiesList = getCitiesList()

        initList(citiesList)

        val citiesModel = CitiesModel(citiesList)

        initListener(citiesModel)
    }

    private fun bindView() {
        rvCities = findViewById(R.id.recyclerView)
        rvCities?.layoutManager = LinearLayoutManager(this)
    }

    private fun initList(citiesList: List<String>) {
        adapter = CitiesAdapter(citiesList, this::onCityClick)
        rvCities?.adapter = adapter
    }


    private fun initListener(citiesModel: CitiesModel) {
        searchView2.setOnQueryTextListener(
            object: SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter?.updateCitiesList(citiesModel.filter(newText))
                    return false
                }
            })
    }

    private fun getCitiesList(): List<String> =
        resources.getStringArray(R.array.cities).toList()

    private fun onCityClick(string: String) {
        Toast.makeText(
            this,
            resources.getString(R.string.toast_string) + " " + string,
            Toast.LENGTH_SHORT).show()
    }
}


