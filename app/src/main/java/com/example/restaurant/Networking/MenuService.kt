package com.example.restaurant.Networking

import android.view.Menu
import com.example.restaurant.CategoriesServer
import com.example.restaurant.MenuServer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MenuService {
    @GET("/menu")
    fun getMenu(@Query("category") category:String): Call<MenuServer>
}