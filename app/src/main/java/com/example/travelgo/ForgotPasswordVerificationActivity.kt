package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordVerificationActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_verification)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val nextButton = findViewById<Button>(R.id.next_button)
        val codeInput1 = findViewById<EditText>(R.id.code_input_1)
        val codeInput2 = findViewById<EditText>(R.id.code_input_2)
        val codeInput3 = findViewById<EditText>(R.id.code_input_3)
        val codeInput4 = findViewById<EditText>(R.id.code_input_4)
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Next button - go to new password step
        nextButton.setOnClickListener {
            val intent = Intent(this, ForgotPasswordNewPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}
