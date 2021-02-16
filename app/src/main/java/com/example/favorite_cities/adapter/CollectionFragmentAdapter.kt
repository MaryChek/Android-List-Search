package com.example.favorite_cities.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.favorite_cities.FragmentKeys
import com.example.favorite_cities.fragments.FavoritesFragment
import com.example.favorite_cities.fragments.GeneralFragment

class CollectionFragmentAdapter(
    fragmentManager: FragmentManager,
    private val fragmentsNamesId: MutableMap<FragmentKeys, Int>,
    private val resources: Resources
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val COUNT_FRAGMENT = 2
    }

    override fun getCount(): Int =
        COUNT_FRAGMENT

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> GeneralFragment()
            else -> FavoritesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> {
                fragmentsNamesId[FragmentKeys.GENERAL]?.let {
                    resources.getString(it)
                } ?: ""
            }
            else -> {
                fragmentsNamesId[FragmentKeys.FAVORITE]?.let {
                    resources.getString(it)
                } ?: ""
            }
        }
    }
}