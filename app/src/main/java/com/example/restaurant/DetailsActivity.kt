package com.example.restaurant

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurant.databinding.ActivityDetailsBinding
import com.example.restaurant.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class DetailsActivity: AppCompatActivity() {
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

       setContent(binding,name,description,imageUrl,price,position)

        }
    }
    fun setContent(binding: ActivityDetailsBinding,name:String,description:String,imageUrl:String,price:String,position:String){


        binding.apply {
            detailsNameText.text = name
            detailsDescriptionText.text = description
            var newImageUrl = imageUrl
            newImageUrl = newImageUrl.replace("localhost","192.168.1.191")
            Picasso.get().load(newImageUrl).into(detailsImage)
            detailsPriceText.text = "Price: $${price}"
            detailsTimeText.text = "position: $position"
    }
}