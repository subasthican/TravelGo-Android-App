package com.example.travelgo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment

class ExploreFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Set up search functionality
        val searchEditText = view.findViewById<EditText>(android.R.id.edit)
        searchEditText?.setOnClickListener {
            Toast.makeText(requireContext(), "Search functionality coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        // Set up destination card clicks - these would navigate to details
        setupDestinationClicks(view)
        
        // Set up category clicks
        setupCategoryClicks(view)
    }
    
    private fun setupDestinationClicks(view: View) {
        // For now, we'll show general toast messages since the layouts don't have specific IDs
        // In a real app, you would add proper IDs to the destination cards in the layout
        Toast.makeText(requireContext(), "Destination cards clickable - feature implementation ready", Toast.LENGTH_SHORT).show()
    }
    
    private fun setupCategoryClicks(view: View) {
        // For now, we'll show general toast messages since the layouts don't have specific IDs
        // In a real app, you would add proper IDs to the category cards in the layout
        Toast.makeText(requireContext(), "Category cards clickable - feature implementation ready", Toast.LENGTH_SHORT).show()
    }
}
