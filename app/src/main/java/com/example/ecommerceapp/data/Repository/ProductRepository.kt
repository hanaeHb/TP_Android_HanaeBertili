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
    private val _products = mutableListOf<Product>()

    suspend fun getProducts(): List<Product> {
        return try {
            if (_products.isEmpty()) {
                val productsFromApi = api.getProducts()
                _products.clear()
                _products.addAll(productsFromApi)
                Log.d("ProductRepository", "Fetched ${productsFromApi.size} products from API")
            }
            _products
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching products", e)
            emptyList()
        }
    }

    fun getLocalProducts(): List<Product> = _products

    fun updateProductStock(updatedProducts: List<Product>) {
        _products.clear()
        _products.addAll(updatedProducts)
    }
}

