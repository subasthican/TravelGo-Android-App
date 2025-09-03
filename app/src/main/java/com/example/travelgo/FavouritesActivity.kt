package com.example.travelgo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavouritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        

        backButton.setOnClickListener {
            finish()
        }
        

        setupFavouritesRecyclerView()
        
        Toast.makeText(this, "Favourites page loaded with sample data", Toast.LENGTH_SHORT).show()
    }
    
    private fun setupFavouritesRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.favourites_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        val favouritePackages = listOf(
            FavouritePackage("Sigiriya Adventure", "Ancient rock fortress with stunning views", R.drawable.kandy),
            FavouritePackage("Kandy Cultural Tour", "Cultural capital with temple of the tooth", R.drawable.kandy)
        )
        
        val adapter = FavouritesAdapter(favouritePackages)
        recyclerView.adapter = adapter
    }
    
    data class FavouritePackage(
        val packageName: String,
        val description: String,
        val imageResource: Int
    )
    
    private inner class FavouritesAdapter(
        private val favouritePackages: List<FavouritePackage>
    ) : RecyclerView.Adapter<FavouritesAdapter.FavouritePackageViewHolder>() {
        
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritePackageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_destination_vertical, parent, false)
            return FavouritePackageViewHolder(view)
        }
        
        override fun onBindViewHolder(holder: FavouritePackageViewHolder, position: Int) {
            holder.bind(favouritePackages[position])
        }
        
        override fun getItemCount(): Int = favouritePackages.size
        
        inner class FavouritePackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val destinationImage: ImageView = itemView.findViewById(R.id.destination_image)
            private val destinationName: TextView = itemView.findViewById(R.id.destination_name)
            private val destinationDescription: TextView = itemView.findViewById(R.id.destination_description)
            private val heartIcon: ImageView = itemView.findViewById(R.id.heart_icon)
            
            fun bind(favouritePackage: FavouritePackage) {
                destinationImage.setImageResource(favouritePackage.imageResource)
                destinationName.text = favouritePackage.packageName
                destinationDescription.text = favouritePackage.description
                

                heartIcon.setImageResource(R.drawable.ic_heart)
                heartIcon.setColorFilter(resources.getColor(R.color.primary, null))
                heartIcon.tag = "favorited"
                

                heartIcon.setOnClickListener {

                    heartIcon.setImageResource(R.drawable.ic_heart_outline)
                    heartIcon.setColorFilter(resources.getColor(R.color.primary, null))
                    heartIcon.tag = "not_favorited"
                    Toast.makeText(this@FavouritesActivity, "Removed from favorites", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
