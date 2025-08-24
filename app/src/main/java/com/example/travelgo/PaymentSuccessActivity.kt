package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PaymentSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)
        
        val destination = intent.getStringExtra("destination") ?: "your trip"
        val paymentMethod = intent.getStringExtra("payment_method") ?: "selected payment method"
        
        // Set success message with destination details
        val successMessage = findViewById<TextView>(R.id.success_message)
        successMessage.text = "Payment successful for $destination!"
        
        val paymentMethodText = findViewById<TextView>(R.id.payment_method_text)
        paymentMethodText.text = "Paid with: $paymentMethod"
        
        // Set up continue button
        val continueButton = findViewById<Button>(R.id.continue_button)
        continueButton.setOnClickListener {
            // Navigate back to main activity
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        
        // Show success animation or message
        val successIcon = findViewById<ImageView>(R.id.success_icon)
        successIcon.alpha = 0f
        successIcon.animate()
            .alpha(1f)
            .setDuration(1000)
            .start()
    }
}
