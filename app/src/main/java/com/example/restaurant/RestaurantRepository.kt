package com.example.restaurant

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.restaurant.Networking.CategoriesClient
import com.example.restaurant.Networking.MenuClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController.getContext

object RestaurantRepository {
    var menuList = mutableListOf<MenuServer.Item>()
    var categoriesList = mutableListOf<String>()
    var string = ""
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


    data class CategoriesModel(var name:String)


}