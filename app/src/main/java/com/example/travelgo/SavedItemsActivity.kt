package com.example.travelgo

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SavedItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_items)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        Toast.makeText(this, "Saved items page loaded", Toast.LENGTH_SHORT).show()
    }
}
