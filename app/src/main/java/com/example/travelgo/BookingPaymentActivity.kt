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
    

    private var destination: String = ""
    private var adultsCount: Int = 1
    private var pickupLocation: String = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_payment)
        

        destination = intent.getStringExtra("destination") ?: "Destination"
        adultsCount = intent.getIntExtra("adults_count", 1)
        pickupLocation = intent.getStringExtra("pickup_location") ?: "No pickup location"
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        

        backButton.setOnClickListener {
            finish()
        }
        

        setupPaymentMethodClicks()
        

    }
    
    private fun setupPaymentMethodClicks() {

        findViewById<LinearLayout>(R.id.apple_id_item).setOnClickListener {
            selectPaymentMethod("Apple ID", R.id.apple_id_item)
        }
        

        findViewById<LinearLayout>(R.id.mastercard_item).setOnClickListener {
            selectPaymentMethod("Master Card", R.id.mastercard_item)
        }
        

        findViewById<LinearLayout>(R.id.visa_item).setOnClickListener {
            selectPaymentMethod("Visa", R.id.visa_item)
        }
        

        findViewById<LinearLayout>(R.id.add_payment_method).setOnClickListener {
            val intent = Intent(this, AddPaymentActivity::class.java)
            intent.putExtra("destination", destination) // You can get this from previous activity
            startActivity(intent)
        }
        

        setupDeleteButtons()
    }
    
    private fun selectPaymentMethod(methodName: String, itemId: Int) {

        proceedToPaymentSuccess(methodName)
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

        findViewById<ImageButton>(R.id.apple_delete_button).setOnClickListener {
            showDeleteConfirmation("Apple ID")
        }
        

        findViewById<ImageButton>(R.id.mastercard_delete_button).setOnClickListener {
            showDeleteConfirmation("Master Card")
        }
        

        findViewById<ImageButton>(R.id.visa_delete_button).setOnClickListener {
            showDeleteConfirmation("Visa")
        }
    }
    
    private fun showDeleteConfirmation(methodName: String) {
        AlertDialog.Builder(this)
            .setTitle("Delete Payment Method")
            .setMessage("Are you sure you want to remove $methodName from your saved payment methods?")
            .setPositiveButton("Delete") { _, _ ->

                Toast.makeText(this, "$methodName removed successfully", Toast.LENGTH_SHORT).show()
                

                if (selectedPaymentMethod == methodName) {
                    selectedPaymentMethod = null
                    findViewById<TextView>(R.id.selection_indicator).visibility = View.GONE
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun proceedToPaymentSuccess(methodName: String) {

        val intent = Intent(this, PaymentSuccessActivity::class.java)
        intent.putExtra("destination", destination)
        intent.putExtra("payment_method", methodName)
        intent.putExtra("pickup_location", pickupLocation)
        intent.putExtra("adults_count", adultsCount)
        startActivity(intent)
        finish()
    }
}
