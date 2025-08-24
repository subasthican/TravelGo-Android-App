package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BookingMembersActivity : AppCompatActivity() {
    private var memberCount = 1
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_members)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val minusButton = findViewById<Button>(R.id.minus_button)
        val plusButton = findViewById<Button>(R.id.plus_button)
        val memberCountText = findViewById<TextView>(R.id.member_count_text)
        val nextButton = findViewById<Button>(R.id.next_button)
        
        // Update member count display
        updateMemberCountDisplay(memberCountText)
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Minus button
        minusButton.setOnClickListener {
            if (memberCount > 1) {
                memberCount--
                updateMemberCountDisplay(memberCountText)
            }
        }
        
        // Plus button
        plusButton.setOnClickListener {
            if (memberCount < 10) {
                memberCount++
                updateMemberCountDisplay(memberCountText)
            }
        }
        
        // Next button
        nextButton.setOnClickListener {
            val intent = Intent(this, BookingLocationActivity::class.java)
            intent.putExtra("member_count", memberCount)
            startActivity(intent)
        }
    }
    
    private fun updateMemberCountDisplay(textView: TextView) {
        textView.text = memberCount.toString()
    }
}
