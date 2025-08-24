package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AnuradhapuraDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_anuradhapura)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val bookmarkButton = findViewById<ImageButton>(R.id.bookmark_button)
        val shareButton = findViewById<ImageButton>(R.id.share_button)
        val bookNowButton = findViewById<Button>(R.id.book_now_button)
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Bookmark button
        bookmarkButton.setOnClickListener {
            Toast.makeText(this, "Anuradhapura added to favorites! ❤️", Toast.LENGTH_SHORT).show()
        }
        
        // Share button
        shareButton.setOnClickListener {
            Toast.makeText(this, "Sharing Anuradhapura details...", Toast.LENGTH_SHORT).show()
        }
        
        // Book Now button
        bookNowButton.setOnClickListener {
            val intent = Intent(this, BookingMembersActivity::class.java)
            intent.putExtra("destination", "Anuradhapura")
            intent.putExtra("destination_type", "Cultural Heritage")
            startActivity(intent)
        }
        
        Toast.makeText(this, "Welcome to Anuradhapura - Sacred City of Buddhism! 🌳", Toast.LENGTH_SHORT).show()
    }
}
