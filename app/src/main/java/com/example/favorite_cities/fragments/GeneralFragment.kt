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
import com.example.favorite_cities.contract.BaseContract
import com.example.favorite_cities.contract.CitiesContract
import com.example.favorite_cities.contract.GeneralCitiesContract
import com.example.favorite_cities.presenter.GeneralPresenter
import kotlinx.android.synthetic.main.fragment_fragment_general.*


class GeneralFragment :
    BaseCitiesFragment<GeneralCitiesContract.View, GeneralCitiesContract.Presenter>(),
    GeneralCitiesContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun init() {
        val activity = requireActivity() as MainActivity
        val app = activity.applicationContext as App
        val model = app.model
        presenter = GeneralPresenter(model)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_fragment_general, container, false)
        rvCities = rootView.findViewById(R.id.rvGeneralCities)
        svCity = rootView.findViewById(R.id.svCity)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onAttachView(this)
        presenter?.onViewCreated()
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

    override fun showSlideNothingFound(show: Boolean) {
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

    override fun getResourceString(id: Int, vararg formatArgs: Any?): String =
        resources.getString(id, *formatArgs)

    override fun getResourceId(nameString: String): Int? {
        return manager.string(nameString)
    }

    override fun setEnteredText(text: CharSequence) {
        svCity.setQuery(text, false)
    }

}