package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class AIChatActivity : AppCompatActivity() {

    private lateinit var messageInput: EditText
    private lateinit var sendButton: ImageView
    private lateinit var chatContainer: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_chat)


        messageInput = findViewById(R.id.message_input)
        sendButton = findViewById(R.id.send_button)
        chatContainer = findViewById(R.id.chat_container)



        findViewById<ImageView>(R.id.back_button).setOnClickListener {
            finish()
        }



        // Setup send button
        sendButton.setOnClickListener {
            val intent = Intent(this, AISuggestionsActivity::class.java)
            startActivity(intent)
        }

        // Setup home button
        findViewById<ImageView>(R.id.home_button).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

}
