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
import com.example.restaurant.databinding.ActivityMainBinding
import com.example.restaurant.databinding.FragmentCategoriesBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenu

class CategoriesFragment: Fragment(R.layout.fragment_categories) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var inflater = LayoutInflater.from(context)
        val binding = FragmentCategoriesBinding.inflate(inflater)
        //val categoriesAdapter = CategoriesAdapter()

        var recycler = view.findViewById<RecyclerView>(R.id.categoriesRecycler)
        RestaurantRepository.callGetCategories(requireContext())
        var test =""
        Handler().postDelayed(
          {
              binding.apply {
                  recycler.apply {
                      adapter= CatAdapter(RestaurantRepository.categoriesList,onClick = { CategoriesModel ->
                          var category = CategoriesModel
                          Toast.makeText(context,"this is test value $category:",Toast.LENGTH_SHORT).show()
                          RestaurantRepository.menuList.clear()
                          RestaurantRepository.callGetMenu(category,context)
                          Handler().postDelayed({
                              Toast.makeText(context,"we just requested callget menu: ${RestaurantRepository.menuList}",Toast.LENGTH_LONG).show()
                                    var manager =  parentFragmentManager
                              manager.beginTransaction()
                                      .replace(R.id.categories_container, MenuFragment())
                                      .commit()
                                      //MainActivity().swapFragmentsForecast(MenuFragment())
                          },500)

                          Toast.makeText(context,"this is the category that was clicked $category" ,Toast.LENGTH_SHORT).show()

                  })
                      //CategoriesAdapter(onClick = { CategoriesModel -> }).submitList(RestaurantRepository.categoriesList)
                      layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
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