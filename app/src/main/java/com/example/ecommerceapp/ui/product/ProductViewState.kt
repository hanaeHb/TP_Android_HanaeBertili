package com.example.ecommerceapp.ui.product

import com.example.ecommerceapp.data.Entities.CartItems
import com.example.ecommerceapp.data.Entities.Product

data class ProductViewState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null,
    val cartItems: List<CartItems> = emptyList()
)
