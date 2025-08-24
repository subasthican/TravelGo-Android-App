package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        
        // Initialize views
        val editProfileButton = findViewById<Button>(R.id.edit_profile_button)
        val favouritesOption = findViewById<LinearLayout>(R.id.favourites_option)
        val savedOption = findViewById<LinearLayout>(R.id.saved_option)
        val languageOption = findViewById<LinearLayout>(R.id.language_option)
        val currencyOption = findViewById<LinearLayout>(R.id.currency_option)
        val notificationsOption = findViewById<LinearLayout>(R.id.notifications_option)
        val privacyOption = findViewById<LinearLayout>(R.id.privacy_option)
        val helpOption = findViewById<LinearLayout>(R.id.help_option)
        val aboutOption = findViewById<LinearLayout>(R.id.about_option)
        val clearCacheOption = findViewById<LinearLayout>(R.id.clear_cache_option)
        val clearHistoryOption = findViewById<LinearLayout>(R.id.clear_history_option)
        val logoutOption = findViewById<LinearLayout>(R.id.logout_option)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        
        // Set up bottom navigation
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Navigate to MainActivity (home)
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    true
                }
                R.id.nav_history -> {
                    // Navigate to MainActivity with history tab
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("selected_tab", "history")
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    true
                }
                R.id.nav_booked_packages -> {
                    // Navigate to MainActivity with booked packages tab
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("selected_tab", "booked_packages")
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    true
                }
                R.id.nav_profile -> {
                    // Already on profile page
                    true
                }
                else -> false
            }
        }
        
        // Set profile tab as selected
        bottomNavigation.selectedItemId = R.id.nav_profile
        
        // Edit Profile Button
        editProfileButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
        
        // Profile Options
        favouritesOption.setOnClickListener {
            val intent = Intent(this, FavouritesActivity::class.java)
            startActivity(intent)
        }
        
        savedOption.setOnClickListener {
            val intent = Intent(this, SavedItemsActivity::class.java)
            startActivity(intent)
        }
        
        languageOption.setOnClickListener {
            val intent = Intent(this, LanguageSettingsActivity::class.java)
            startActivity(intent)
        }
        
        currencyOption.setOnClickListener {
            val intent = Intent(this, CurrencySettingsActivity::class.java)
            startActivity(intent)
        }
        
        notificationsOption.setOnClickListener {
            val intent = Intent(this, NotificationsSettingsActivity::class.java)
            startActivity(intent)
        }
        
        privacyOption.setOnClickListener {
            val intent = Intent(this, PrivacySecurityActivity::class.java)
            startActivity(intent)
        }
        
        helpOption.setOnClickListener {
            val intent = Intent(this, HelpSupportActivity::class.java)
            startActivity(intent)
        }
        
        aboutOption.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        
        clearCacheOption.setOnClickListener {
            // Show cleared status
            val clearedText = findViewById<TextView>(R.id.cleared_text)
            val checkmarkIcon = findViewById<ImageButton>(R.id.checkmark_icon)
            clearedText.visibility = TextView.VISIBLE
            checkmarkIcon.visibility = ImageButton.VISIBLE
            Toast.makeText(this, "Cache cleared successfully!", Toast.LENGTH_SHORT).show()
        }
        
        clearHistoryOption.setOnClickListener {
            Toast.makeText(this, "History cleared", Toast.LENGTH_SHORT).show()
        }
        
        logoutOption.setOnClickListener {
            // Navigate back to login
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
