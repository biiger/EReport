package com.arsoft.mobile.ereport.tutorial

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.arsoft.mobile.ereport.LoginActivity
import com.arsoft.mobile.ereport.R
import kotlinx.android.synthetic.main.activity_tutorial.*

class TutorialActivity : AppCompatActivity() {

    private lateinit var adapter: TutorialViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
        supportActionBar?.hide()
        setupWidget()
    }

    private fun setupWidget() {

        adapter = TutorialViewPagerAdapter(supportFragmentManager)
        activity_tutorial_vp_pager.adapter = adapter
        activity_tutorial_worm_dots_indicator.setViewPager(activity_tutorial_vp_pager)

        activity_tutorial_vp_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                if (p0 == 3) {
                    activity_tutorial_ll_button_skip.visibility = View.INVISIBLE
                    activity_tutorial_worm_dots_indicator.visibility = View.GONE
                    activity_tutorial_bt_start.visibility = View.VISIBLE
                } else {
                    activity_tutorial_ll_button_skip.visibility = View.VISIBLE
                    activity_tutorial_worm_dots_indicator.visibility = View.VISIBLE
                    activity_tutorial_bt_start.visibility = View.GONE
                }
            }

        })

        activity_tutorial_ll_button_skip.setOnClickListener {
            var intent = Intent(this@TutorialActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        activity_tutorial_bt_start.setOnClickListener {
            var intent = Intent(this@TutorialActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
