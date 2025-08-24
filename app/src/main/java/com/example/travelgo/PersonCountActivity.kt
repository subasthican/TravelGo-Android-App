package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PersonCountActivity : AppCompatActivity() {
    
    // Person count variables
    private lateinit var adultsCountText: TextView
    private lateinit var decreaseAdultsButton: ImageButton
    private lateinit var increaseAdultsButton: ImageButton
    private var adultsCount = 2
    
    // Data from previous activity
    private var destination: String = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_count)
        
        // Get data from previous activity
        destination = intent.getStringExtra("destination") ?: "Destination"
        
        // Initialize views
        initializeViews()
        
        // Set up back button
        setupBackButton()
        
        // Set up person count functionality
        setupPersonCountFunctionality()
        
        // Set up next button
        setupNextButton()
        
        // Update UI with received data
        updateUI()
    }
    
    private fun initializeViews() {
        adultsCountText = findViewById(R.id.adults_count)
        decreaseAdultsButton = findViewById(R.id.decrease_adults_button)
        increaseAdultsButton = findViewById(R.id.increase_adults_button)
    }
    
    private fun setupBackButton() {
        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            finish()
        }
    }
    
    private fun setupPersonCountFunctionality() {
        // Update display
        updateAdultsCount()
        
        // Decrease adults button
        decreaseAdultsButton.setOnClickListener {
            if (adultsCount > 1) {
                adultsCount--
                updateAdultsCount()
                Toast.makeText(this, "Adults: $adultsCount", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Minimum 1 adult required", Toast.LENGTH_SHORT).show()
            }
        }
        
        // Increase adults button
        increaseAdultsButton.setOnClickListener {
            if (adultsCount < 10) {
                adultsCount++
                updateAdultsCount()
                Toast.makeText(this, "Adults: $adultsCount", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Maximum 10 adults allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun setupNextButton() {
        findViewById<Button>(R.id.next_button).setOnClickListener {
            proceedToLocation()
        }
    }
    
    private fun updateAdultsCount() {
        adultsCountText.text = adultsCount.toString()
    }
    
    private fun updateUI() {
        // Update destination text
        findViewById<TextView>(R.id.destination_text).text = destination
    }
    
    private fun proceedToLocation() {
        // Navigate to location activity with collected data
        val intent = Intent(this, LocationActivity::class.java)
        intent.putExtra("destination", destination)
        intent.putExtra("adults_count", adultsCount)
        startActivity(intent)
        finish()
    }
}
