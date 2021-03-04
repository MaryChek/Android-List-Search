package com.example.favorite_cities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite_cities.*
import com.example.favorite_cities.adapter.CitiesAdapter
import com.example.favorite_cities.contract.CitiesContract
import com.example.favorite_cities.databinding.FragmentCitiesListBinding
import com.example.favorite_cities.model.CitiesModel
import kotlinx.android.synthetic.main.fragment_cities_list.*

abstract class BaseCitiesFragment<V : CitiesContract.View, T : CitiesContract.Presenter<V>> :
    Fragment(), CitiesContract.View {
    protected var presenter: T? = null
    protected val dialogCreator: DialogCreator = DialogCreator()
    protected lateinit var model: CitiesModel
    private var rvCities: RecyclerView? = null
    private var svCity: SearchView? = null
    private var adapter: CitiesAdapter? = null
    private var binding: FragmentCitiesListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app: App = requireActivity().applicationContext as App
        model = app.model
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
        initList(emptyList())
        initListener()
        presenter?.onViewCreated()
    }

    private fun initList(citiesList: List<String>) {
        adapter = CitiesAdapter(citiesList, this::onCityClicked)
        rvCities?.adapter = adapter
        addDividerItem()
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
            object : SimpleSearchTextListener() {
                override fun onQueryTextChange(enteredText: String?): Boolean {
                    presenter?.onSearchTextChanged(enteredText)
                    return false
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            presenter?.onTabVisible()
        }
    }

    override fun updateCitiesList(modifiedList: List<String>) {
        adapter?.updateList(modifiedList)
    }

    override fun showDialogAdding(nameCity: String) {
        dialogCreator.setTitle(nameCity)
        dialogCreator.setMessage(R.string.message_for_unelected_city)
        dialogCreator.setPositiveButtonTitle(R.string.text_button_add)
        dialogCreator.setNegativeButtonTitle(R.string.button_cancel)
        dialogCreator.setFunctionOnPositive(this::onAddButtonClick)
        dialogCreator.showDialog(requireActivity(), nameCity)
    }

    override fun showDialogRemoving(nameCity: String) {
        dialogCreator.setTitle(nameCity)
        dialogCreator.setMessage(R.string.message_for_favorite_city)
        dialogCreator.setPositiveButtonTitle(R.string.text_button_remove)
        dialogCreator.setNegativeButtonTitle(R.string.button_cancel)
        dialogCreator.setFunctionOnPositive(this::onRemoveButtonClick)
        dialogCreator.showDialog(requireActivity(), nameCity)
    }

    private fun onAddButtonClick(nameCity: String) {
        presenter?.onAddButtonClick(nameCity)
    }

    private fun onRemoveButtonClick(nameCity: String) {
        presenter?.onRemoveButtonClick(nameCity)
    }

    override fun showEmptyListHint(@StringRes textId: Int) {
        val slide: TextView = tvEmptyListHint
        slide.text = resources.getString(textId)
        slide.visibility = View.VISIBLE
    }

    override fun hideEmptyListHint() {
        tvEmptyListHint.visibility = View.GONE
    }

    override fun showUserMessage(@StringRes stringId: Int, nameCity: String) =
        Toast.makeText(
            activity, resources.getString(stringId, nameCity), Toast.LENGTH_LONG
        ).show()

    override fun setEnteredText(text: CharSequence) {
        svCity?.setQuery(text, false)
    }

    private fun onCityClicked(nameCity: String) {
        presenter?.onCityClicked(nameCity)
    }
}