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
    lateinit var model: CitiesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindView()
        val citiesList = getCitiesList()

        model = CitiesModel(citiesList)
        initList(citiesList)
        initListener()
    }

    private fun bindView() {
        rvCities = findViewById(R.id.recyclerView)
    }

    private fun initList(citiesList: List<String>) {
        rvCities?.layoutManager = LinearLayoutManager(this)
        adapter = CitiesAdapter(citiesList, this::onCityClick)
        rvCities?.adapter = adapter
    }

    private fun initListener() {
        svCity.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(enteredText: String?): Boolean {
                    val filteredText = model.filter(enteredText)
                    adapter?.updateCitiesList(filteredText)
                    return false
                }
            })
    }

    private fun getCitiesList(): List<String> =
        resources.getStringArray(R.array.cities).toList()

    private fun onCityClick(nameCity: String) {
        val res = resources
        val toastText = res.getString(R.string.toast_string, nameCity)
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
    }
}


