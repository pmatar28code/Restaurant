package com.example.restaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurant.databinding.ActivityDetailsBinding
import com.example.restaurant.repositories.RestaurantRepository
import com.squareup.picasso.Picasso

//This activity is not being used, i changed my mind and used a fragment to continue
//using bottom nav menu

class DetailsActivity: AppCompatActivity() {
    var context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name").toString()
        val description = intent.getStringExtra("description").toString()
        val imageUrl = intent.getStringExtra("image").toString()
        val price = intent.getStringExtra("price").toString()
        val position = intent.getStringExtra("position").toString()
        Toast.makeText(this,"Details activity price value $price",Toast.LENGTH_LONG).show()
        //for testing
        val testingRepoObject = RestaurantRepository.MenuObject

        setContent(binding,name,description,imageUrl,price,position,testingRepoObject!!)

        binding.button.setOnClickListener {
            RestaurantRepository.orderList.add(testingRepoObject!!)
            Toast.makeText(this,"this is the item added to order list ${RestaurantRepository.orderList[0].name}",Toast.LENGTH_LONG).show()
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("change","change")
            startActivity(intent)
            Log.d("toooo FRAAAGGGG","what the hell")
        }

        binding.detailsButtonBackCategories.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.detailsBackButton.setOnClickListener{
            onBackPressed()
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