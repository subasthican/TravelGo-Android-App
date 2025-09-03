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
        
        val personsCount = intent.getIntExtra("persons_count", 1)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val pickupInput = findViewById<EditText>(R.id.pickup_location_input)
        val nextButton = findViewById<Button>(R.id.next_button)
        

        backButton.setOnClickListener {
            finish()
        }
        

        nextButton.setOnClickListener {
            val intent = Intent(this, BookingPaymentActivity::class.java)
            intent.putExtra("persons_count", personsCount)
            intent.putExtra("pickup_location", "Default Location")
            startActivity(intent)
        }
    }
}
