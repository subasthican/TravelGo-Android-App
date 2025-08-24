package com.example.travelgo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class OnboardingFragment : Fragment() {
    
    companion object {
        private const val ARG_PAGE = "page"
        
        fun newInstance(page: OnboardingActivity.OnboardingPage): OnboardingFragment {
            val fragment = OnboardingFragment()
            val args = Bundle()
            args.putString("title", page.title)
            args.putString("description", page.description)
            args.putInt("image", page.imageResource)
            fragment.arguments = args
            return fragment
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val imageView = view.findViewById<ImageView>(R.id.onboarding_image)
        val titleText = view.findViewById<TextView>(R.id.onboarding_title)
        val descriptionText = view.findViewById<TextView>(R.id.onboarding_description)
        
        arguments?.let { args ->
            titleText.text = args.getString("title")
            descriptionText.text = args.getString("description")
            imageView.setImageResource(args.getInt("image"))
        }
    }
}
