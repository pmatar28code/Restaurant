package com.example.restaurant

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.FragmentMenuBinding
import com.example.restaurant.recyclerview.MenuAdapter
import com.example.restaurant.repositories.RestaurantRepository
import com.example.restaurant.viewmodels.MainViewModel

class MenuFragment: Fragment(R.layout.fragment_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(context)
        val binding = FragmentMenuBinding.inflate(inflater)

        val recycler = view.findViewById<RecyclerView>(R.id.menu_recycler)
        var test =""

        var category = RestaurantRepository.menuCategory

        RestaurantRepository.callGetMenu(category,requireContext()){list ->
            binding.apply {
                recycler.apply {
                    adapter= MenuAdapter(list, onClick = { MenuModel, position ->
                        RestaurantRepository.MenuObject = MenuModel

                        var mainActivityView =(activity as MainActivity)
                        if(mainActivityView is ListenersInterface){
                            mainActivityView.goToFragment(DetailsFragment())
                        }

                    }, addToOnClick = { MenuModel ->
                        RestaurantRepository.orderList.add(MenuModel)
                        ///
                        PrefConfing().writeListInPref(requireContext(), RestaurantRepository.orderList)

                        var MainAct = (activity as MainActivity)
                        if(MainAct is ListenersInterface){
                            MainAct.live(requireContext())
                        }
                        //val intent = Intent(context, MainActivity::class.java)
                        //intent.putExtra("change", "change")
                       // startActivity(intent)
                        var mainActivityView =(activity as MainActivity)
                        if(mainActivityView is ListenersInterface){
                            mainActivityView.goToFragment(OrderFragment())
                        }
                    })
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                    setHasFixedSize(true)
                }
            }

        }



    }
}