package com.practicum.imdbmovies.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.practicum.imdbmovies.ui.details.DetailsFragment
import com.practicum.imdbmovies.ui.poster.PosterFragment

class InfoPagerAdapter(fragment: Fragment, url : String, id : String) : FragmentStateAdapter(fragment) {

    private val fragments = listOf(
        PosterFragment.newInstance(url),
        DetailsFragment.newInstance(id),
    )
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}