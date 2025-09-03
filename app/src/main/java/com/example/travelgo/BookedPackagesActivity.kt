package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


class BookedPackagesActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booked_packages)
        

        

        setupBookedPackagesList()
        

        setupBottomNavigation()
    }
    
    private fun setupBookedPackagesList() {
        val recyclerView = findViewById<RecyclerView>(R.id.booked_packages_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        val bookedPackages = listOf(
            BookedPackage("Sigiriya Adventure", "Booked on: 15 Dec 2024", "Status: Confirmed", R.drawable.kandy),
            BookedPackage("Kandy Cultural Tour", "Booked on: 12 Dec 2024", "Status: Confirmed", R.drawable.kandy),
            BookedPackage("Galle Fort Experience", "Booked on: 10 Dec 2024", "Status: Confirmed", R.drawable.galle)
        )
        
        val adapter = BookedPackagesAdapter(bookedPackages)
        recyclerView.adapter = adapter
    }
    
    data class BookedPackage(
        val packageName: String,
        val bookingDate: String,
        val status: String,
        val imageResource: Int
    )
    
    private inner class BookedPackagesAdapter(
        private val bookedPackages: List<BookedPackage>
    ) : RecyclerView.Adapter<BookedPackagesAdapter.BookedPackageViewHolder>() {
        
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookedPackageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_booked_package, parent, false)
            return BookedPackageViewHolder(view)
        }
        
        override fun onBindViewHolder(holder: BookedPackageViewHolder, position: Int) {
            holder.bind(bookedPackages[position])
        }
        
        override fun getItemCount(): Int = bookedPackages.size
        
        inner class BookedPackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val packageImage: ImageView = itemView.findViewById(R.id.package_image)
            private val packageName: TextView = itemView.findViewById(R.id.package_name)
            private val bookingDate: TextView = itemView.findViewById(R.id.booking_date)
            private val status: TextView = itemView.findViewById(R.id.status)
            
            fun bind(bookedPackage: BookedPackage) {
                packageImage.setImageResource(bookedPackage.imageResource)
                packageName.text = bookedPackage.packageName
                bookingDate.text = bookedPackage.bookingDate
                status.text = bookedPackage.status
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
                    val intent = Intent(this, HistoryActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_booked_packages -> {

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
        
        bottomNav.selectedItemId = R.id.nav_booked_packages
    }
}
