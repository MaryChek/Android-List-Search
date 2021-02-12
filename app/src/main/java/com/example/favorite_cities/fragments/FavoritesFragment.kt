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
import com.example.favorite_cities.model.Model
import com.example.favorite_cities.presenter.GeneralPresenter
import kotlinx.android.synthetic.main.fragment_fragment_favorites.*

class FavoritesFragment : Fragment(), FavoriteCitiesContract.View {
    private var adapter: CitiesAdapter? = null
    private var presenter: FavoritePresenter? = null
    private lateinit var rvFavoriteCities: RecyclerView
    private lateinit var svCity: SearchView
    private var manager = ResourceManager()

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
//        val model = Model.model
//        presenter = FavoritePresenter(model)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.fragment_fragment_favorites, container, false
        )
        rvFavoriteCities = rootView.findViewById(R.id.rvFavoriteCities)
        svCity = rootView.findViewById(R.id.svCity)
        textSlide = rootView.findViewById(R.id.textSlideInFavorites)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onAttachView(this)
        presenter?.onViewCreated()
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible)
            presenter?.onFragmentVisible()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun showCitiesList(citiesList: List<String>) {
        initList(citiesList)
        initListener()
    }

    override fun initList(citiesList: List<String>) {
        itemDecorate()
        adapter = CitiesAdapter(citiesList, this::onCityClicked)
        rvFavoriteCities.adapter = adapter
    }

    override fun initListener() {
        svCity.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(enteredText: String?): Boolean {
                    presenter?.searchTextChanged(enteredText)
                    return false
                }
            })
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

//    override fun showSlideNothingFound(show: Boolean) {
//        val slide = slideNothingFoundInFavorite
//        if (show) {
//            if (slide.visibility == View.GONE)
//                slide.visibility = View.VISIBLE
//        } else {
//            if (slide.visibility == View.VISIBLE)
//                slide.visibility = View.GONE
//        }
//    }

//    override fun showSlideNoFavorites(show: Boolean) {
//        val slide = slideNoFavoriteCities
//        if (show) {
//            if (slide.visibility == View.GONE)
//                slide.visibility = View.VISIBLE
//        } else {
//            if (slide.visibility == View.VISIBLE)
//                slide.visibility = View.GONE
//        }
//    }

    override fun itemDecorate() {
        val dividerItem = DividerItemDecoration(activity, RecyclerView.VERTICAL)
        rvFavoriteCities.addItemDecoration(dividerItem)
    }

    override fun onCityClicked(nameCity: String) {
        presenter?.onCityClicked(nameCity)
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

