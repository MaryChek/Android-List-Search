package com.example.favorite_cities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.favorite_cities.App
import com.example.favorite_cities.MainActivity
import com.example.favorite_cities.R
import com.example.favorite_cities.adapter.CollectionFragmentAdapter
import com.example.favorite_cities.contract.CollectionContract
import com.example.favorite_cities.presenter.CollectionPresenter
import com.google.android.material.tabs.TabLayout

class CollectionFragment : Fragment(), CollectionContract.View {
    private var presenter: CollectionPresenter? = null
    private lateinit var pager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        val activity = activity as MainActivity
        val app = activity.applicationContext as App
        if (savedInstanceState != null)
            app.upDateListInModel()
        val model = app.model
        presenter = CollectionPresenter(model)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pager = view.findViewById(R.id.pager)
        tabLayout = view.findViewById(R.id.tabLayout)
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
        pager.currentItem = 1
    }

    override fun getFavoriteFragmentNameId(): CharSequence =
        resources.getString(R.string.name_fragment_favorite)

    override fun getGeneralFragmentNameId(): CharSequence =
        resources.getString(R.string.name_fragment_general)
}
