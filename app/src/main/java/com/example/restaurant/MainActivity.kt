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

        var getIntent = intent.getStringExtra("change")
        Toast.makeText(this,"this is the intent ${getIntent}",Toast.LENGTH_LONG).show()


        //RestaurantRepository.callGetCategories(context)
        // Toast.makeText(context,"calling getCategories()", Toast.LENGTH_LONG).show()


        Handler().postDelayed(
                {
                    if(getIntent!=null){
                        binding.menu.selectedItemId=R.id.order
                       // RestaurantRepository.categoriesList.clear()
                        RestaurantRepository.menuList.clear()
                    }else{
                        binding.menu.selectedItemId=R.id.categories
                        //RestaurantRepository.categoriesList.clear()
                        RestaurantRepository.menuList.clear()
                    }
                },
                2000 // value in milliseconds
        )

        binding.menu.setOnNavigationItemSelectedListener {
            handeBottonNavigation(it.itemId,binding)
        }
        //binding.menu.selectedItemId=R.id.categories
    }





    fun swapFragments(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.categories_container, fragment)
                .commit()
    }

    private fun handeBottonNavigation(menuItemId:Int, binding:ActivityMainBinding):Boolean {
        return when(menuItemId){
            R.id.categories -> {
                RestaurantRepository.categoriesList.clear()
                swapFragments(CategoriesFragment())
                true
            }
            R.id.order -> {
                swapFragments(OrderFragment())
                true
            }

            else -> false
        }
    }

}