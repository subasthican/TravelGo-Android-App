package com.example.travelgo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment

class SendFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_send, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Set up send functionality
        setupSendFeatures(view)
        
        Toast.makeText(requireContext(), "Send page loaded - Share your travel experiences!", Toast.LENGTH_SHORT).show()
    }
    
    private fun setupSendFeatures(view: View) {
        // For now, we'll show general functionality message
        // In a real app, you would add proper IDs to send elements in the layout
        Toast.makeText(requireContext(), "Send features ready - add UI elements with IDs to layout for specific functionality", Toast.LENGTH_SHORT).show()
    }
}

