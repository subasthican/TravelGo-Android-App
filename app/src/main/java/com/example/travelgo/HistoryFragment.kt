package com.example.travelgo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.ImageView

class HistoryFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Set up history sections with vertical package lists
        setupHistorySections(view)
    }
    
    private fun setupHistorySections(view: View) {
        // Set up "Seen a minute ago" section
        val seenMinuteAgoRecycler = view.findViewById<RecyclerView>(R.id.seen_minute_ago_recycler)
        seenMinuteAgoRecycler.layoutManager = LinearLayoutManager(requireContext())
        
        val seenMinuteAgoPackages = listOf(
            HistoryPackage("Sigiriya, Sri Lanka", "Ancient rock fortress", R.drawable.placeholder_sigiriya)
        )
        
        val minuteAdapter = HistoryPackagesAdapter(seenMinuteAgoPackages)
        seenMinuteAgoRecycler.adapter = minuteAdapter
        
        // Set up "Seen an hour ago" section
        val seenHourAgoRecycler = view.findViewById<RecyclerView>(R.id.seen_hour_ago_recycler)
        seenHourAgoRecycler.layoutManager = LinearLayoutManager(requireContext())
        
        val seenHourAgoPackages = listOf(
            HistoryPackage("Nallur Temple, Sri Lanka", "Historic Hindu temple", R.drawable.placeholder_nallur),
            HistoryPackage("Nuwara Eliya Ella, Sri Lanka", "Beautiful tea plantations", R.drawable.placeholder_sigiriya)
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
            
            fun bind(historyPackage: HistoryPackage) {
                destinationImage.setImageResource(historyPackage.imageResource)
                destinationName.text = historyPackage.packageName
                destinationDescription.text = historyPackage.description
                
                // Set up heart icon click
                heartIcon.setOnClickListener {
                    // Toggle favorite state
                    if (heartIcon.tag == "favorited") {
                        heartIcon.setImageResource(R.drawable.ic_heart)
                        heartIcon.tag = "not_favorited"
                        Toast.makeText(requireContext(), "Removed from favorites", Toast.LENGTH_SHORT).show()
                    } else {
                        heartIcon.setImageResource(R.drawable.ic_heart)
                        heartIcon.tag = "favorited"
                        Toast.makeText(requireContext(), "Added to favorites", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

