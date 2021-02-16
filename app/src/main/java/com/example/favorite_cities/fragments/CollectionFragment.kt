package com.example.favorite_cities.fragments

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.favorite_cities.App
import com.example.favorite_cities.CitiesFragmentsParams
import com.example.favorite_cities.MainActivity
import com.example.favorite_cities.R
import com.example.favorite_cities.adapter.CollectionFragmentAdapter
import com.example.favorite_cities.contract.CollectionContract
import com.example.favorite_cities.model.CitiesModel
import com.example.favorite_cities.presenter.CollectionPresenter
import com.google.android.material.tabs.TabLayout

class CollectionFragment : Fragment(R.layout.fragment_collection), CollectionContract.View{
    private var presenter: CollectionPresenter? = null
    private lateinit var pager: ViewPager
    private lateinit var tabLayout: TabLayout

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
        presenter = CollectionPresenter(model, resources)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pager = view.findViewById(R.id.pagerCities)
        tabLayout = view.findViewById(R.id.tabLayoutCities)
        presenter?.onAttachView(this)
        presenter?.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun showPagerWithFragments(fragmentsNames: MutableMap<String, CharSequence?>) {
        pager.adapter = CollectionFragmentAdapter(childFragmentManager, fragmentsNames)
        presenter?.fragmentsDisplayed()
    }

    override fun addPagerInTabLayout() {
        tabLayout.setupWithViewPager(pager)
    }

    override fun setFavoriteFragmentAsCurrent() {
        pager.currentItem = CitiesFragmentsParams.FAVORITE_FRAGMENT
    }
}
