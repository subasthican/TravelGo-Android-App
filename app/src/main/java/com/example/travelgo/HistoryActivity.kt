package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val titleText = findViewById<TextView>(R.id.title_text)
        
        // Set title
        titleText.text = "Recently Seen"
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Set up seen destinations with navigation
        setupSeenDestinations()
    }
    
    private fun setupSeenDestinations() {
        // Set up "Seen a minute ago" section - Sigiriya
        val seenMinuteAgo = findViewById<LinearLayout>(R.id.seen_minute_ago)
        seenMinuteAgo?.setOnClickListener {
            val intent = Intent(this, SigiriyaDetailActivity::class.java)
            startActivity(intent)
        }
        
        // Set up "Seen an hour ago" sections
        val seenHourAgo1 = findViewById<LinearLayout>(R.id.seen_hour_ago_1)
        seenHourAgo1?.setOnClickListener {
            val intent = Intent(this, NallurDetailActivity::class.java)
            startActivity(intent)
        }
        
        val seenHourAgo2 = findViewById<LinearLayout>(R.id.seen_hour_ago_2)
        seenHourAgo2?.setOnClickListener {
            val intent = Intent(this, EllaDetailActivity::class.java)
            startActivity(intent)
        }
        
        // Set up heart icon clicks for favorites
        setupHeartIconClicks()
    }
    
    private fun setupHeartIconClicks() {
        val heartIcons = listOf(
            findViewById<ImageView>(R.id.heart_sigiriya),
            findViewById<ImageView>(R.id.heart_nallur),
            findViewById<ImageView>(R.id.heart_ella)
        )
        
        heartIcons.forEach { heartIcon ->
            heartIcon?.setOnClickListener {
                // Toggle favorite state
                if (heartIcon.tag == "favorited") {
                    heartIcon.setImageResource(R.drawable.ic_heart)
                    heartIcon.tag = "not_favorited"
                    Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
                } else {
                    heartIcon.setImageResource(R.drawable.ic_heart)
                    heartIcon.tag = "favorited"
                    Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
