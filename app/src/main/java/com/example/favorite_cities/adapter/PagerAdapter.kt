package com.example.favorite_cities.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.favorite_cities.view.fragments.FavoritesFragment
import com.example.favorite_cities.view.fragments.GeneralFragment

class PagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =
        COUNT_OF_PAGE

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GeneralFragment()
            else -> FavoritesFragment()
        }
    }

    companion object {
        private const val COUNT_OF_PAGE = 2
    }
}
