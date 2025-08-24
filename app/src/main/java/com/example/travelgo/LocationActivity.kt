package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LocationActivity : AppCompatActivity() {
    
    // Data from previous activity
    private var destination: String = ""
    private var adultsCount: Int = 1
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        
        // Get data from previous activity
        destination = intent.getStringExtra("destination") ?: "Destination"
        adultsCount = intent.getIntExtra("adults_count", 1)
        
        // Set up back button
        setupBackButton()
        
        // Set up next button - SIMPLIFIED: Just navigate directly
        setupNextButton()
        
        // Update UI with received data
        updateUI()
    }
    
    private fun setupBackButton() {
        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            finish()
        }
    }
    
    private fun setupNextButton() {
        // SIMPLIFIED: Just navigate directly without complex logic
        findViewById<Button>(R.id.next_button).setOnClickListener {
            proceedToPayment()
        }
    }
    
    private fun updateUI() {
        // Update destination text
        findViewById<TextView>(R.id.destination_text).text = destination
        
        // Update adults count text
        findViewById<TextView>(R.id.adults_count_text).text = "$adultsCount Adults"
    }
    
    private fun proceedToPayment() {
        // SIMPLIFIED: Use default location, no complex selection needed
        val selectedLocation = "Colombo International Airport, Sri Lanka"
        
        // Navigate to payment activity with all collected data
        val intent = Intent(this, BookingPaymentActivity::class.java)
        intent.putExtra("destination", destination)
        intent.putExtra("adults_count", adultsCount)
        intent.putExtra("pickup_location", selectedLocation)
        startActivity(intent)
        finish()
    }
}
