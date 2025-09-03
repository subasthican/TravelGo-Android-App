package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            // Set content view
            setContentView(R.layout.activity_register)
            Toast.makeText(this, "Register page loaded successfully!", Toast.LENGTH_SHORT).show()
            
            // Set up register button
            val registerButton = findViewById<Button>(R.id.register_button)
            if (registerButton != null) {
                registerButton.setOnClickListener {
                    Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Register button not found!", Toast.LENGTH_LONG).show()
            }
            
            // Set up login link
            val loginLink = findViewById<TextView>(R.id.tv_login_link)
            if (loginLink != null) {
                loginLink.setOnClickListener {
                    Toast.makeText(this, "Going back to login...", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                Toast.makeText(this, "Login link not found!", Toast.LENGTH_LONG).show()
            }
            
        } catch (e: Exception) {
            Toast.makeText(this, "Error loading register page: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
