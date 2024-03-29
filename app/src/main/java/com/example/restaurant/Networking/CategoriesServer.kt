package com.example.restaurant.Networking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesServer(
    @Json(name = "categories")
    var categories: List<String>
)

