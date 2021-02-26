package com.example.favorite_cities.fragments

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.favorite_cities.*
import com.example.favorite_cities.adapter.CollectionFragmentAdapter
import com.example.favorite_cities.contract.CollectionContract
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.presenter.CollectionPresenter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CollectionFragment : Fragment(R.layout.fragment_collection), CollectionContract.View {
    private var presenter: CollectionPresenter? = null
    private lateinit var adapter: CollectionFragmentAdapter
    private lateinit var pagerCities: ViewPager2
    private lateinit var tabLayoutCities: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        val activity: Activity = activity as MainActivity
        val app: App = activity.applicationContext as App
        if (savedInstanceState != null)
            app.updateListInModel()
        val model: CitiesModel = app.model
        presenter = CollectionPresenter(model, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerCities = view.findViewById(R.id.pagerCities)
        tabLayoutCities = view.findViewById(R.id.tabLayoutCities)
        presenter?.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun showPagerWithFragments(countFragment: Int) {
        adapter = CollectionFragmentAdapter(this, countFragment)
        pagerCities.adapter = adapter
        presenter?.onFragmentsShown()
    }

    override fun addTitlesInTabLayout(fragmentsNamesId: Array<Int>) {
        TabLayoutMediator(tabLayoutCities, pagerCities) { tab, position ->
            fragmentsNamesId[position].let {
                tab.text = resources.getString(it)
            }
        }.attach()
    }

    override fun setFavoriteFragmentAsCurrent(currentFragmentPosition: Int) {
        pagerCities.currentItem = currentFragmentPosition
    }
}
