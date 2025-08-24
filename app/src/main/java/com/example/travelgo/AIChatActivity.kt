package com.example.travelgo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AIChatActivity : AppCompatActivity() {
    
    private lateinit var chatInput: EditText
    private lateinit var sendButton: Button
    private lateinit var chatRecyclerView: RecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_chat)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        chatInput = findViewById(R.id.chat_input)
        sendButton = findViewById(R.id.send_button)
        chatRecyclerView = findViewById(R.id.chat_recycler_view)
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Send button
        sendButton.setOnClickListener {
            sendMessage()
        }
        
        // Set up chat recycler view
        setupChatRecyclerView()
        
        // Show welcome message
        showWelcomeMessage()
    }
    
    private fun setupChatRecyclerView() {
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        // Here you would set up a proper chat adapter
    }
    
    private fun sendMessage() {
        val message = chatInput.text.toString().trim()
        if (message.isNotEmpty()) {
            // Add user message to chat
            addMessageToChat("You", message, true)
            
            // Simulate AI response
            simulateAIResponse(message)
            
            // Clear input
            chatInput.text.clear()
        }
    }
    
    private fun addMessageToChat(sender: String, message: String, isUser: Boolean) {
        // Here you would add the message to your chat adapter
        Toast.makeText(this, "$sender: $message", Toast.LENGTH_SHORT).show()
    }
    
    private fun simulateAIResponse(userMessage: String) {
        // Simulate AI thinking
        Toast.makeText(this, "AI is thinking...", Toast.LENGTH_SHORT).show()
        
        // Simulate response delay
        sendButton.postDelayed({
            val aiResponse = when {
                userMessage.contains("sigiriya", ignoreCase = true) -> 
                    "Sigiriya is an ancient palace and fortress complex in Sri Lanka. It's known as the 'Lion Rock' and is a UNESCO World Heritage Site. Would you like to know about visiting times, ticket prices, or the best way to get there?"
                
                userMessage.contains("kandy", ignoreCase = true) -> 
                    "Kandy is the cultural capital of Sri Lanka, famous for the Temple of the Sacred Tooth Relic. It's located in the central highlands and offers beautiful mountain views. What would you like to know about Kandy?"
                
                userMessage.contains("weather", ignoreCase = true) -> 
                    "The weather in Sri Lanka varies by region. The west and south coasts are best visited from December to April, while the east coast is best from May to September. What specific area are you planning to visit?"
                
                userMessage.contains("best time", ignoreCase = true) -> 
                    "The best time to visit Sri Lanka depends on which coast you want to explore. Generally, December to April is great for the west and south, while May to September is ideal for the east coast. When are you planning your trip?"
                
                else -> "I'd be happy to help you plan your Sri Lanka adventure! You can ask me about destinations, weather, best times to visit, transportation, or any other travel questions. What would you like to know?"
            }
            
            addMessageToChat("TravelGo AI", aiResponse, false)
        }, 2000)
    }
    
    private fun showWelcomeMessage() {
        val welcomeMessage = "Hello! I'm your TravelGo AI assistant. I can help you plan your Sri Lanka adventure, answer questions about destinations, weather, transportation, and more. What would you like to know?"
        addMessageToChat("TravelGo AI", welcomeMessage, false)
    }
}
