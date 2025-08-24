package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {
    
    private var currentFilter = "all"
    private lateinit var destinationsRecyclerView: RecyclerView
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Set up all click listeners
        setupClickListeners(view)
        
        // Set up filter chips
        setupFilterChips(view)
        
        // Set up destinations RecyclerView
        setupDestinationsRecyclerView(view)
    }
    
    private fun setupClickListeners(view: View) {
        // View All Button
        view.findViewById<TextView>(R.id.view_all_button).setOnClickListener {
            val intent = Intent(requireContext(), AllDestinationsActivity::class.java)
            startActivity(intent)
        }
        
        // AI Chat Button
        view.findViewById<LinearLayout>(R.id.ai_chat_button).setOnClickListener {
            val intent = Intent(requireContext(), AIChatActivity::class.java)
            startActivity(intent)
        }
    }
    
    private fun setupFilterChips(view: View) {
        val allChip = view.findViewById<TextView>(R.id.filter_all)
        val beachChip = view.findViewById<TextView>(R.id.filter_beach)
        val mountainChip = view.findViewById<TextView>(R.id.filter_mountain)
        val cityChip = view.findViewById<TextView>(R.id.filter_city)
        
        allChip.setOnClickListener {
            setFilterActive(allChip, beachChip, mountainChip, cityChip, "all")
            filterDestinations("all")
        }
        
        beachChip.setOnClickListener {
            setFilterActive(beachChip, allChip, mountainChip, cityChip, "beach")
            filterDestinations("beach")
        }
        
        mountainChip.setOnClickListener {
            setFilterActive(mountainChip, allChip, beachChip, cityChip, "mountain")
            filterDestinations("mountain")
        }
        
        cityChip.setOnClickListener {
            setFilterActive(cityChip, allChip, beachChip, mountainChip, "city")
            filterDestinations("city")
        }
    }
    
    private fun setFilterActive(
        activeChip: TextView,
        inactiveChip1: TextView,
        inactiveChip2: TextView,
        inactiveChip3: TextView,
        filterType: String
    ) {
        currentFilter = filterType
        
        // Set active chip
        activeChip.setBackgroundResource(R.drawable.filter_button_selected)
        activeChip.setTextColor(resources.getColor(R.color.white, null))
        
        // Set inactive chips
        inactiveChip1.setBackgroundResource(R.drawable.filter_button_unselected)
        inactiveChip1.setTextColor(resources.getColor(R.color.gray_medium, null))
        
        inactiveChip2.setBackgroundResource(R.drawable.filter_button_unselected)
        inactiveChip2.setTextColor(resources.getColor(R.color.gray_medium, null))
        
        inactiveChip3.setBackgroundResource(R.drawable.filter_button_unselected)
        inactiveChip3.setTextColor(resources.getColor(R.color.gray_medium, null))
    }
    
    private fun filterDestinations(filterType: String) {
        when (filterType) {
            "all" -> {
                Toast.makeText(requireContext(), "Showing all destinations", Toast.LENGTH_SHORT).show()
                // Here you would show all destinations
            }
            "beach" -> {
                Toast.makeText(requireContext(), "Showing beach destinations", Toast.LENGTH_SHORT).show()
                // Here you would filter destinations to show only beaches
            }
            "mountain" -> {
                Toast.makeText(requireContext(), "Showing mountain destinations", Toast.LENGTH_SHORT).show()
                // Here you would filter destinations to show only mountains
            }
            "city" -> {
                Toast.makeText(requireContext(), "Showing city destinations", Toast.LENGTH_SHORT).show()
                // Here you would filter destinations to show only cities
            }
        }
    }
    
    private fun setupDestinationsRecyclerView(view: View) {
        destinationsRecyclerView = view.findViewById(R.id.destinations_recycler)
        destinationsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        
        // Create sample destination data with actual package images
        val destinations = listOf(
            Destination("Sigiriya, Sri Lanka", "Sigiriya, Sri Lanka", "4.8", R.drawable.sigiriya),
            Destination("Kandy, Sri Lanka", "Central Province", "4.7", R.drawable.kandy),
            Destination("Galle, Sri Lanka", "Southern Province", "4.6", R.drawable.galle),
            Destination("Polonnaruwa, Sri Lanka", "North Central Province", "4.5", R.drawable.polonnaruwa),
            Destination("Anuradhapura, Sri Lanka", "North Central Province", "4.4", R.drawable.anurathapura),
            Destination("Dambulla, Sri Lanka", "Central Province", "4.3", R.drawable.dumbulla),
            Destination("Yala, Sri Lanka", "Southern Province", "4.2", R.drawable.yalla),
            Destination("Mirissa, Sri Lanka", "Southern Province", "4.1", R.drawable.mirissa),
            Destination("Ella, Sri Lanka", "Uva Province", "4.0", R.drawable.ella),
            Destination("Nallur, Sri Lanka", "Jaffna", "4.9", R.drawable.nallur)
        )
        
        val adapter = DestinationsAdapter(destinations) { destination ->
            // Handle destination click
            when (destination.title) {
                "Sigiriya, Sri Lanka" -> {
                    val intent = Intent(requireContext(), SigiriyaDetailActivity::class.java)
                    startActivity(intent)
                }
                "Nallur, Sri Lanka" -> {
                    val intent = Intent(requireContext(), NallurDetailActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(requireContext(), "Opening ${destination.title}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        
        destinationsRecyclerView.adapter = adapter
    }
    
    // Data class for destinations
    data class Destination(
        val title: String,
        val location: String,
        val rating: String,
        val imageRes: Int
    )
    
    // Adapter for destinations RecyclerView
    inner class DestinationsAdapter(
        private val destinations: List<Destination>,
        private val onDestinationClick: (Destination) -> Unit
    ) : RecyclerView.Adapter<DestinationsAdapter.DestinationViewHolder>() {
        
        inner class DestinationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val destinationImage: ImageView = itemView.findViewById(R.id.destination_image)
            private val destinationTitle: TextView = itemView.findViewById(R.id.destination_title)
            private val destinationLocation: TextView = itemView.findViewById(R.id.destination_location)
            private val destinationRating: TextView = itemView.findViewById(R.id.destination_rating)
            private val bookmarkIcon: ImageView = itemView.findViewById(R.id.bookmark_icon)
            
            fun bind(destination: Destination) {
                destinationImage.setImageResource(destination.imageRes)
                destinationTitle.text = destination.title
                destinationLocation.text = destination.location
                destinationRating.text = destination.rating
                
                // Set up click listeners
                itemView.setOnClickListener { onDestinationClick(destination) }
                
                bookmarkIcon.setOnClickListener {
                    // Toggle bookmark state
                    val isBookmarked = bookmarkIcon.tag as? Boolean ?: false
                    if (isBookmarked) {
                        bookmarkIcon.setImageResource(R.drawable.ic_heart)
                        bookmarkIcon.tag = false
                        Toast.makeText(requireContext(), "${destination.title} removed from favorites", Toast.LENGTH_SHORT).show()
                    } else {
                        bookmarkIcon.setImageResource(R.drawable.ic_heart)
                        bookmarkIcon.tag = true
                        Toast.makeText(requireContext(), "${destination.title} added to favorites! ❤️", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_destination_card, parent, false)
            return DestinationViewHolder(view)
        }
        
        override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
            holder.bind(destinations[position])
        }
        
        override fun getItemCount(): Int = destinations.size
    }
}
