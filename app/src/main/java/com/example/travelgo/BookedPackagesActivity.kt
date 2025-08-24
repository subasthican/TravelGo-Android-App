package com.example.travelgo

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

class BookedPackagesActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booked_packages)
        
        val backButton = findViewById<ImageButton>(R.id.back_button)
        val titleText = findViewById<TextView>(R.id.title_text)
        
        // Set title
        titleText.text = "Booked Packages"
        
        // Back button
        backButton.setOnClickListener {
            finish()
        }
        
        // Set up booked packages list
        setupBookedPackagesList()
    }
    
    private fun setupBookedPackagesList() {
        val recyclerView = findViewById<RecyclerView>(R.id.booked_packages_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        val bookedPackages = listOf(
            BookedPackage("Sigiriya Adventure", "Booked on: 15 Dec 2024", "Status: Confirmed", R.drawable.placeholder_sigiriya),
            BookedPackage("Kandy Cultural Tour", "Booked on: 12 Dec 2024", "Status: Confirmed", R.drawable.placeholder_sigiriya),
            BookedPackage("Galle Fort Experience", "Booked on: 10 Dec 2024", "Status: Confirmed", R.drawable.placeholder_sigiriya)
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
}
