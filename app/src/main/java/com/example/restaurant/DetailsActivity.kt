package com.example.restaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurant.Networking.MenuClient
import com.example.restaurant.Networking.MenuService
import com.example.restaurant.databinding.ActivityDetailsBinding
import com.example.restaurant.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class DetailsActivity: AppCompatActivity() {
    var context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name = intent.getStringExtra("name").toString()
        var description = intent.getStringExtra("description").toString()
        var imageUrl = intent.getStringExtra("image").toString()
        var price = intent.getStringExtra("price").toString()
        var position = intent.getStringExtra("position").toString()
        Toast.makeText(this,"Details activity price value $price",Toast.LENGTH_LONG).show()
        //for testing
        var testingRepoObject = RestaurantRepository.MenuObject


        setContent(binding,name,description,imageUrl,price,position,testingRepoObject!!)

        binding.button.setOnClickListener {
           RestaurantRepository.orderList.add(testingRepoObject!!)
            Toast.makeText(this,"this is the item added to order list ${RestaurantRepository.orderList[0].name}",Toast.LENGTH_LONG).show()
            var intent = Intent(this,MainActivity::class.java)
            intent.putExtra("change","change")
            startActivity(intent)
            Log.d("toooo FRAAAGGGG","what the hell")
        }
        }
    }
    fun setContent(binding: ActivityDetailsBinding,name:String,description:String,imageUrl:String,price:String,position:String,testing:MenuServer.Item){


        binding.apply {
            detailsNameText.text = name
            detailsDescriptionText.text = description
            var newImageUrl = imageUrl
            newImageUrl = newImageUrl.replace("localhost","192.168.1.191")
            Picasso.get().load(newImageUrl).into(detailsImage)
            detailsPriceText.text = "Price: $${price}"
            //for testing
            detailsTimeText.text = "position: ${testing.name}"
    }
}