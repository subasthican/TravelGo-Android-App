package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView


class HistoryActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        

        

        setupHistorySections()
        

        setupBottomNavigation()
    }
    
    private fun setupHistorySections() {

        val seenMinuteAgoRecycler = findViewById<RecyclerView>(R.id.seen_minute_ago_recycler)
        seenMinuteAgoRecycler.layoutManager = LinearLayoutManager(this)
        
        val seenMinuteAgoPackages = listOf(
            HistoryPackage("Sigiriya, Sri Lanka", "Ancient rock fortress", R.drawable.kandy)
        )
        
        val minuteAdapter = HistoryPackagesAdapter(seenMinuteAgoPackages)
        seenMinuteAgoRecycler.adapter = minuteAdapter
        

        val seenHourAgoRecycler = findViewById<RecyclerView>(R.id.seen_hour_ago_recycler)
        seenHourAgoRecycler.layoutManager = LinearLayoutManager(this)
        
        val seenHourAgoPackages = listOf(
            HistoryPackage("Nallur Temple, Sri Lanka", "Historic Hindu temple", R.drawable.nallur),
            HistoryPackage("Ella, Sri Lanka", "Beautiful tea plantations", R.drawable.ella)
        )
        
        val hourAdapter = HistoryPackagesAdapter(seenHourAgoPackages)
        seenHourAgoRecycler.adapter = hourAdapter
    }
    
    data class HistoryPackage(
        val packageName: String,
        val description: String,
        val imageResource: Int
    )
    
    private inner class HistoryPackagesAdapter(
        private val historyPackages: List<HistoryPackage>
    ) : RecyclerView.Adapter<HistoryPackagesAdapter.HistoryPackageViewHolder>() {
        
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryPackageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_destination_vertical, parent, false)
            return HistoryPackageViewHolder(view)
        }
        
        override fun onBindViewHolder(holder: HistoryPackageViewHolder, position: Int) {
            holder.bind(historyPackages[position])
        }
        
        override fun getItemCount(): Int = historyPackages.size
        
        inner class HistoryPackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val destinationImage: ImageView = itemView.findViewById(R.id.destination_image)
            private val destinationName: TextView = itemView.findViewById(R.id.destination_name)
            private val destinationDescription: TextView = itemView.findViewById(R.id.destination_description)
            private val heartIcon: ImageView = itemView.findViewById(R.id.heart_icon)
            private val savedIcon: ImageView = itemView.findViewById(R.id.saved_icon)
            
            fun bind(historyPackage: HistoryPackage) {
                destinationImage.setImageResource(historyPackage.imageResource)
                destinationName.text = historyPackage.packageName
                destinationDescription.text = historyPackage.description
                

                heartIcon.setOnClickListener {
                    val intent = Intent(this@HistoryActivity, FavouritesActivity::class.java)
                    startActivity(intent)
                }
                

                heartIcon.setImageResource(R.drawable.ic_heart_outline)
                heartIcon.setColorFilter(resources.getColor(R.color.primary, null))
                heartIcon.tag = "not_favorited"
                

                savedIcon.setOnClickListener {
                    val intent = Intent(this@HistoryActivity, SavedItemsActivity::class.java)
                    startActivity(intent)
                }
                

                savedIcon.setImageResource(R.drawable.ic_bookmark)
                savedIcon.setColorFilter(resources.getColor(R.color.primary, null))
            }
        }
    }
    
    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_history -> {

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
        

        bottomNav.selectedItemId = R.id.nav_history
    }
}
