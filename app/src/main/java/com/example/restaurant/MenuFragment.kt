package com.example.restaurant

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.FragmentMenuBinding

class MenuFragment: Fragment(R.layout.fragment_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var inflater = LayoutInflater.from(context)
        val binding = FragmentMenuBinding.inflate(inflater)

        var recycler = view.findViewById<RecyclerView>(R.id.menu_recycler)
        var test =""
        Handler().postDelayed(
                {
                    binding.apply {
                        recycler.apply {
                            adapter= MenuAdapter(RestaurantRepository.menuList,onClick = { MenuModel,position ->
                                RestaurantRepository.MenuObject = MenuModel
                                var test = MenuModel
                                val intent = Intent(context,MainActivity::class.java)
                                intent.putExtra("details","details")
                                intent.putExtra("position",position.toString())
                                intent.putExtra("name",test.name)
                                intent.putExtra("description",test.description)
                                intent.putExtra("image",test.imageUrl)
                                intent.putExtra("price",test.price.toString())
                                intent.putExtra("id",test.id)
                                startActivity(intent)

                            },addToOnClick = {MenuModel ->
                                RestaurantRepository.orderList.add(MenuModel)
                                var intent = Intent(context,MainActivity::class.java)
                                intent.putExtra("change","change")
                                startActivity(intent)
                            })
                            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                            setHasFixedSize(true)
                        }
                    }
                },
                500 // value in milliseconds
        )
    }
}