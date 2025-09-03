package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Onboarding1Activity : AppCompatActivity() {
    
    private lateinit var btnNext: Button
    private lateinit var btnSkip: Button
    private lateinit var pageImage: ImageView
    private lateinit var pageTitle: TextView
    private lateinit var pageDescription: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding1)
        
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

        pageTitle.text = "Discover Amazing Places"
        pageDescription.text = "Explore beautiful destinations around Sri Lanka with our carefully curated travel experiences"
        pageImage.setImageResource(R.drawable.discover_amazing_places)
    }
    
    private fun setupButtons() {

        btnNext.setOnClickListener {
            val intent = Intent(this, Onboarding2Activity::class.java)
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
