package com.example.restaurant

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.restaurant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getIntent = intent.getStringExtra("change")
        var getIntentDetails = intent.getStringExtra("details")
        var getIntentConfirmation = intent.getStringExtra("confirmation")

        Handler().postDelayed(
                {
                    if(getIntent!=null){
                        binding.menu.selectedItemId=R.id.order
                       // RestaurantRepository.categoriesList.clear()
                        RestaurantRepository.menuList.clear()
                        getIntent = ""
                    }else if(getIntentDetails == "details"){
                        swapFragments(DetailsFragment())
                        getIntentDetails =""
                    }else if (getIntentConfirmation == "confirmation"){
                        swapFragments((ConfirmationFragment()))
                        getIntentConfirmation = ""
                    } else{
                        binding.menu.selectedItemId=R.id.categories
                        //RestaurantRepository.categoriesList.clear()
                        RestaurantRepository.menuList.clear()
                    }
                },
                300 // value in milliseconds
        )

        binding.menu.setOnNavigationItemSelectedListener {
            handeBottonNavigation(it.itemId,binding)
        }
        var orderBadgeNumber = RestaurantRepository.orderList.size
        binding.menu.refreshDrawableState()
        binding.menu.getOrCreateBadge(R.id.order).number=orderBadgeNumber
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
    //was using this method, but at the end it handled itself
    fun updateBadge(){
        var count = RestaurantRepository.orderList.size
        var context = this
        var inflater = LayoutInflater.from(context)
        var binding = ActivityMainBinding.inflate(inflater)
        binding.menu.getOrCreateBadge(R.id.order).number = count
    }
}