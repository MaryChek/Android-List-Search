package com.example.favorite_cities.adapter

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.favorite_cities.R
import com.example.favorite_cities.fragments.FavoritesFragment
import com.example.favorite_cities.fragments.GeneralFragment

class CollectionFragmentAdapter(
    fragmentManager: FragmentManager,
    private val view: View
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int =
        2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> GeneralFragment()
            else -> FavoritesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> view.resources.getString(R.string.name_fragment_general)
            else -> view.resources.getString(R.string.name_fragment_favorite)
        }
    }
}