package com.br.everis.financialgoal.ui

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.br.everis.financialgoal.R
import com.google.android.material.tabs.TabLayout
import java.util.*

class MainActivity : AppCompatActivity() {

	var currentPage = 0
	private lateinit var timer: Timer
	val DELAY_MS: Long = 500
	val PERIOD_MS: Long = 3000

	var mViewPager: ViewPager? = null
	private var tabLayout: TabLayout? = null

	var images = intArrayOf(
		R.drawable.logged_out_slide_1,
		R.drawable.logged_out_slide_2,
		R.drawable.logged_out_slide_3,
		R.drawable.logged_out_slide_4,
	)

	var NUM_PAGES = images.size

	private var mViewPagerAdapter: ViewPagerAdapter? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		mViewPager = findViewById<View>(R.id.viewPagerMain) as ViewPager
		tabLayout = findViewById(R.id.tabDots)

		tabLayout!!.setupWithViewPager(mViewPager, true)

		mViewPagerAdapter = ViewPagerAdapter(this@MainActivity, images)

		mViewPager!!.adapter = mViewPagerAdapter

		val handler = Handler()
		val Update = Runnable {
			if (currentPage == NUM_PAGES) {
				currentPage = 0
			}
			mViewPager!!.setCurrentItem(currentPage++, true)
		}

		timer = Timer() // This will create a new Thread

		timer.schedule(object : TimerTask() {
			// task to be scheduled
			override fun run() {
				handler.post(Update)
			}
		}, DELAY_MS, PERIOD_MS)
	}
}