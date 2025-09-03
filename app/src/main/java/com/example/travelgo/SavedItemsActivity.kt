package com.example.travelgo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SavedItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_items)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Set up saved items RecyclerView
        setupSavedItemsRecyclerView()
        
        Toast.makeText(this, getString(R.string.saved_items_loaded), Toast.LENGTH_SHORT).show()
    }
    
    private fun setupSavedItemsRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.saved_items_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        val savedPackages = listOf(
            SavedPackage("Sigiriya Adventure", "Ancient rock fortress with stunning views", R.drawable.kandy),
            SavedPackage("Kandy Cultural Tour", "Cultural capital with temple of the tooth", R.drawable.kandy)
        )
        
        val adapter = SavedItemsAdapter(savedPackages)
        recyclerView.adapter = adapter
    }
    
    data class SavedPackage(
        val packageName: String,
        val description: String,
        val imageResource: Int
    )
    
    private inner class SavedItemsAdapter(
        private val savedPackages: List<SavedPackage>
    ) : RecyclerView.Adapter<SavedItemsAdapter.SavedPackageViewHolder>() {
        
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedPackageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_destination_vertical, parent, false)
            return SavedPackageViewHolder(view)
        }
        
        override fun onBindViewHolder(holder: SavedPackageViewHolder, position: Int) {
            holder.bind(savedPackages[position])
        }
        
        override fun getItemCount(): Int = savedPackages.size
        
        inner class SavedPackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val destinationImage: ImageView = itemView.findViewById(R.id.destination_image)
            private val destinationName: TextView = itemView.findViewById(R.id.destination_name)
            private val destinationDescription: TextView = itemView.findViewById(R.id.destination_description)
            private val heartIcon: ImageView = itemView.findViewById(R.id.heart_icon)
            private val savedIcon: ImageView = itemView.findViewById(R.id.saved_icon)
            
            fun bind(savedPackage: SavedPackage) {
                destinationImage.setImageResource(savedPackage.imageResource)
                destinationName.text = savedPackage.packageName
                destinationDescription.text = savedPackage.description
                

                heartIcon.setImageResource(R.drawable.ic_heart)
                heartIcon.setColorFilter(resources.getColor(R.color.primary, null))
                heartIcon.tag = "favorited"
                

                savedIcon.setImageResource(R.drawable.ic_bookmark)
                savedIcon.setColorFilter(resources.getColor(R.color.primary, null))
                savedIcon.tag = "saved"
                

                heartIcon.setOnClickListener {
                    // Remove from favorites
                    heartIcon.setImageResource(R.drawable.ic_heart_outline)
                    heartIcon.setColorFilter(resources.getColor(R.color.primary, null))
                    heartIcon.tag = "not_favorited"
                    Toast.makeText(this@SavedItemsActivity, getString(R.string.removed_from_favorites), Toast.LENGTH_SHORT).show()
                }
                

                savedIcon.setOnClickListener {
                    // Remove from saved items
                    savedIcon.setImageResource(R.drawable.ic_bookmark)
                    savedIcon.setColorFilter(resources.getColor(R.color.primary, null))
                    savedIcon.tag = "not_saved"
                    Toast.makeText(this@SavedItemsActivity, getString(R.string.removed_from_saved_items), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
