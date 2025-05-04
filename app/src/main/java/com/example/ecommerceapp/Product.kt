package com.example.ecommerceapp

data class Product(
    val id: String,
    val title: String,
    val description: String,
    val imageResId: Int,
    val price: Double,
    val category: String,
    val quantity: Int
)
