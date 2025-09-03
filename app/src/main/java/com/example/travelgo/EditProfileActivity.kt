package com.example.travelgo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val changePhotoButton = findViewById<Button>(R.id.change_photo_button)
        val saveButton = findViewById<Button>(R.id.save_button)
        

        backButton.setOnClickListener {
            finish()
        }
        

        

        changePhotoButton.setOnClickListener {
            Toast.makeText(this, "Photo change feature coming soon!", Toast.LENGTH_SHORT).show()
        }
        

        saveButton.setOnClickListener {
            Toast.makeText(this, "Profile saved successfully! ✓", Toast.LENGTH_SHORT).show()
            finish()
        }
        
        Toast.makeText(this, "Edit your profile information", Toast.LENGTH_SHORT).show()
    }
}
