package com.example.restaurant

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.restaurant.Networking.MenuServer
import com.example.restaurant.databinding.FragmentDetailsBinding
import com.example.restaurant.repositories.RestaurantRepository
import com.squareup.picasso.Picasso

class DetailsFragment: Fragment(R.layout.fragment_details) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(context)
        val binding = FragmentDetailsBinding.inflate(inflater)

        val testingRepoObject = RestaurantRepository.MenuObject

        setContent(binding,testingRepoObject!!)

        //Again had trouble with binding on this fragment
        val addToOrderButton = view.findViewById<Button>(R.id.frag_details_button)
        addToOrderButton.setOnClickListener{
            RestaurantRepository.orderList.add(testingRepoObject!!)
            //
            PrefConfing().writeListInPref(requireContext(), RestaurantRepository.orderList)

            var MainAct = (activity as MainActivity)
            if(MainAct is ListenersInterface){
                MainAct.liveBadgeUpdate(requireContext())
            }

            var mainActivityView =(activity as MainActivity)
            if(mainActivityView is ListenersInterface){
                mainActivityView.goToFragment(OrderFragment())
            }
        }
    }

fun setContent(binding: FragmentDetailsBinding,  testing: MenuServer.Item){

    view?.findViewById<TextView>(R.id.frag_details_name_text)?.text = testing.name
    view?.findViewById<TextView>(R.id.frag_details_text)?.text = testing.description
    var newImageUrl = testing.imageUrl
    newImageUrl = newImageUrl.replace("localhost","192.168.1.191")
    val detailsImage = view?.findViewById<ImageView>(R.id.frag_details_image)
    Picasso.get().load(newImageUrl).into(detailsImage)
    view?.findViewById<TextView>(R.id.frag_details_price_text)?.text = "Price: $${testing.price}"
    view?.findViewById<TextView>(R.id.details_prep_time)?.text = "Estimated Prep Time: " + testing.estimatedPrepTime.toString() + " Minutes"
    }
}