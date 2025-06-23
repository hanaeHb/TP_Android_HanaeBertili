package com.example.ecommerceapp.data.Entities

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imageResId")
    val imageResId: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("quantity")
    val quantity: String,
    val isFavorite: Boolean = false,
    @SerializedName("discountPercentage")
    val discountPercentage: Int?,
    @SerializedName("offerEnd")
    val offerEnd: Long?,
    @SerializedName("rating")
    val rating: Float? = 0f,
    @SerializedName("brand")
    val brand: String,
)
