package com.example.travelgo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LanguageSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_settings)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val languageRadioGroup = findViewById<RadioGroup>(R.id.language_radio_group)
        val saveButton = findViewById<Button>(R.id.save_button)
        

        backButton.setOnClickListener {
            finish()
        }
        

        saveButton.setOnClickListener {
            val selectedId = languageRadioGroup.checkedRadioButtonId
            if (selectedId != -1) {
                val selectedLanguage = findViewById<RadioButton>(selectedId).text
                Toast.makeText(this, "Language changed to: $selectedLanguage", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please select a language", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
