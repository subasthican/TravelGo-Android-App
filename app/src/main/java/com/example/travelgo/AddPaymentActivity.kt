package com.example.travelgo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class AddPaymentActivity : AppCompatActivity() {
    
    private var selectedPaymentMethod: String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_payment)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val addButton = findViewById<Button>(R.id.add_button)
        
        // Get destination info from previous activity
        val destination = intent.getStringExtra("destination") ?: "your trip"
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Set up payment method selection
        setupPaymentMethodSelection()
        
        // Set up scan card functionality
        setupScanCard()
        
        // Set up add button
        setupAddButton(addButton, destination)
    }
    
    private fun setupPaymentMethodSelection() {
        val gpayButton = findViewById<CardView>(R.id.gpay_button)
        val applePayButton = findViewById<CardView>(R.id.apple_pay_button)
        val mastercardButton = findViewById<CardView>(R.id.mastercard_button)
        val visaButton = findViewById<CardView>(R.id.visa_button)
        
        gpayButton.setOnClickListener {
            selectPaymentMethod("Google Pay", gpayButton)
        }
        
        applePayButton.setOnClickListener {
            selectPaymentMethod("Apple Pay", applePayButton)
        }
        
        mastercardButton.setOnClickListener {
            selectPaymentMethod("Mastercard", mastercardButton)
        }
        
        visaButton.setOnClickListener {
            selectPaymentMethod("Visa", visaButton)
        }
    }
    
    private fun selectPaymentMethod(methodName: String, selectedButton: CardView) {
        // Clear previous selection
        clearAllSelections()
        
        // Set new selection
        selectedPaymentMethod = methodName
        selectedButton.setBackgroundResource(R.drawable.filter_button_selected)
        
        // Show selection confirmation
        Toast.makeText(this, "$methodName selected", Toast.LENGTH_SHORT).show()
    }
    
    private fun clearAllSelections() {
        val buttons = listOf(
            R.id.gpay_button,
            R.id.apple_pay_button,
            R.id.mastercard_button,
            R.id.visa_button
        )
        
        buttons.forEach { buttonId ->
            findViewById<CardView>(buttonId).setBackgroundResource(R.drawable.filter_button_unselected)
        }
    }
    
    private fun setupScanCard() {
        val scanCardLayout = findViewById<LinearLayout>(R.id.scan_card_layout)
        scanCardLayout.setOnClickListener {
            Toast.makeText(this, "Scan Card feature coming soon! 📱", Toast.LENGTH_SHORT).show()
            // Here you would implement camera-based card scanning
        }
    }
    
    private fun setupAddButton(addButton: Button, destination: String) {
        addButton.setOnClickListener {
            if (validateInputs()) {
                // Process the new payment method
                Toast.makeText(this, "Payment method added successfully! ✅", Toast.LENGTH_SHORT).show()
                
                // Return to previous screen
                finish()
            } else {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun validateInputs(): Boolean {
        val nameOnCard = findViewById<EditText>(R.id.name_on_card_input).text.toString()
        val cardNumber = findViewById<EditText>(R.id.card_number_input).text.toString()
        val expiryDate = findViewById<EditText>(R.id.expiry_date_input).text.toString()
        val securityCode = findViewById<EditText>(R.id.security_code_input).text.toString()
        val zipCode = findViewById<EditText>(R.id.zip_code_input).text.toString()
        
        // Check if all fields are filled
        if (nameOnCard.isEmpty()) {
            findViewById<EditText>(R.id.name_on_card_input).error = "Name is required"
            return false
        }
        
        if (cardNumber.isEmpty()) {
            findViewById<EditText>(R.id.card_number_input).error = "Card number is required"
            return false
        }
        
        if (expiryDate.isEmpty()) {
            findViewById<EditText>(R.id.expiry_date_input).error = "Expiry date is required"
            return false
        }
        
        if (securityCode.isEmpty()) {
            findViewById<EditText>(R.id.security_code_input).error = "Security code is required"
            return false
        }
        
        if (zipCode.isEmpty()) {
            findViewById<EditText>(R.id.zip_code_input).error = "ZIP code is required"
            return false
        }
        
        if (selectedPaymentMethod == null) {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
            return false
        }
        
        return true
    }
}
