package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingActivity : AppCompatActivity() {
    
    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: LinearLayout
    private lateinit var btnNext: Button
    private lateinit var btnSkip: Button
    private lateinit var btnGetStarted: Button
    
    private val onboardingPages = listOf(
        OnboardingPage(
            "Discover Amazing Places",
            "Explore beautiful destinations around Sri Lanka with our carefully curated travel experiences",
            R.drawable.ic_globe
        ),
        OnboardingPage(
            "Plan Your Perfect Trip",
            "Create personalized itineraries and book your dream vacation with just a few taps",
            R.drawable.ic_airplane
        ),
        OnboardingPage(
            "Travel with Confidence",
            "Safe, secure, and hassle-free travel planning with 24/7 customer support",
            R.drawable.ic_heart
        )
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        
        initViews()
        setupViewPager()
        setupButtons()
        setupDotsIndicator()
    }
    
    private fun initViews() {
        viewPager = findViewById(R.id.onboarding_viewpager)
        dotsIndicator = findViewById(R.id.dots_indicator)
        btnNext = findViewById(R.id.btn_next)
        btnSkip = findViewById(R.id.btn_skip)
        btnGetStarted = findViewById(R.id.btn_get_started)
    }
    
    private fun setupViewPager() {
        val adapter = OnboardingPagerAdapter(this)
        viewPager.adapter = adapter
        
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateDotsIndicator(position)
                updateButtons(position)
            }
        })
    }
    
    private fun setupButtons() {
        btnNext.setOnClickListener {
            val currentItem = viewPager.currentItem
            if (currentItem < onboardingPages.size - 1) {
                viewPager.currentItem = currentItem + 1
            }
        }
        
        btnSkip.setOnClickListener {
            goToLogin()
        }
        
        btnGetStarted.setOnClickListener {
            goToLogin()
        }
    }
    
    private fun setupDotsIndicator() {
        val dots = arrayOfNulls<ImageView>(onboardingPages.size)
        
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            dots[i]?.setImageResource(R.drawable.circle_grey_outline)
            
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            
            dotsIndicator.addView(dots[i], params)
        }
        
        // Set first dot as active
        if (dots.isNotEmpty()) {
            dots[0]?.setImageResource(R.drawable.circle_orange)
        }
    }
    
    private fun updateDotsIndicator(position: Int) {
        for (i in 0 until dotsIndicator.childCount) {
            val dot = dotsIndicator.getChildAt(i) as ImageView
            if (i == position) {
                dot.setImageResource(R.drawable.circle_orange)
            } else {
                dot.setImageResource(R.drawable.circle_grey_outline)
            }
        }
    }
    
    private fun updateButtons(position: Int) {
        if (position == onboardingPages.size - 1) {
            // Last page
            btnNext.visibility = View.GONE
            btnGetStarted.visibility = View.VISIBLE
        } else {
            // Not last page
            btnNext.visibility = View.VISIBLE
            btnGetStarted.visibility = View.GONE
        }
    }
    
    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    
    private inner class OnboardingPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = onboardingPages.size
        
        override fun createFragment(position: Int): Fragment {
            return OnboardingFragment.newInstance(onboardingPages[position])
        }
    }
    
    data class OnboardingPage(
        val title: String,
        val description: String,
        val imageResource: Int
    )
}
