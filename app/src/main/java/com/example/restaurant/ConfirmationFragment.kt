package com.example.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.restaurant.databinding.FragmentConfirmationBinding
import com.example.restaurant.repositories.RestaurantRepository

class ConfirmationFragment: Fragment(R.layout.fragment_confirmation) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(context)

        //was having trouble with binding on some fragments, thats why i used findviewbyid
        //gonna work on this.
        val binding = FragmentConfirmationBinding.inflate(inflater)
        val thankYou = view.findViewById<TextView>(R.id.thanks_text)
        thankYou.text = getString(R.string.thank_you_purchase_text)
        val confirmationPreptimeText = view.findViewById<TextView>(R.id.confirmation_preptime_text)
        confirmationPreptimeText.text = "Estimated Prep time for your purchase: ${RestaurantRepository.totalPrepTime.toString()} Minutes"
        val confirmationAmountText = view.findViewById<TextView>(R.id.confirmation_purchase_total)
        confirmationAmountText.text = "Your purchase total: $${RestaurantRepository.totalCheckAmount}"
    }
}