package com.example.restaurant

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.restaurant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //RestaurantRepository.callGetCategories(context)
        // Toast.makeText(context,"calling getCategories()", Toast.LENGTH_LONG).show()


        Handler().postDelayed(
                {
                    //  Toast.makeText(context,"entering post delayed",Toast.LENGTH_LONG).show()

//                var test = RestaurantRepository.menuList[0].name
                    //    Toast.makeText(context,"test var in main: ${RestaurantRepository.menuList[0].name}",Toast.LENGTH_LONG).show()

                    // binding.testText.text = test
                    swapFragments(CategoriesFragment())
                },
                1000 // value in milliseconds
        )

    }

    fun swapFragments(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.categories_container, fragment)
                .commit()
    }


}