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
){
    suspend fun getProducts(): List<Product> {
        return api.getProducts()
        val products = api.getProducts()
        Log.d("products repo", "size :"+ products.size)
        return products
    }
}
