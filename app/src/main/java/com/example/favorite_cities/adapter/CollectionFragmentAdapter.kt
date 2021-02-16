package com.example.favorite_cities.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.favorite_cities.CitiesFragmentsParams
import com.example.favorite_cities.fragments.FavoritesFragment
import com.example.favorite_cities.fragments.GeneralFragment

class CollectionFragmentAdapter(
    fragmentManager: FragmentManager,
    private val fragmentsName: MutableMap<String, Int>,
    private val resources: Resources
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
            0 -> {
                val titleId: Int? = fragmentsName[CitiesFragmentsParams.FAVORITE]
                titleId?.let {
                    resources.getString(titleId)
                } ?: ""
            }
            else -> {
                val titleId: Int? = fragmentsName[CitiesFragmentsParams.FAVORITE]
                titleId?.let {
                    resources.getString(titleId)
                } ?: ""
            }
        }
    }
}