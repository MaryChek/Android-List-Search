package com.example.favorite_cities.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.favorite_cities.fragments.FavoritesFragment
import com.example.favorite_cities.fragments.GeneralFragment

class CollectionFragmentAdapter(
    fragment: Fragment,
    private val countFragment: Int
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =
        countFragment

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GeneralFragment()
            else -> FavoritesFragment()
        }
    }
}
