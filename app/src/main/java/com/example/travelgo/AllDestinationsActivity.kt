package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

class AllDestinationsActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_destinations)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val titleText = findViewById<TextView>(R.id.title_text)
        
        // Set title
        titleText.text = "All Destinations"
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Set up destinations list
        setupDestinationsList()
    }
    
    private fun setupDestinationsList() {
        val recyclerView = findViewById<RecyclerView>(R.id.destinations_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        val destinations = listOf(
            Destination("Sigiriya, Sri Lanka", "Ancient rock fortress", R.drawable.sigiriya, "SigiriyaDetailActivity"),
            Destination("Kandy, Sri Lanka", "Cultural capital", R.drawable.kandy, "KandyDetailActivity"),
            Destination("Galle, Sri Lanka", "Coastal fortress city", R.drawable.galle, "GalleDetailActivity"),
            Destination("Polonnaruwa, Sri Lanka", "Ancient kingdom ruins", R.drawable.polonnaruwa, "PolonnaruwaDetailActivity"),
            Destination("Anuradhapura, Sri Lanka", "Sacred city", R.drawable.anurathapura, "AnuradhapuraDetailActivity"),
            Destination("Dambulla, Sri Lanka", "Cave temple complex", R.drawable.dumbulla, "DambullaDetailActivity"),
            Destination("Yala, Sri Lanka", "Wildlife sanctuary", R.drawable.yalla, "YallaDetailActivity"),
            Destination("Mirissa, Sri Lanka", "Beach paradise", R.drawable.mirissa, "MirissaDetailActivity"),
            Destination("Ella, Sri Lanka", "Mountain retreat", R.drawable.ella, "EllaDetailActivity"),
            Destination("Nallur, Sri Lanka", "Temple town", R.drawable.nallur, "NallurDetailActivity")
        )
        
        val adapter = DestinationsAdapter(destinations) { destination ->
            navigateToDestination(destination)
        }
        recyclerView.adapter = adapter
    }
    
    private fun navigateToDestination(destination: Destination) {
        try {
            val activityClass = Class.forName("com.example.travelgo.${destination.activityName}")
            val intent = Intent(this, activityClass)
            startActivity(intent)
        } catch (e: Exception) {
            // Fallback to generic details activity
            val intent = Intent(this, SigiriyaDetailActivity::class.java)
            startActivity(intent)
        }
    }
    
    data class Destination(
        val name: String,
        val description: String,
        val imageResource: Int,
        val activityName: String
    )
    
    private inner class DestinationsAdapter(
        private val destinations: List<Destination>,
        private val onDestinationClick: (Destination) -> Unit
    ) : RecyclerView.Adapter<DestinationsAdapter.DestinationViewHolder>() {
        
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_destination_vertical, parent, false)
            return DestinationViewHolder(view)
        }
        
        override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
            holder.bind(destinations[position])
        }
        
        override fun getItemCount(): Int = destinations.size
        
        inner class DestinationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val destinationImage: ImageView = itemView.findViewById(R.id.destination_image)
            private val destinationName: TextView = itemView.findViewById(R.id.destination_name)
            private val destinationDescription: TextView = itemView.findViewById(R.id.destination_description)
            private val heartIcon: ImageView = itemView.findViewById(R.id.heart_icon)
            
            fun bind(destination: Destination) {
                destinationImage.setImageResource(destination.imageResource)
                destinationName.text = destination.name
                destinationDescription.text = destination.description
                
                itemView.setOnClickListener {
                    onDestinationClick(destination)
                }
                
                heartIcon.setOnClickListener {
                    // Toggle favorite state
                    heartIcon.setImageResource(R.drawable.ic_heart)
                }
            }
        }
    }
}
