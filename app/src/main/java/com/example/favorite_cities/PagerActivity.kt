package com.example.favorite_cities

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.favorite_cities.adapter.PagerAdapter
import com.example.favorite_cities.contract.PagerContract
import com.example.favorite_cities.databinding.PagerActivityBinding
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.presenter.PagerPresenter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PagerActivity : FragmentActivity(), PagerContract.View {
    private var binding: PagerActivityBinding? = null
    private var presenter: PagerPresenter? = null
    private var adapter: PagerAdapter? = null
    private var pagerCities: ViewPager2? = null
    private var tabLayoutCities: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PagerActivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        init(savedInstanceState)
        initPager()
        registerOnPageChange()
    }

    private fun init(savedInstanceState: Bundle?) {
        val app: App = this.applicationContext as App
        val model: CitiesModel = app.model
        if (savedInstanceState != null) {
            model.updateCitiesLists()
        }
        presenter = PagerPresenter(model, this)
        pagerCities = binding?.pagerCities
        tabLayoutCities = binding?.tabLayoutCities
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

    override fun onBackPressed() {
        if (pagerCities?.currentItem == 0) {
            super.onBackPressed()
        } else {
            pagerCities?.currentItem = pagerCities?.currentItem?.minus(1)!!
        }
    }

    override fun onStart() {
        super.onStart()
        presenter?.onActivityStart()
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