package com.example.restaurant.Networking

import com.example.restaurant.CategoriesServer
import retrofit2.Call
import retrofit2.http.GET

interface CategoriesService {
    @GET("/categories")
    fun getCategories():Call<CategoriesServer>
}