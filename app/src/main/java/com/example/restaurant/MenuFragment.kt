package com.example.restaurant

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.FragmentCategoriesBinding
import com.example.restaurant.databinding.FragmentDetailsBinding
import com.example.restaurant.databinding.FragmentMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.concurrent.fixedRateTimer

class MenuFragment: Fragment(R.layout.fragment_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var inflater = LayoutInflater.from(context)
        val binding = FragmentMenuBinding.inflate(inflater)
        //val categoriesAdapter = CategoriesAdapter()

        var recycler = view.findViewById<RecyclerView>(R.id.menu_recycler)
       // RestaurantRepository.callGetMenu("entrees")
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



                                Toast.makeText(context,"this is the Menu that was clicked $test" , Toast.LENGTH_SHORT).show()

                            },addToOnClick = {MenuModel ->
                                RestaurantRepository.orderList.add(MenuModel)
                                var intent = Intent(context,MainActivity::class.java)
                                intent.putExtra("change","change")
                                startActivity(intent)


                            })
                            //CategoriesAdapter(onClick = { CategoriesModel -> }).submitList(RestaurantRepository.categoriesList)
                            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                            setHasFixedSize(true)
                            //layoutManager = LinearLayoutManager(context)
                            // CategoriesAdapter(){CategoriesModel ->
                            //   var categorieName = CategoriesModel.name
                            // Toast.makeText(context,"this is the category that was clicked" ,Toast.LENGTH_LONG).show()
                            // }
                            // CategoriesAdapter(onClick= {CategoriesModel ->  var categorieName = CategoriesModel.name
                            //   Toast.makeText(context,"this is the category that was clicked" ,Toast.LENGTH_LONG).show()}).submitList(RestaurantRepository.categoriesList)

                            //(adapter as CatAdapter).notifyDataSetChanged()
                            // CategoriesAdapter.submitList(RestaurantRepository.categoriesList)
                        }
                    }

                },
                500 // value in milliseconds
        )
    }
}