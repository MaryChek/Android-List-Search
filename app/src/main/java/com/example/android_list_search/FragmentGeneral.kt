package com.example.android_list_search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_list_search.аdapter.CitiesAdapter
import kotlinx.android.synthetic.main.fragment_fragment_general.*

class FragmentGeneral : Fragment() {

    private var rvCities: RecyclerView? = null
    private var adapter: CitiesAdapter? = null
    lateinit var model: CitiesModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val citiesList = getCitiesList()

        model = CitiesModel(citiesList)
        initList(citiesList)
        initListener()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_fragment_general, container, false)

        rvCities = rootView.findViewById(R.id.rvCities)

        return rootView
    }

    private fun initList(citiesList: List<String>) {
        rvCities?.layoutManager = LinearLayoutManager(activity)
        adapter = CitiesAdapter(citiesList, this::onCityClicked)
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

    private fun onCityClicked(nameCity: String) {
        val toastText = resources.getString(R.string.message_selected_city, nameCity)
        Toast.makeText(activity, toastText, Toast.LENGTH_SHORT).show()
    }
}
