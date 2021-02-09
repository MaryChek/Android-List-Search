package com.example.favorite_cities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.favorite_cities.R
import com.example.favorite_cities.presenter.PresenterCommon
import com.google.android.material.tabs.TabLayout

class CollectionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val presenterCommon = PresenterCommon()
        val collectionAdapter = CollectionAdapter(childFragmentManager, view, presenterCommon)
        val pager: ViewPager = view.findViewById(R.id.pager)
        pager.adapter = collectionAdapter

        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(pager)
    }
}
