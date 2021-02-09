package com.example.android_list_search.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.android_list_search.R
import com.example.android_list_search.presenter.PresenterCommon
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

class CollectionAdapter(
    fragmentManager: FragmentManager,
    private val view: View,
    private val presenterCommon: PresenterCommon
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int =
        2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FragmentGeneral(presenterCommon)
            else -> FragmentFavorites(presenterCommon)
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> view.resources.getString(R.string.name_fragment_general)
            else -> view.resources.getString(R.string.name_fragment_favorite)
        }
    }
}