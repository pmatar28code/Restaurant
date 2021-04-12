package com.example.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.restaurant.databinding.FragmentErrorBinding

class ErrorFragment: Fragment(R.layout.fragment_error) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var inflater = LayoutInflater.from(context)
        val binding = FragmentErrorBinding.inflate(inflater)
        var errorText = view.findViewById<TextView>(R.id.error_text)
        errorText.text = "Error: Server not available"
    }
}