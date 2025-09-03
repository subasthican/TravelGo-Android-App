package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PersonCountActivity : AppCompatActivity() {
    

    private lateinit var personsSpinner: Spinner
    private var personsCount = 2
    

    private var destination: String = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_count)
        

        destination = intent.getStringExtra("destination") ?: "Destination"
        

        initializeViews()
        

        setupBackButton()
        

        setupPersonCountFunctionality()
        

        setupNextButton()
        

        updateUI()
    }
    
    private fun initializeViews() {
        personsSpinner = findViewById(R.id.persons_spinner)
    }
    
    private fun setupBackButton() {
        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            finish()
        }
    }
    
    private fun setupPersonCountFunctionality() {
        // Set up spinner with numbers 1-10
        val numbers = (1..10).toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, numbers)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        personsSpinner.adapter = adapter
        
        // Set default selection to 2
        personsSpinner.setSelection(1) //
        
        // Handle spinner selection
        personsSpinner.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: View?, position: Int, id: Long) {
                personsCount = numbers[position]
                Toast.makeText(this@PersonCountActivity, "Selected: $personsCount persons", Toast.LENGTH_SHORT).show()
            }
            
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {

            }
        }
    }
    
    private fun setupNextButton() {
        findViewById<Button>(R.id.next_button).setOnClickListener {
            proceedToLocation()
        }
    }
    

    
    private fun updateUI() {

        findViewById<TextView>(R.id.destination_text).text = destination
    }
    
    private fun proceedToLocation() {
        val intent = Intent(this, BookingLocationActivity::class.java)
        intent.putExtra("destination", destination)
        intent.putExtra("persons_count", personsCount)
        startActivity(intent)
        finish()
    }
}
