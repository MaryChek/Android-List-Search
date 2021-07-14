package com.example.favorite_cities.view.fragments

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite_cities.*
import com.example.favorite_cities.adapter.CitiesListAdapter
import com.example.favorite_cities.contract.CitiesContract
import com.example.favorite_cities.databinding.FragmentCitiesListBinding
import com.example.favorite_cities.App
import com.example.favorite_cities.model.cities.CitiesModel
import com.example.favorite_cities.view.CityAttributes
import com.example.favorite_cities.model.cities.CityIcon
import com.example.favorite_cities.view.DialogCreator
import kotlinx.android.synthetic.main.fragment_cities_list.*

abstract class BaseCitiesFragment<T : CitiesContract.Presenter<CitiesContract.View>>(
    private val dialogCreator: DialogCreator = DialogCreator()
) : Fragment(), CitiesContract.View {
    protected var presenter: T? = null
    protected lateinit var model: CitiesModel
    private var rvCities: RecyclerView? = null
    private var svCity: SearchView? = null
    private var adapter: CitiesListAdapter? = null
    private var binding: FragmentCitiesListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app: App = requireActivity().applicationContext as App
        model = app.citiesModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCitiesListBinding.inflate(inflater, container, false)
        rvCities = binding?.rvCities
        svCity = binding?.svCity
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initListener()
        presenter?.onViewCreated()
    }

    private fun initList() {
        adapter = CitiesListAdapter(this::onCityIconClicked)
        rvCities?.adapter = adapter
        addDividerItem()
    }

    private fun onCityIconClicked(nameCity: String) {
        presenter?.onCityIconClicked(nameCity)
    }

    private fun addDividerItem() {
        val dividerItem = DividerItemDecoration(activity, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_cities, context?.theme)?.let {
            dividerItem.setDrawable(it)
            rvCities?.addItemDecoration(dividerItem)
        }
    }

    private fun initListener() {
        svCity?.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(enteredText: String?): Boolean {
                    presenter?.onSearchTextChanged(enteredText)
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    hideKeyboard()
                    println(query)
                    return true
                }
            })
    }

    private fun hideKeyboard() {
        val inputManager: InputMethodManager? =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager?.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

    override fun setEnteredText(text: CharSequence) {
        svCity?.setQuery(text, false)
    }

    override fun updateCitiesList(modifiedListCities: List<CityIcon>) {
        adapter?.submitList(getCitiesAttributes(modifiedListCities))
    }

    private fun getCitiesAttributes(listCities: List<CityIcon>): List<CityAttributes> {
        val citiesAttributes: MutableList<CityAttributes> = mutableListOf()
        listCities.forEach {
            val drawable: Drawable? = getCityIconDrawable(it.iconId)
            citiesAttributes.add(
                CityAttributes(
                    it.name,
                    drawable
                )
            )
        }
        return citiesAttributes
    }

    private fun getCityIconDrawable(@DrawableRes iconId: Int): Drawable? =
        ResourcesCompat.getDrawable(resources, iconId, context?.theme)

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            presenter?.onTabVisible()
        }
    }

    override fun showAddToFavoriteActionConfirmation(nameCity: String) {
        dialogCreator.setTitle(nameCity)
        dialogCreator.setMessage(R.string.message_for_unelected_city)
        dialogCreator.setPositiveButtonTitle(R.string.text_button_add)
        dialogCreator.setNegativeButtonTitle(R.string.button_cancel)
        dialogCreator.setFunctionOnPositive(this::onAddButtonClick)
        dialogCreator.showDialog(requireActivity(), nameCity)
    }

    private fun onAddButtonClick(nameCity: String) {
        presenter?.onAddToFavoriteClick(nameCity)
    }

    override fun showRemoveFromFavoriteActionConfirmation(nameCity: String) {
        dialogCreator.setTitle(nameCity)
        dialogCreator.setMessage(R.string.message_for_favorite_city)
        dialogCreator.setPositiveButtonTitle(R.string.text_button_remove)
        dialogCreator.setNegativeButtonTitle(R.string.button_cancel)
        dialogCreator.setFunctionOnPositive(this::onRemoveButtonClick)
        dialogCreator.showDialog(requireActivity(), nameCity)
    }

    private fun onRemoveButtonClick(nameCity: String) {
        presenter?.onRemoveFromFavoriteClick(nameCity)
    }

    override fun showEmptyListHint(@StringRes textId: Int) {
        val slide: TextView = tvEmptyListHint
        slide.text = resources.getString(textId)
        slide.visibility = View.VISIBLE
    }

    override fun hideEmptyListHint() {
        tvEmptyListHint.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        presenter?.onViewPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        dialogCreator.onDestroy()
    }
}
