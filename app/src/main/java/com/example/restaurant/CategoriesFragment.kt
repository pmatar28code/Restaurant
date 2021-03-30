package com.example.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurant.databinding.FragmentCategoriesBinding
import com.example.restaurant.databinding.ItemCategoriesBinding

class CategoriesFragment: Fragment(R.layout.fragment_categories) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var inflater = LayoutInflater.from(context)
        val binding = FragmentCategoriesBinding.inflate(inflater)

        var categoriesAdapter = CatAdapter(RestaurantRepository.categoriesList)

        binding.apply {
               categoriesRecycler.apply {
               adapter= categoriesAdapter
               layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                   //(adapter as CatAdapter).notifyDataSetChanged()
           }
        }
    }
}