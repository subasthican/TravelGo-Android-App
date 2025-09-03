package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AISuggestionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_suggestions)

        findViewById<ImageView>(R.id.back_button).setOnClickListener {
            finish()
        }


        setupFavoriteButton(R.id.favorite_button_1, "Nallur")
        setupFavoriteButton(R.id.favorite_button_2, "Sigiriya")
        setupFavoriteButton(R.id.favorite_button_3, "Kandy")


        setupViewDetailsButton(R.id.view_details_button_1, "Nallur", NallurDetailActivity::class.java)
        setupViewDetailsButton(R.id.view_details_button_2, "Sigiriya", SigiriyaDetailActivity::class.java)
        setupViewDetailsButton(R.id.view_details_button_3, "Kandy", KandyDetailActivity::class.java)

        findViewById<ImageView>(R.id.home_button).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
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

    private fun setupViewDetailsButton(buttonId: Int, destinationName: String, activityClass: Class<*>) {
        findViewById<ImageView>(buttonId).setOnClickListener {
            val intent = Intent(this, activityClass)
            startActivity(intent)
        }
    }
}
