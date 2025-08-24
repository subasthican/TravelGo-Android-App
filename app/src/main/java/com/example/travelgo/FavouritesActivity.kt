package com.example.travelgo

import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FavouritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val emptyState = findViewById<LinearLayout>(R.id.empty_state)
        val favouritesList = findViewById<ScrollView>(R.id.favourites_list)
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // For now, show empty state
        // In a real app, you would load favourites from database/preferences
        emptyState.visibility = LinearLayout.VISIBLE
        favouritesList.visibility = ScrollView.GONE
        
        Toast.makeText(this, "Favourites page loaded", Toast.LENGTH_SHORT).show()
    }
}
