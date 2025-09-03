package com.example.travelgo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class CurrencySettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_settings)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val currencyRadioGroup = findViewById<RadioGroup>(R.id.currency_radio_group)
        val saveButton = findViewById<Button>(R.id.save_button)
        

        backButton.setOnClickListener {
            finish()
        }
        

        saveButton.setOnClickListener {
            val selectedId = currencyRadioGroup.checkedRadioButtonId
            if (selectedId != -1) {
                val selectedCurrency = findViewById<RadioButton>(selectedId).text
                Toast.makeText(this, "Currency changed to: $selectedCurrency", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please select a currency", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
