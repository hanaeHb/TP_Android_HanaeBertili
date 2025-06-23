package com.example.ecommerceapp.ui.product

import com.example.ecommerceapp.data.Entities.Product

fun Product.getEffectivePrice(): Double {
    val basePrice = this.price.toDoubleOrNull() ?: 0.0
    val discount = this.discountPercentage ?: 0
    return basePrice * (1 - discount / 100.0)
}