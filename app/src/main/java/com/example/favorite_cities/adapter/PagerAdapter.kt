package com.example.favorite_cities.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.favorite_cities.fragments.FavoritesFragment
import com.example.favorite_cities.fragments.GeneralFragment

class PagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

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
