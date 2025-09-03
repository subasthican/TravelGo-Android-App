package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SigiriyaDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_sigiriya)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val bookmarkButton = findViewById<ImageButton>(R.id.bookmark_button)

        val bookNowButton = findViewById<Button>(R.id.book_now_button)
        

        backButton.setOnClickListener {
            finish()
        }
        

        bookmarkButton.setOnClickListener {
            val intent = Intent(this, SavedItemsActivity::class.java)
            startActivity(intent)
        }
        

        

        bookNowButton.setOnClickListener {
            val intent = Intent(this, PersonCountActivity::class.java)
            intent.putExtra("destination", "Sigiriya Adventure")
            startActivity(intent)
        }
        
        Toast.makeText(this, "Welcome to Sigiriya - The Lion Rock! 🦁", Toast.LENGTH_SHORT).show()
    }
}
