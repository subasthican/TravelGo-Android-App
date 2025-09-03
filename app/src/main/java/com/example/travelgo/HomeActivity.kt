package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView

class HomeActivity : AppCompatActivity() {
    
    private lateinit var popularDestinationsRecyclerView: RecyclerView
    private lateinit var nearestPlacesRecyclerView: RecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        

        setupRecyclerViews()
        

        setupClickListeners()
        

        setupFilterChips()
        
        setupBottomNavigation()
        

        addSampleData()
    }
    
    private fun setupBottomNavigation() {
        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)
        bottomNav?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {

                    true
                }
                R.id.nav_history -> {
                    val intent = Intent(this, HistoryActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_booked_packages -> {
                    val intent = Intent(this, BookedPackagesActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        

        bottomNav?.selectedItemId = R.id.nav_home
    }
    
    private fun setupRecyclerViews() {
        val popularRecycler = findViewById<RecyclerView>(R.id.popular_destinations_recycler)
        val nearestRecycler = findViewById<RecyclerView>(R.id.nearest_places_recycler)
        

        if (popularRecycler == null || nearestRecycler == null) {
            Toast.makeText(this, "Error: RecyclerViews not found", Toast.LENGTH_SHORT).show()
            return
        }
        
        popularDestinationsRecyclerView = popularRecycler
        nearestPlacesRecyclerView = nearestRecycler
        

        popularDestinationsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        nearestPlacesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
    
    private fun setupClickListeners() {

        findViewById<TextView>(R.id.view_all_button)?.setOnClickListener {
            val intent = Intent(this, AllDestinationsActivity::class.java)
            startActivity(intent)
        }
        

        findViewById<LinearLayout>(R.id.ai_chat_button)?.setOnClickListener {
            val intent = Intent(this, AIChatActivity::class.java)
            startActivity(intent)
        }
        

        findViewById<LinearLayout>(R.id.go_to_favorites_button)?.setOnClickListener {
            val intent = Intent(this, FavouritesActivity::class.java)
            startActivity(intent)
        }
        

        findViewById<LinearLayout>(R.id.search_section)?.setOnClickListener {
            Toast.makeText(this, "Search functionality coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun setupFilterChips() {
        val allChip = findViewById<TextView>(R.id.filter_all)
        val beachChip = findViewById<TextView>(R.id.filter_beach)
        val mountainChip = findViewById<TextView>(R.id.filter_mountain)
        val cityChip = findViewById<TextView>(R.id.filter_city)
        

        if (allChip == null || beachChip == null || mountainChip == null || cityChip == null) {
            Toast.makeText(this, "Error: Filter chips not found", Toast.LENGTH_SHORT).show()
            return
        }
        
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

        activeChip.setBackgroundResource(R.drawable.filter_button_selected)
        activeChip.setTextColor(resources.getColor(R.color.white, null))
        

        inactiveChip1.setBackgroundResource(R.drawable.filter_button_unselected)
        inactiveChip1.setTextColor(resources.getColor(R.color.gray_medium, null))
        
        inactiveChip2.setBackgroundResource(R.drawable.filter_button_unselected)
        inactiveChip2.setTextColor(resources.getColor(R.color.gray_medium, null))
        
        inactiveChip3.setBackgroundResource(R.drawable.filter_button_unselected)
        inactiveChip3.setTextColor(resources.getColor(R.color.gray_medium, null))
    }
    
    private fun filterDestinations(filterType: String) {

        val filterText = when (filterType) {
            "all" -> "Showing all destinations"
            "beach" -> "Showing beach destinations"
            "mountain" -> "Showing mountain destinations"
            "city" -> "Showing city destinations"
            else -> "Showing all destinations"
        }
        Toast.makeText(this, filterText, Toast.LENGTH_SHORT).show()
        

        val allDestinations = listOf(
            DestinationItem("Sigiriya", "Ancient Rock Fortress", R.drawable.kandy, "4.5", "$99"),
            DestinationItem("Kandy", "Cultural Capital", R.drawable.kandy, "4.3", "$89"),
            DestinationItem("Galle", "Coastal Fortress", R.drawable.galle, "4.4", "$79"),
            DestinationItem("Yala", "Wildlife Safari", R.drawable.yalla, "4.6", "$129"),
            DestinationItem("Polonnaruwa", "Ancient City", R.drawable.polonnaruwa, "4.2", "$69"),
            DestinationItem("Anuradhapura", "Sacred City", R.drawable.anurathapura, "4.1", "$59"),
            DestinationItem("Dambulla", "Cave Temple", R.drawable.dumbulla, "4.0", "$49"),
            DestinationItem("Mirissa", "Beach Paradise", R.drawable.mirissa, "4.3", "$89")
        )
        
        val filteredDestinations = when (filterType) {
            "beach" -> allDestinations.filter { it.name in listOf("Galle", "Mirissa") }
            "mountain" -> allDestinations.filter { it.name in listOf("Sigiriya", "Kandy") }
            "city" -> allDestinations.filter { it.name in listOf("Polonnaruwa", "Anuradhapura", "Dambulla") }
            else -> allDestinations
        }
        

        popularDestinationsRecyclerView.adapter = DestinationAdapter(filteredDestinations.take(4)) { destination ->

            when (destination.name) {
                "Sigiriya" -> startActivity(Intent(this, SigiriyaDetailActivity::class.java))
                "Kandy" -> startActivity(Intent(this, KandyDetailActivity::class.java))
                "Galle" -> startActivity(Intent(this, GalleDetailActivity::class.java))
                "Yala" -> startActivity(Intent(this, YalaDetailActivity::class.java))
                "Polonnaruwa" -> startActivity(Intent(this, PolonnaruwaDetailActivity::class.java))
                "Anuradhapura" -> startActivity(Intent(this, AnuradhapuraDetailActivity::class.java))
                "Dambulla" -> startActivity(Intent(this, DambullaDetailActivity::class.java))
                "Mirissa" -> startActivity(Intent(this, MirissaDetailActivity::class.java))
                else -> Toast.makeText(this, "Opening ${destination.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun addSampleData() {

        val popularDestinations = listOf(
            DestinationItem("Sigiriya", "Ancient Rock Fortress", R.drawable.sigiriya, "4.5", "$99"),
            DestinationItem("Kandy", "Cultural Capital", R.drawable.kandy, "4.3", "$89"),
            DestinationItem("Galle", "Coastal Fortress", R.drawable.galle, "4.4", "$79"),
            DestinationItem("Yala", "Wildlife Safari", R.drawable.yalla, "4.6", "$129")
        )
        
        val nearestPlaces = listOf(
            DestinationItem("Polonnaruwa", "Ancient City", R.drawable.polonnaruwa, "4.2", "$69"),
            DestinationItem("Anuradhapura", "Sacred City", R.drawable.anurathapura, "4.1", "$59"),
            DestinationItem("Dambulla", "Cave Temple", R.drawable.dumbulla, "4.0", "$49"),
            DestinationItem("Mirissa", "Beach Paradise", R.drawable.mirissa, "4.3", "$89")
        )
        

        popularDestinationsRecyclerView.adapter = DestinationAdapter(popularDestinations) { destination ->

            when (destination.name) {
                "Sigiriya" -> startActivity(Intent(this, SigiriyaDetailActivity::class.java))
                "Kandy" -> startActivity(Intent(this, KandyDetailActivity::class.java))
                "Galle" -> startActivity(Intent(this, GalleDetailActivity::class.java))
                "Yala" -> startActivity(Intent(this, YalaDetailActivity::class.java))
                else -> Toast.makeText(this, "Opening ${destination.name}", Toast.LENGTH_SHORT).show()
            }
        }
        
        nearestPlacesRecyclerView.adapter = DestinationAdapter(nearestPlaces) { destination ->

            when (destination.name) {
                "Polonnaruwa" -> startActivity(Intent(this, PolonnaruwaDetailActivity::class.java))
                "Anuradhapura" -> startActivity(Intent(this, AnuradhapuraDetailActivity::class.java))
                "Dambulla" -> startActivity(Intent(this, DambullaDetailActivity::class.java))
                "Mirissa" -> startActivity(Intent(this, MirissaDetailActivity::class.java))
                else -> Toast.makeText(this, "Opening ${destination.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    

    data class DestinationItem(
        val name: String,
        val description: String,
        val imageResId: Int,
        val rating: String,
        val price: String
    )
    

    inner class DestinationAdapter(
        private val destinations: List<DestinationItem>,
        private val onItemClick: (DestinationItem) -> Unit
    ) : RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder>() {
        
        inner class DestinationViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
            private val imageView: ImageView = itemView.findViewById(R.id.destination_image)
            private val nameText: TextView = itemView.findViewById(R.id.destination_name)
            private val descriptionText: TextView = itemView.findViewById(R.id.destination_description)
            private val heartIcon: ImageView = itemView.findViewById(R.id.heart_icon)
            private val savedIcon: ImageView = itemView.findViewById(R.id.saved_icon)
            
            fun bind(destination: DestinationItem) {
                imageView.setImageResource(destination.imageResId)
                nameText.text = destination.name
                descriptionText.text = destination.description
                

                heartIcon.setImageResource(R.drawable.ic_heart_outline)
                heartIcon.setColorFilter(resources.getColor(R.color.primary, null))
                heartIcon.tag = "not_favorited"
                

                savedIcon.setImageResource(R.drawable.ic_bookmark)
                savedIcon.setColorFilter(resources.getColor(R.color.primary, null))
                

                itemView.setOnClickListener { onItemClick(destination) }
                heartIcon.setOnClickListener {
                    val intent = Intent(this@HomeActivity, FavouritesActivity::class.java)
                    startActivity(intent)
                }
                savedIcon.setOnClickListener {
                    val intent = Intent(this@HomeActivity, SavedItemsActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        
        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): DestinationViewHolder {
            val view = layoutInflater.inflate(R.layout.item_destination_vertical, parent, false)
            return DestinationViewHolder(view)
        }
        
        override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
            holder.bind(destinations[position])
        }
        
        override fun getItemCount() = destinations.size
    }
}
