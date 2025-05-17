package com.example.ecommerceapp.ui.product

sealed class ProductIntent {
    object LoadProducts : ProductIntent()
}