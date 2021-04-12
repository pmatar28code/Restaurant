package com.example.restaurant.Networking

import com.example.restaurant.RestaurantRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CategoriesClient {
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
    val categoriesService = client.create(CategoriesService::class.java)
}