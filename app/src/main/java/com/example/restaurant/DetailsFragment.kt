package com.example.restaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.restaurant.databinding.ActivityDetailsBinding
import com.example.restaurant.databinding.FragmentDetailsBinding
import com.example.restaurant.databinding.FragmentMenuBinding
import com.squareup.picasso.Picasso

class DetailsFragment: Fragment(R.layout.fragment_details) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var inflater = LayoutInflater.from(context)
        val binding = FragmentDetailsBinding.inflate(inflater)


        var intent = Intent()
        var name = intent.getStringExtra("name").toString()
        var description = intent.getStringExtra("description").toString()
        var imageUrl = intent.getStringExtra("image").toString()
        var price = intent.getStringExtra("price").toString()
        var position = intent.getStringExtra("position").toString()
        Toast.makeText(context,"Details activity price value $price", Toast.LENGTH_LONG).show()
        //for testing
        var testingRepoObject = RestaurantRepository.MenuObject


        setContent(binding,name,description,imageUrl,price,position,testingRepoObject!!)

        //binding.fragDetailsButtonOrder.setOnClickListener {
        var addToOrderButton = view.findViewById<Button>(R.id.frag_details_button)
        addToOrderButton.setOnClickListener{
            RestaurantRepository.orderList.add(testingRepoObject!!)

            //MainActivity().updateBadge()
            //Toast.makeText(this,"this is the item added to order list ${RestaurantRepository.orderList[0].name}", Toast.LENGTH_LONG).show()
            var intent = Intent(context,MainActivity::class.java)
            intent.putExtra("change","change")
            startActivity(intent)
            //Log.d("toooo FRAAAGGGG","what the hell")
        }


    }

fun setContent(binding: FragmentDetailsBinding, name:String, description:String, imageUrl:String, price:String, position:String, testing:MenuServer.Item){

    view?.findViewById<TextView>(R.id.frag_details_name_text)?.text = testing.name
    view?.findViewById<TextView>(R.id.frag_details_text)?.text = testing.description
    var newImageUrl = testing.imageUrl
    newImageUrl = newImageUrl.replace("localhost","192.168.1.191")
    var detailsImage = view?.findViewById<ImageView>(R.id.frag_details_image)
    Picasso.get().load(newImageUrl).into(detailsImage)
    view?.findViewById<TextView>(R.id.frag_details_price_text)?.text = "Price: $${testing.price}"
        //for testing
        //detailsTimeText.text = "position: ${testing.name}"


    }
}