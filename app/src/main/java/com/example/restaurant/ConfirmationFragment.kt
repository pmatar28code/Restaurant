package com.example.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.restaurant.databinding.FragmentConfirmationBinding

class ConfirmationFragment: Fragment(R.layout.fragment_confirmation) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var inflater = LayoutInflater.from(context)

        //was having trouble with binding on some fragments, thats why i used findviewbyid
        //gonna work on this.
        val binding = FragmentConfirmationBinding.inflate(inflater)
        var thankYou = view.findViewById<TextView>(R.id.thanks_text)
        thankYou.text = "Thanks you for your purchase"
        var confirmationPreptimeText = view.findViewById<TextView>(R.id.confirmation_preptime_text)
        confirmationPreptimeText.text = "Estimated Prep time for your purchase: ${RestaurantRepository.totalPrepTime.toString()} Minutes"
        var confirmationAmountText = view.findViewById<TextView>(R.id.confirmation_purchase_total)
        confirmationAmountText.text = "Your purchase total: $${RestaurantRepository.totalCheckAmount}"
    }
}