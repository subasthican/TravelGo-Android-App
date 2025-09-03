package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        

        val loginButton = findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener {

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        

        val registerLink = findViewById<TextView>(R.id.tv_register_link)
        if (registerLink != null) {
            registerLink.setOnClickListener {
                try {
                    Toast.makeText(this, "Opening registration page...", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this, "Error opening registration: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            Toast.makeText(this, "Register link not found!", Toast.LENGTH_LONG).show()
        }
        

        val forgotPasswordButton = findViewById<Button>(R.id.forgot_password_button)
        forgotPasswordButton?.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}
