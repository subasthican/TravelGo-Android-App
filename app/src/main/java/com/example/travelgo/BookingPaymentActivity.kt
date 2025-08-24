package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class BookingPaymentActivity : AppCompatActivity() {
    
    private var selectedPaymentMethod: String? = null
    private lateinit var confirmPaymentButton: Button
    
    // Data from previous activity
    private var destination: String = ""
    private var adultsCount: Int = 1
    private var pickupLocation: String = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_payment)
        
        // Get data from previous activity
        destination = intent.getStringExtra("destination") ?: "Destination"
        adultsCount = intent.getIntExtra("adults_count", 1)
        pickupLocation = intent.getStringExtra("pickup_location") ?: "No pickup location"
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        confirmPaymentButton = findViewById<Button>(R.id.confirm_payment_button)
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Set up payment method item clicks
        setupPaymentMethodClicks()
        
        // Set up confirm payment button
        setupConfirmPaymentButton()
        
        // Initially disable confirm button until payment method is selected
        confirmPaymentButton.isEnabled = false
        confirmPaymentButton.alpha = 0.5f
    }
    
    private fun setupPaymentMethodClicks() {
        // Apple ID item
        findViewById<LinearLayout>(R.id.apple_id_item).setOnClickListener {
            selectPaymentMethod("Apple ID", R.id.apple_id_item)
        }
        
        // Mastercard item
        findViewById<LinearLayout>(R.id.mastercard_item).setOnClickListener {
            selectPaymentMethod("Master Card", R.id.mastercard_item)
        }
        
        // Visa item
        findViewById<LinearLayout>(R.id.visa_item).setOnClickListener {
            selectPaymentMethod("Visa", R.id.visa_item)
        }
        
        // Add Payment Method
        findViewById<LinearLayout>(R.id.add_payment_method).setOnClickListener {
            val intent = Intent(this, AddPaymentActivity::class.java)
            intent.putExtra("destination", destination) // You can get this from previous activity
            startActivity(intent)
        }
        
        // Set up delete buttons
        setupDeleteButtons()
    }
    
    private fun selectPaymentMethod(methodName: String, itemId: Int) {
        // Clear previous selection
        clearAllSelections()
        
        // Set new selection
        selectedPaymentMethod = methodName
        val selectedItem = findViewById<LinearLayout>(itemId)
        selectedItem.setBackgroundResource(R.drawable.filter_button_selected)
        
        // Show selection confirmation
        Toast.makeText(this, "$methodName selected for payment", Toast.LENGTH_SHORT).show()
        
        // Enable confirm button
        confirmPaymentButton.isEnabled = true
        confirmPaymentButton.alpha = 1.0f
        
        // Show selection indicator
        val selectionIndicator = findViewById<TextView>(R.id.selection_indicator)
        selectionIndicator.text = "Selected: $methodName"
        selectionIndicator.visibility = View.VISIBLE
    }
    
    private fun clearAllSelections() {
        val items = listOf(
            R.id.apple_id_item,
            R.id.mastercard_item,
            R.id.visa_item
        )
        
        items.forEach { itemId ->
            findViewById<LinearLayout>(itemId).setBackgroundResource(R.drawable.filter_button_unselected)
        }
    }
    
    private fun setupDeleteButtons() {
        // Apple ID delete
        findViewById<ImageButton>(R.id.apple_delete_button).setOnClickListener {
            showDeleteConfirmation("Apple ID")
        }
        
        // Mastercard delete
        findViewById<ImageButton>(R.id.mastercard_delete_button).setOnClickListener {
            showDeleteConfirmation("Master Card")
        }
        
        // Visa delete
        findViewById<ImageButton>(R.id.visa_delete_button).setOnClickListener {
            showDeleteConfirmation("Visa")
        }
    }
    
    private fun showDeleteConfirmation(methodName: String) {
        AlertDialog.Builder(this)
            .setTitle("Delete Payment Method")
            .setMessage("Are you sure you want to remove $methodName from your saved payment methods?")
            .setPositiveButton("Delete") { _, _ ->
                // Remove the payment method
                Toast.makeText(this, "$methodName removed successfully", Toast.LENGTH_SHORT).show()
                
                // If this was the selected method, clear selection
                if (selectedPaymentMethod == methodName) {
                    selectedPaymentMethod = null
                    confirmPaymentButton.isEnabled = false
                    confirmPaymentButton.alpha = 0.5f
                    findViewById<TextView>(R.id.selection_indicator).visibility = View.GONE
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun setupConfirmPaymentButton() {
        confirmPaymentButton.setOnClickListener {
            if (selectedPaymentMethod != null) {
                showPaymentConfirmation()
            } else {
                Toast.makeText(this, "Please select a payment method first", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun showPaymentConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Confirm Payment")
            .setMessage("Are you sure you want to proceed with payment using $selectedPaymentMethod?")
            .setPositiveButton("Confirm Payment") { _, _ ->
                processPayment()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun processPayment() {
        // Get selected location
        val selectedLocation = pickupLocation
        
        // Show processing message
        Toast.makeText(this, "Processing payment with $selectedPaymentMethod...", Toast.LENGTH_SHORT).show()
        
        // Simulate payment processing
        confirmPaymentButton.isEnabled = false
        confirmPaymentButton.text = "Processing..."
        
        // Simulate API call delay
        confirmPaymentButton.postDelayed({
            // Payment successful
            Toast.makeText(this, "Payment successful! 🎉", Toast.LENGTH_LONG).show()
            
            // Navigate to payment success screen
            val intent = Intent(this, PaymentSuccessActivity::class.java)
            intent.putExtra("destination", destination) // You can get this from previous activity
            intent.putExtra("payment_method", selectedPaymentMethod)
            intent.putExtra("pickup_location", selectedLocation)
            intent.putExtra("adults_count", adultsCount)
            startActivity(intent)
            finish()
        }, 2000)
    }
}
