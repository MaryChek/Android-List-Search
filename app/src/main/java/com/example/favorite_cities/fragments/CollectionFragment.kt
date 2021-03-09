package com.example.favorite_cities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.favorite_cities.*
import com.example.favorite_cities.adapter.CollectionFragmentAdapter
import com.example.favorite_cities.contract.CollectionContract
import com.example.favorite_cities.databinding.FragmentCollectionBinding
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.presenter.CollectionPresenter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CollectionFragment : Fragment(), CollectionContract.View {
    private var presenter: CollectionPresenter? = null
    private var adapter: CollectionFragmentAdapter? = null
    private var pagerCities: ViewPager2? = null
    private var tabLayoutCities: TabLayout? = null
    private var binding: FragmentCollectionBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        val app: App = requireActivity().applicationContext as App
        val model: CitiesModel = app.model
        if (savedInstanceState != null) {
            model.updateCitiesLists()
        }
        presenter = CollectionPresenter(model, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectionBinding.inflate(inflater, container, false)
        pagerCities = binding?.pagerCities
        tabLayoutCities = binding?.tabLayoutCities
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPager()
        registerOnPageChange()
        presenter?.onViewCreated()
    }

    private fun initPager() {
        adapter = CollectionFragmentAdapter(this)
        pagerCities?.adapter = adapter
    }

    private fun registerOnPageChange() {
        pagerCities?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                presenter?.onPageSelected(position)
            }
        })
    }

    override fun setTitlesInTabLayout(tabTitleResIds: List<Int>) {
        TabLayoutMediator(tabLayoutCities!!, pagerCities!!) { tab, position ->
            tabTitleResIds[position].let {
                tab.text = resources.getString(it)
            }
        }.attach()
    }

    override fun selectItemOnPager(position: Int) {
        pagerCities?.currentItem = position
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
