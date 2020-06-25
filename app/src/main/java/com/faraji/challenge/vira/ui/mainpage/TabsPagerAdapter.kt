package com.faraji.challenge.vira.ui.mainpage

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.faraji.challenge.vira.R

private val TAB_TITLES = arrayOf(
    R.string.yesterday,
    R.string.today,
    R.string.tomarrow
)


class TabsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return GameResultFragment.newInstance()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}