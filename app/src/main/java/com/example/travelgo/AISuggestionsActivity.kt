package com.example.travelgo

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AISuggestionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_suggestions)

        // Setup back button
        findViewById<ImageView>(R.id.back_button).setOnClickListener {
            finish()
        }

        // Setup favorite buttons
        setupFavoriteButton(R.id.favorite_button_1, "Nallur")
        setupFavoriteButton(R.id.favorite_button_2, "Sigiriya")
        setupFavoriteButton(R.id.favorite_button_3, "Kandy")

        // Setup view details buttons
        setupViewDetailsButton(R.id.view_details_button_1, "Nallur")
        setupViewDetailsButton(R.id.view_details_button_2, "Sigiriya")
        setupViewDetailsButton(R.id.view_details_button_3, "Kandy")
    }

    private fun setupFavoriteButton(buttonId: Int, destinationName: String) {
        findViewById<ImageView>(buttonId).setOnClickListener {
            val favoriteButton = findViewById<ImageView>(buttonId)
            if (favoriteButton.tag == "favorited") {
                favoriteButton.setImageResource(R.drawable.ic_heart)
                favoriteButton.setColorFilter(resources.getColor(R.color.gray_medium, null))
                favoriteButton.tag = "not_favorited"
                Toast.makeText(this, "Removed $destinationName from favorites", Toast.LENGTH_SHORT).show()
            } else {
                favoriteButton.setImageResource(R.drawable.ic_heart)
                favoriteButton.setColorFilter(resources.getColor(R.color.error, null))
                favoriteButton.tag = "favorited"
                Toast.makeText(this, "Added $destinationName to favorites", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupViewDetailsButton(buttonId: Int, destinationName: String) {
        findViewById<ImageView>(buttonId).setOnClickListener {
            Toast.makeText(this, "View $destinationName details", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to destination detail page
        }
    }
}
