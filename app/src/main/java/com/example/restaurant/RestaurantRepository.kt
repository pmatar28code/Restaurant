package com.example.restaurant

import android.content.Context
import android.media.Image
import android.service.voice.VoiceInteractionSession
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.ImageView
import android.widget.Toast
import com.example.restaurant.Networking.CategoriesClient
import com.example.restaurant.Networking.MenuClient
import com.example.restaurant.Networking.MenuClient.ids
import com.example.restaurant.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.ManufacturerUtils
import com.google.android.material.internal.NavigationMenu
import okhttp3.Cache.Companion.key
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController.getContext
import kotlin.coroutines.coroutineContext

object RestaurantRepository {
    var menuList = mutableListOf<MenuServer.Item>()
    var categoriesList = mutableListOf<String>()
    var string = ""
    var MenuObject:MenuServer.Item?=null
    var orderList = mutableListOf<MenuServer.Item>()
    var totalPrepTime: Int = 0
    var totalCheckAmount: Double = 0.0


    fun callGetCategories(context:Context){

            CategoriesClient.categoriesService.getCategories().
            enqueue(object : Callback<CategoriesServer> {
                override fun onFailure(call: Call<CategoriesServer>, t: Throwable) {
                    Log.d("Pedrrrooo no jalaa","error$ $t")
                }
                override fun onResponse(call: Call<CategoriesServer>, response: Response<CategoriesServer>) {
                    if (response.isSuccessful) {
                        Log.d("PEEDRROOOO","${response.body()?.categories}")
                        if(response.body()?.categories?.isEmpty()!!){
                            string = "No jala esta madre de la conexion"
                        }
                        var listServer = response.body()?.categories
                        //Toast.makeText(context,"${listServer?.get(0)}",Toast.LENGTH_LONG).show()
                        if (listServer != null) {
                            for(item in listServer){
                                categoriesList.add(item)
                            }
                        }
                        Toast.makeText(context,"${categoriesList?.get(2)}",Toast.LENGTH_LONG).show()

                    }
                }
            })
    }

    fun callGetMenu(menu :String,context: Context){
        MenuClient.MenuService.getMenu(menu).
        enqueue(object : Callback<MenuServer> {
            override fun onFailure(call: Call<MenuServer>, t: Throwable) {
                Log.d("Pedrrrooo no jalaa","error$ $t")
            }
            override fun onResponse(call: Call<MenuServer>, response: Response<MenuServer>) {
                if (response.isSuccessful) {
                    Log.d("PEEDRROOOO","${response.body()?.items}")
                    if(response.body()?.items?.isEmpty()!!){
                        string = "No jala esta madre de la conexion"
                    }
                    var newMenuList = response.body()?.items

                   // Toast.makeText(context,"${newMenuList?.get(0)}",Toast.LENGTH_SHORT).show()
                    if (newMenuList != null) {
                        for(item in newMenuList){
                           menuList.add(item)
                        }
                    }
                    // Toast.makeText(context,"this is the list after func called in repository${menuList?.get(0)}",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    fun getOrderIdsList():List<Keys>{
        var list = mutableListOf<Keys>()

        for(item in orderList){
            var keys = Keys(item.id.toString())
            list.add(keys)
        }
        return list.toList()
        //var newStringList = mutableListOf<String>()
        //for(item in mutableList){
          //  var id =  item.id.toString()
            //newStringList.add( id)
       // }
       // return    newStringList.toList()

    }
    var ids : List<String> = emptyList()
    fun post(context: Context){
        //getOrderIdsList(RestaurantRepository.orderList)
        MenuClient.MenuService.sendItems(getOrderIdsList()).enqueue(object: Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                TODO("Not yet implemented")
                Toast.makeText(context,"ERRORRR: $t",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                TODO("Not yet implemented")
                Toast.makeText(context,"this message: ${response.body()}",Toast.LENGTH_LONG).show()
            }

        })
    }

    fun getCheckTotal(){
        var oderList = orderList
        for(item in orderList){
            totalCheckAmount += item.price
        }
    }

    fun getTotalPrepTime(){
       var orderList =  RestaurantRepository.orderList
        for(item in orderList){
            totalPrepTime += item.estimatedPrepTime
       }
    }

     private fun MenuServer.Item.toToServer():MenuServer.Item{
        return MenuServer.Item(
                name = name,
                imageUrl = imageUrl,
                category = category,
                description = description,
                id = id,
                price = price,
                estimatedPrepTime = estimatedPrepTime
        )
    }
}
