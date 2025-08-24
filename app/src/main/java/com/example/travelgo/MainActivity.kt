package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Set up bottom navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_history -> {
                    loadFragment(HistoryFragment())
                    true
                }
                R.id.nav_booked_packages -> {
                    loadFragment(BookedPackagesFragment())
                    true
                }
                R.id.nav_profile -> {
                    // Navigate to ProfileActivity instead of loading ProfileFragment
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        
        // Check if we're coming from ProfileActivity with a specific tab selected
        val selectedTab = intent.getStringExtra("selected_tab")
        if (selectedTab != null) {
            when (selectedTab) {
                "history" -> {
                    loadFragment(HistoryFragment())
                    bottomNav.selectedItemId = R.id.nav_history
                }
                "booked_packages" -> {
                    loadFragment(BookedPackagesFragment())
                    bottomNav.selectedItemId = R.id.nav_booked_packages
                }
            }
        } else {
            // Load home fragment by default
            if (savedInstanceState == null) {
                loadFragment(HomeFragment())
            }
        }
    }
    
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}