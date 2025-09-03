package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val nextButton = findViewById<Button>(R.id.next_button)
        val emailInput = findViewById<EditText>(R.id.email_input)
        

        backButton.setOnClickListener {
            finish()
        }
        

        nextButton.setOnClickListener {
            val intent = Intent(this, ForgotPasswordVerificationActivity::class.java)
            startActivity(intent)
        }
    }
}
