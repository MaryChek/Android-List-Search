package com.example.favorite_cities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite_cities.*
import com.example.favorite_cities.adapter.CitiesAdapter
import com.example.favorite_cities.contract.GeneralCitiesContract
import com.example.favorite_cities.presenter.GeneralPresenter
import kotlinx.android.synthetic.main.fragment_fragment_general.*

class GeneralFragment : Fragment(), GeneralCitiesContract.View {

    private var adapter: CitiesAdapter? = null
    private lateinit var presenter: GeneralPresenter
    private lateinit var rvGeneralCities: RecyclerView
    private lateinit var svCity: SearchView
    private var manager = ResourceManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_fragment_general, container, false)
        rvGeneralCities = rootView.findViewById(R.id.rvGeneralCities)
        svCity = rootView.findViewById(R.id.svCity)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttachView(this)
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showCitiesList(citiesList: List<String>) {
        initList(citiesList)
        initListener()
    }

    override fun showDialogFragment(dialogCreator: DialogCreator) {
        dialogCreator.show(activity)
    }

    override fun updateCitiesList(modifiedList: List<String>) {
        adapter?.updateList(modifiedList)
    }

    override fun showSlidNothingFound(show: Boolean) {
        val slide = slideNothingFoundInGeneral
        if (show) {
            if (slide.visibility == View.GONE) {
                slide.visibility = View.VISIBLE
            }
        } else {
            if (slide.visibility == View.VISIBLE)
                slide.visibility = View.GONE
        }
    }

    override fun initList(citiesList: List<String>) {
        itemDecorate()
        adapter = CitiesAdapter(citiesList, this::onCityClicked)
        rvGeneralCities.adapter = adapter
    }

    override fun initListener() {
        svCity.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(enteredText: String?): Boolean {
                    presenter.searchTextChanged(enteredText)
                    return false
                }
            })
    }

    override fun itemDecorate() {
        val dividerItem = DividerItemDecoration(activity, RecyclerView.VERTICAL)
        rvGeneralCities.addItemDecoration(dividerItem)
    }

    override fun onCityClicked(nameCity: String) {
        presenter.onCityClicked(nameCity)
    }

    override fun getResourceString(id: Int, vararg formatArgs: Any?): String =
        resources.getString(id, *formatArgs)

    override fun getResourceId(nameString: String): Int? {
        return manager.string(nameString)
    }

    private fun init() {
//        val activity = requireActivity() as MainActivity
//        val app = activity.applicationContext as App
//        val model = app.model
//        presenter = GeneralPresenter(model)
        val model = Model.model
        presenter = GeneralPresenter(model)
    }
}