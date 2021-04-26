package com.example.restaurant

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.FragmentCategoriesBinding
import com.example.restaurant.recyclerview.CatAdapter
import com.example.restaurant.repositories.RestaurantRepository

class CategoriesFragment: Fragment(R.layout.fragment_categories) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(context)
        val binding = FragmentCategoriesBinding.inflate(inflater)

        val recycler = view.findViewById<RecyclerView>(R.id.categoriesRecycler)
        RestaurantRepository.callGetCategories(requireContext()){list ->
            binding.apply {
                recycler.apply {
                    adapter= CatAdapter(
                            list,
                            onClick = { CategoriesModel ->
                                val category = CategoriesModel
                                RestaurantRepository.menuList.clear()
                                RestaurantRepository.menuCategory = category

                                //val manager = parentFragmentManager
                                //manager.beginTransaction()
                                        //.replace(R.id.categories_container, MenuFragment())
                                       // .commit()
                               var mainActivityView = (activity as MainActivity)
                                if(mainActivityView is ListenersInterface){
                                    mainActivityView.goToFragment(MenuFragment())
                                }


                            })
                    layoutManager = LinearLayoutManager(context,
                            LinearLayoutManager.VERTICAL,false)
                    setHasFixedSize(true)
                }
            }

        }






    }
}