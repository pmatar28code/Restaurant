package com.example.restaurant.Networking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MenuServer(
    @Json(name = "items")
    val items: List<Item>
) {
    @JsonClass(generateAdapter = true)
    data class Item(
        @Json(name = "category")
        val category: String,
        @Json(name = "description")
        val description: String,
        @Json(name = "id")
        val id: Int,
        @Json(name = "image_url")
        val imageUrl: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "price")
        val price: Double,
        //this i got it by modifying server to give estimated prep time
        @Json(name = "estimated_prep_time")
        var estimatedPrepTime :Int
    )
}