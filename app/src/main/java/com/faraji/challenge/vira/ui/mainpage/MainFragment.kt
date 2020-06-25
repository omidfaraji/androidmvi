package com.faraji.challenge.vira.ui.mainpage

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.faraji.challenge.vira.R
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_main, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = TabsPagerAdapter(requireContext(), childFragmentManager)
        tabs.setupWithViewPager(viewPager)
        appBar.tag = AppBarStateChangeListener.State.EXPANDED
        appBar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                when (state) {
                    State.COLLAPSED -> {
                        if ((appBar.tag as State) == State.COLLAPSED)
                            return
                        bottomNavigation.animate()
                            .translationYBy(bottomNavigation.height.toFloat())
                            .apply {
                                duration = 300
                                setListener(object : AnimatorAdapter() {
                                    override fun onAnimationEnd(animation: Animator?) {
                                        appBar.tag = State.COLLAPSED
                                    }
                                })
                                start()
                            }
                    }
                    State.EXPANDED -> {
                        if ((appBar.tag as State) == State.EXPANDED)
                            return
                        bottomNavigation.animate()
                            .translationYBy(-bottomNavigation.height.toFloat())
                            .apply {
                                duration = 300
                                setListener(object : AnimatorAdapter() {
                                    override fun onAnimationEnd(animation: Animator?) {
                                        appBar.tag = State.EXPANDED
                                    }
                                })
                                start()
                            }
                    }
                }
            }

        })
    }
}