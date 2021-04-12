package com.example.restaurant

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.restaurant.databinding.DialogSubmitBinding
import com.example.restaurant.repositories.RestaurantRepository

class SubmitDialog(
): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        val binding = DialogSubmitBinding.inflate(inflater)
        RestaurantRepository.totalCheckAmount =0.0
        RestaurantRepository.getCheckTotal()
        binding.dialogText.text = "Are you sure you want to order this items?," +
        " your total $${RestaurantRepository.totalCheckAmount.toString()}"

        val builder = AlertDialog.Builder(requireContext())
                .setView(binding.root)
                .setPositiveButton(getString(R.string.submit)){ _, _ ->
                    //onPositiveSelected(binding)
                    RestaurantRepository.totalPrepTime = 0
                    RestaurantRepository.totalCheckAmount = 0.0
                    RestaurantRepository.getTotalPrepTime()
                    RestaurantRepository.getCheckTotal()
                    val intent = Intent(context,MainActivity::class.java)
                    intent.putExtra("confirmation","confirmation")
                    startActivity(intent)
                    RestaurantRepository.orderList.clear()
                    //PrefConfing().deletePref(requireContext())
                    //RestaurantRepository.totalCheckAmount=0.0
                }
                .setNegativeButton(getString(R.string.cancel)){ _, _ ->
                    //do nothing here
                    RestaurantRepository.totalCheckAmount = 0.0
                    RestaurantRepository.totalPrepTime = 0
                }

        return builder.create()
    }
}


