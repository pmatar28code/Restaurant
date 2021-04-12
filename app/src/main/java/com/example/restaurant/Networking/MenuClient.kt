package com.example.restaurant.Networking

import com.example.restaurant.repositories.RestaurantRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MenuClient {
    val url = RestaurantRepository.urlForClients
    val okhttp = OkHttpClient()
    private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    val client = Retrofit.Builder()
            .baseUrl(url)
            .client(okhttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    val MenuService = client.create(MenuService::class.java)
    var ids = mutableListOf<String>()
    var orderList = RestaurantRepository.orderList
}