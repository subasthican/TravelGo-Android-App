package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AIChatActivity : AppCompatActivity() {

    private lateinit var messageInput: EditText
    private lateinit var sendButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_chat)

        // Initialize views
        messageInput = findViewById(R.id.message_input)
        sendButton = findViewById(R.id.send_button)

        // Setup back button
        findViewById<ImageView>(R.id.back_button).setOnClickListener {
            finish()
        }

        // Setup continue button
        findViewById<Button>(R.id.continue_button).setOnClickListener {
            val intent = Intent(this, AISuggestionsActivity::class.java)
            startActivity(intent)
        }



        // Setup send button
        sendButton.setOnClickListener {
            val message = messageInput.text.toString().trim()
            if (message.isNotEmpty()) {
                // TODO: Implement AI chat functionality
                Toast.makeText(this, "AI Chat coming soon!", Toast.LENGTH_SHORT).show()
                messageInput.text.clear()
            }
        }


    }
}
