package com.example.favorite_cities.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.favorite_cities.CitiesFragmentsParams
import com.example.favorite_cities.fragments.FavoritesFragment
import com.example.favorite_cities.fragments.GeneralFragment

class CollectionFragmentAdapter(
    fragmentManager: FragmentManager,
    private val fragmentsName: MutableMap<String, CharSequence?>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int =
        CitiesFragmentsParams.COUNT_FRAGMENT

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> GeneralFragment()
            else -> FavoritesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> fragmentsName[CitiesFragmentsParams.GENERAL] ?: ""
            else -> fragmentsName[CitiesFragmentsParams.FAVORITE] ?: ""
        }
    }
}