package com.example.ecommerceapp.ui.product

sealed class ProductIntent {
    object LoadProducts : ProductIntent()
    data class ToggleFavorite(val productId: String) : ProductIntent()
    data class RateProduct(val productId: String, val rating: Float) : ProductIntent()
}