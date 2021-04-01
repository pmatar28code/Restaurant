package com.example.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.FragmentCategoriesBinding
import com.example.restaurant.databinding.FragmentMenuBinding
import com.example.restaurant.databinding.FragmentOrderBinding

class OrderFragment:Fragment(R.layout.fragment_order) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var inflater = LayoutInflater.from(context)
        val binding = FragmentMenuBinding.inflate(inflater)
        val recycler = view.findViewById<RecyclerView>(R.id.order_recycler)

        binding.apply {
            recycler.apply {
                adapter =  OrderAdapter(RestaurantRepository.orderList)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)


            }
        }
    }
}