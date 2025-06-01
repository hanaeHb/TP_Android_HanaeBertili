package com.example.ecommerceapp.data.API
import com.example.ecommerceapp.data.Entities.Product
import retrofit2.http.GET

interface ProductApi {
    @GET("products.json")
    suspend fun getProducts(): List<Product>
}