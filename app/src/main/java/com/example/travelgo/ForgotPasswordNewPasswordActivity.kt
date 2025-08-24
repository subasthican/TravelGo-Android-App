package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordNewPasswordActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_new_password)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val completeButton = findViewById<Button>(R.id.complete_button)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val confirmPasswordInput = findViewById<EditText>(R.id.confirm_password_input)
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Complete button - show success and go back to login
        completeButton.setOnClickListener {
            Toast.makeText(this, "Password reset successful! Please login with your new password", Toast.LENGTH_LONG).show()
            
            // Go back to login
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
