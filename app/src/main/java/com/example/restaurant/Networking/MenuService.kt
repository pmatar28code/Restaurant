package com.example.restaurant.Networking

import com.example.restaurant.Keys
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MenuService {
    @GET("/menu")
    fun getMenu(@Query("category") category:String): Call<MenuServer>
    //couldnt get Post to work, i got the preptime by modifying server to return items with preptime
    // thats why i asked to please use my server attached
    @POST("/")
    fun sendItems(@Body menuIds: List<Keys>): Call<String>
}