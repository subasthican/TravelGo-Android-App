package com.example.travelgo

import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HelpSupportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_support)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val faqOption = findViewById<LinearLayout>(R.id.faq_option)
        val contactSupportOption = findViewById<LinearLayout>(R.id.contact_support_option)
        val reportIssueOption = findViewById<LinearLayout>(R.id.report_issue_option)
        val userGuideOption = findViewById<LinearLayout>(R.id.user_guide_option)
        

        backButton.setOnClickListener {
            finish()
        }
        

        faqOption.setOnClickListener {
            Toast.makeText(this, "FAQ section coming soon!", Toast.LENGTH_SHORT).show()
        }
        

        contactSupportOption.setOnClickListener {
            Toast.makeText(this, "Contact support feature coming soon!", Toast.LENGTH_SHORT).show()
        }
        

        reportIssueOption.setOnClickListener {
            Toast.makeText(this, "Report issue feature coming soon!", Toast.LENGTH_SHORT).show()
        }
        

        userGuideOption.setOnClickListener {
            Toast.makeText(this, "User guide coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        Toast.makeText(this, "Help & Support page loaded", Toast.LENGTH_SHORT).show()
    }
}
