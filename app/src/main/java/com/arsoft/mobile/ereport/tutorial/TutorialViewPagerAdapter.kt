package com.arsoft.mobile.ereport.tutorial

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.arsoft.mobile.ereport.tutorial.tab.Tutorial1
import com.arsoft.mobile.ereport.tutorial.tab.Tutorial2
import com.arsoft.mobile.ereport.tutorial.tab.Tutorial3
import com.arsoft.mobile.ereport.tutorial.tab.Tutorial4

class TutorialViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> Tutorial1()
            1 -> Tutorial2()
            2 -> Tutorial3()
            3 -> Tutorial4()
            else -> Tutorial1()
        }
    }

    override fun getCount(): Int {
        return 4
    }

}