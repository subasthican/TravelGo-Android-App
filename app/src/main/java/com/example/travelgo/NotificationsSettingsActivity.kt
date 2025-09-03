package com.example.travelgo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NotificationsSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications_settings)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val pushNotificationsSwitch = findViewById<Switch>(R.id.push_notifications_switch)
        val emailNotificationsSwitch = findViewById<Switch>(R.id.email_notifications_switch)
        val bookingUpdatesSwitch = findViewById<Switch>(R.id.booking_updates_switch)
        val promotionalSwitch = findViewById<Switch>(R.id.promotional_switch)
        val saveButton = findViewById<Button>(R.id.save_button)
        

        backButton.setOnClickListener {
            finish()
        }
        

        saveButton.setOnClickListener {
            Toast.makeText(this, "Notification settings saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
        

        pushNotificationsSwitch.isChecked = true
        emailNotificationsSwitch.isChecked = true
        bookingUpdatesSwitch.isChecked = true
        promotionalSwitch.isChecked = false
    }
}
