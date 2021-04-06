package com.example.restaurant

import android.content.Intent
import com.example.restaurant.databinding.DialogSubmitBinding
import com.example.restaurant.databinding.FragmentConfirmationBinding



import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class SubmitDialog(
        //val onAdd : (ToDo) -> Unit
): DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        val binding = DialogSubmitBinding.inflate(inflater)
        RestaurantRepository.totalCheckAmount =0.0
        RestaurantRepository.getCheckTotal()
        binding.dialogText.text = "Are you sure you want to order this items?, your total $${RestaurantRepository.totalCheckAmount.toString()}"

        val builder = AlertDialog.Builder(requireContext())
                .setView(binding.root)
                .setPositiveButton("Submit"){ _ , _ ->
                    //onPositiveSelected(binding)
                    RestaurantRepository.totalPrepTime = 0
                    RestaurantRepository.totalCheckAmount = 0.0
                    RestaurantRepository.getTotalPrepTime()
                    RestaurantRepository.getCheckTotal()
                    var intent = Intent(context,MainActivity::class.java)
                    intent.putExtra("confirmation","confirmation")
                    startActivity(intent)
                    RestaurantRepository.orderList.clear()
                    //RestaurantRepository.totalCheckAmount=0.0
                }
                .setNegativeButton("Cancel"){_ , _ ->
                    //do nothing here
                    RestaurantRepository.totalCheckAmount = 0.0
                    RestaurantRepository.totalPrepTime = 0
                }

        return builder.create()
    }

    private fun onPositiveSelected(binding:DialogSubmitBinding){
        //val name = binding.newTodoName.editText?.text?.toString()?:" "
        //val prioritySelected = binding.priorityOptions.checkedRadioButtonId
        when{
            //name.isBlank() -> {
            //    Toast.makeText(requireContext(),
             //           "Please enter a todo item",
             //           Toast.LENGTH_LONG
            //    ).show()
          //  }
           // prioritySelected == -1 -> {
              //  Toast.makeText(requireContext(),
              //          "Please select a priority",
              //          Toast.LENGTH_SHORT
              //  ).show()
            //}
           // else -> {
                //val priority = Priority.from(prioritySelected)
               // val toDo = ToDo(name,priority)
                //onAdd(toDo)
            }
        }

    }


