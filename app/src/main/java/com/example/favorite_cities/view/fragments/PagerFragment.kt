package com.example.favorite_cities.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.favorite_cities.App
import com.example.favorite_cities.view.OnBackPressed
import com.example.favorite_cities.adapter.PagerAdapter
import com.example.favorite_cities.contract.PagerContract
import com.example.favorite_cities.databinding.PagerFragmentBinding
import com.example.favorite_cities.model.cities.CitiesModel
import com.example.favorite_cities.presenter.PagerPresenter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PagerFragment : Fragment(), PagerContract.View,
    OnBackPressed {
    private var binding: PagerFragmentBinding? = null
    private var presenter: PagerPresenter? = null
    private var adapter: PagerAdapter? = null
    private var pagerCities: ViewPager2? = null
    private var tabLayoutCities: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        val app: App = requireActivity().applicationContext as App
        val model: CitiesModel = app.citiesModel
        if (savedInstanceState != null) {
            model.updateCitiesLists()
        }
        presenter = PagerPresenter(model, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PagerFragmentBinding.inflate(inflater, container, false)
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
        adapter = PagerAdapter(this)
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

    override fun onBackPressed(): Boolean =
        if (pagerCities?.currentItem == 0) {
            true
        } else {
            pagerCities?.currentItem = pagerCities?.currentItem?.minus(1)!!
            false
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}