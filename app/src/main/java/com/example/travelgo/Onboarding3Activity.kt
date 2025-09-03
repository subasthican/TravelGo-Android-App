package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Onboarding3Activity : AppCompatActivity() {
    
    private lateinit var btnGetStarted: Button
    private lateinit var btnSkip: Button
    private lateinit var pageImage: ImageView
    private lateinit var pageTitle: TextView
    private lateinit var pageDescription: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding3)
        
        initViews()
        setupContent()
        setupButtons()
    }
    
    private fun initViews() {
        btnGetStarted = findViewById(R.id.btn_get_started)
        btnSkip = findViewById(R.id.btn_skip)
        pageImage = findViewById(R.id.page_image)
        pageTitle = findViewById(R.id.page_title)
        pageDescription = findViewById(R.id.page_description)
    }
    
    private fun setupContent() {

        pageTitle.text = "Travel with Confidence"
        pageDescription.text = "Safe, secure, and hassle-free travel planning with 24/7 customer support"
        pageImage.setImageResource(R.drawable.traveling_with_confidence)
    }
    
    private fun setupButtons() {

        btnGetStarted.setOnClickListener {
            goToLogin()
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
