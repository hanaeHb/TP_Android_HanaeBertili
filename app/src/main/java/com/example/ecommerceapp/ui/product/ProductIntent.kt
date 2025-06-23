package com.example.ecommerceapp.ui.product

sealed class ProductIntent {
    object LoadProducts : ProductIntent()
    data class ToggleFavorite(val productId: String) : ProductIntent()
}