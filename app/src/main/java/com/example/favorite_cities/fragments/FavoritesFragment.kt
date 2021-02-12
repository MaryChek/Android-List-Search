package com.example.favorite_cities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite_cities.*
import com.example.favorite_cities.presenter.FavoritePresenter
import com.example.favorite_cities.adapter.CitiesAdapter
import com.example.favorite_cities.contract.FavoriteCitiesContract

class FavoritesFragment :
    BaseCitiesFragment<FavoriteCitiesContract.View, FavoriteCitiesContract.Presenter>(),
    FavoriteCitiesContract.View {

    private lateinit var textSlide: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun init() {
        val activity = requireActivity() as MainActivity
        val app = activity.applicationContext as App
        val model = app.model
        presenter = FavoritePresenter(model)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.fragment_fragment_favorites, container, false
        )
        rvCities = rootView.findViewById(R.id.rvFavoriteCities)
        svCity = rootView.findViewById(R.id.svCity)
        textSlide = rootView.findViewById(R.id.textSlideInFavorites)
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

    override fun showTextSlide(text: String, show: Boolean) {
        if (show) {
            textSlide.text = text
            textSlide.visibility = View.VISIBLE
        } else {
            textSlide.visibility = View.GONE
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

