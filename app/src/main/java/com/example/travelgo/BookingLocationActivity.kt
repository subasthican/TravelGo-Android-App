package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class BookingLocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_location)
        
        val memberCount = intent.getIntExtra("member_count", 1)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val pickupInput = findViewById<EditText>(R.id.pickup_location_input)
        val dropoffInput = findViewById<EditText>(R.id.dropoff_location_input)
        val nextButton = findViewById<Button>(R.id.next_button)
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Next button
        nextButton.setOnClickListener {
            val pickupLocation = pickupInput.text.toString()
            val dropoffLocation = dropoffInput.text.toString()
            
            if (pickupLocation.isNotEmpty() && dropoffLocation.isNotEmpty()) {
                val intent = Intent(this, BookingPaymentActivity::class.java)
                intent.putExtra("member_count", memberCount)
                intent.putExtra("pickup_location", pickupLocation)
                intent.putExtra("dropoff_location", dropoffLocation)
                startActivity(intent)
            } else {
                // Show error or validation message
                if (pickupLocation.isEmpty()) pickupInput.error = "Pickup location required"
                if (dropoffLocation.isEmpty()) dropoffInput.error = "Drop-off location required"
            }
        }
    }
}
