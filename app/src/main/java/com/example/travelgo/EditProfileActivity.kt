package com.example.travelgo

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val saveTextButton = findViewById<TextView>(R.id.save_text_button)
        val profileImage = findViewById<ImageView>(R.id.profile_image)
        val changePhotoButton = findViewById<Button>(R.id.change_photo_button)
        val firstNameEdit = findViewById<EditText>(R.id.first_name_edit)
        val lastNameEdit = findViewById<EditText>(R.id.last_name_edit)
        val emailEdit = findViewById<EditText>(R.id.email_edit)
        val phoneEdit = findViewById<EditText>(R.id.phone_edit)
        val dateOfBirthEdit = findViewById<EditText>(R.id.date_of_birth_edit)
        val locationEdit = findViewById<EditText>(R.id.location_edit)
        val saveButton = findViewById<Button>(R.id.save_button)
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Header save button
        saveTextButton.setOnClickListener {
            saveProfile()
        }
        
        // Change photo button
        changePhotoButton.setOnClickListener {
            Toast.makeText(this, "Photo gallery selection coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        // Date of birth picker
        dateOfBirthEdit.setOnClickListener {
            showDatePicker()
        }
        
        // Primary save button
        saveButton.setOnClickListener {
            saveProfile()
        }
        
        Toast.makeText(this, "Edit your profile information", Toast.LENGTH_SHORT).show()
    }
    
    private fun saveProfile() {
        val firstNameEdit = findViewById<EditText>(R.id.first_name_edit)
        val lastNameEdit = findViewById<EditText>(R.id.last_name_edit)
        val emailEdit = findViewById<EditText>(R.id.email_edit)
        val phoneEdit = findViewById<EditText>(R.id.phone_edit)
        val locationEdit = findViewById<EditText>(R.id.location_edit)
        
        val firstName = firstNameEdit.text.toString().trim()
        val lastName = lastNameEdit.text.toString().trim()
        val email = emailEdit.text.toString().trim()
        val phone = phoneEdit.text.toString().trim()
        val location = locationEdit.text.toString().trim()
        
        // Validation
        when {
            firstName.isEmpty() -> {
                firstNameEdit.error = "First name is required"
                firstNameEdit.requestFocus()
                return
            }
            lastName.isEmpty() -> {
                lastNameEdit.error = "Last name is required" 
                lastNameEdit.requestFocus()
                return
            }
            email.isEmpty() -> {
                emailEdit.error = "Email is required"
                emailEdit.requestFocus()
                return
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                emailEdit.error = "Please enter a valid email"
                emailEdit.requestFocus()
                return
            }
            phone.isEmpty() -> {
                phoneEdit.error = "Phone number is required"
                phoneEdit.requestFocus()
                return
            }
        }
        
        // In a real app, you would save this to a database or API
        Toast.makeText(this, "Profile updated successfully! ✓", Toast.LENGTH_SHORT).show()
        finish()
    }
    
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val monthNames = arrayOf(
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
                )
                val formattedDate = "${monthNames[selectedMonth]} $selectedDay, $selectedYear"
                findViewById<EditText>(R.id.date_of_birth_edit).setText(formattedDate)
            },
            year, month, day
        )
        
        // Set maximum date to today (can't be born in the future)
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        
        // Set minimum date to 100 years ago
        val minCalendar = Calendar.getInstance()
        minCalendar.add(Calendar.YEAR, -100)
        datePickerDialog.datePicker.minDate = minCalendar.timeInMillis
        
        datePickerDialog.show()
    }
}
