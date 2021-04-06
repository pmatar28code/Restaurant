package com.example.restaurant

import android.content.Context
import android.util.Log
import com.example.restaurant.Networking.CategoriesClient
import com.example.restaurant.Networking.MenuClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                    Log.d("Error Get Categories","error$ $t")
                }
                override fun onResponse(call: Call<CategoriesServer>,
                response: Response<CategoriesServer>) {
                    if (response.isSuccessful) {
                       // Log.d("Error","${response.body()?.categories}")
                        if(response.body()?.categories?.isEmpty()!!){
                        }
                        var listServer = response.body()?.categories
                        if (listServer != null) {
                            for(item in listServer){
                                categoriesList.add(item)
                            }
                        }
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
                    Log.d("Error","${response.body()?.items}")
                    if(response.body()?.items?.isEmpty()!!){

                    }
                    var newMenuList = response.body()?.items

                    if (newMenuList != null) {
                        for(item in newMenuList){
                           menuList.add(item)
                        }
                    }
                }
            }
        })
    }
    //This was for Post, but still cant get it to work
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
        MenuClient.MenuService.sendItems(getOrderIdsList()).enqueue(object: Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                TODO("Not yet implemented")
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
