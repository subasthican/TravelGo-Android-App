package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Onboarding2Activity : AppCompatActivity() {
    
    private lateinit var btnNext: Button
    private lateinit var btnSkip: Button
    private lateinit var pageImage: ImageView
    private lateinit var pageTitle: TextView
    private lateinit var pageDescription: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding2)
        
        initViews()
        setupContent()
        setupButtons()
    }
    
    private fun initViews() {
        btnNext = findViewById(R.id.btn_next)
        btnSkip = findViewById(R.id.btn_skip)
        pageImage = findViewById(R.id.page_image)
        pageTitle = findViewById(R.id.page_title)
        pageDescription = findViewById(R.id.page_description)
    }
    
    private fun setupContent() {

        pageTitle.text = "Plan Your Perfect Trip"
        pageDescription.text = "Create personalized itineraries and book your dream vacation with just a few taps"
        pageImage.setImageResource(R.drawable.plan_your_perfect_trip)
    }
    
    private fun setupButtons() {

        btnNext.setOnClickListener {
            val intent = Intent(this, Onboarding3Activity::class.java)
            startActivity(intent)
            finish()
        }
        

        btnSkip.setOnClickListener {
            goToLogin()
        }
    }
    
    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
