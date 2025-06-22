package com.example.ecommerceapp.data.Repository

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.API.ProductApi
import com.example.ecommerceapp.data.Entities.Product
import kotlinx.coroutines.delay
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApi
) {
    suspend fun getProducts(): List<Product> {
        return try {
            val products = api.getProducts()
            Log.d("ProductRepository", "Successfully fetched ${products.size} products")
            products
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching products", e)
            emptyList()
        }
    }
}
