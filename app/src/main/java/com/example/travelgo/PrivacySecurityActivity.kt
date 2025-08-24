package com.example.travelgo

import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PrivacySecurityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_security)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val locationSwitch = findViewById<Switch>(R.id.location_switch)
        val dataCollectionSwitch = findViewById<Switch>(R.id.data_collection_switch)
        val changePasswordOption = findViewById<LinearLayout>(R.id.change_password_option)
        val twoFactorOption = findViewById<LinearLayout>(R.id.two_factor_option)
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Change password option
        changePasswordOption.setOnClickListener {
            Toast.makeText(this, "Change password feature coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        // Two-factor authentication option
        twoFactorOption.setOnClickListener {
            Toast.makeText(this, "Two-factor authentication feature coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        // Set default values
        locationSwitch.isChecked = true
        dataCollectionSwitch.isChecked = false
        
        Toast.makeText(this, "Privacy & Security page loaded", Toast.LENGTH_SHORT).show()
    }
}
