package com.example.travelgo

import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val termsOption = findViewById<LinearLayout>(R.id.terms_option)
        val privacyPolicyOption = findViewById<LinearLayout>(R.id.privacy_policy_option)
        val licensesOption = findViewById<LinearLayout>(R.id.licenses_option)
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Terms of Service option
        termsOption.setOnClickListener {
            Toast.makeText(this, "Terms of Service coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        // Privacy Policy option
        privacyPolicyOption.setOnClickListener {
            Toast.makeText(this, "Privacy Policy coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        // Licenses option
        licensesOption.setOnClickListener {
            Toast.makeText(this, "Open Source Licenses coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        Toast.makeText(this, "About TravelGo page loaded", Toast.LENGTH_SHORT).show()
    }
}
