package com.example.restaurant.Networking

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MenuClient {
    const val url = "http://192.168.1.191:8090"
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


}