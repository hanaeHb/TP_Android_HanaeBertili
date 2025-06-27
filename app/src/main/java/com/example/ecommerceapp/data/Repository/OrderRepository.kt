package com.example.ecommerceapp.data.Repository

import com.example.ecommerceapp.data.Entities.CartItems

object OrderRepository {
    private val allOrders = mutableListOf<CartItems>()

    fun addOrUpdateOrders(items: List<CartItems>) {
        items.forEach { newItem ->
            val index = allOrders.indexOfFirst { it.product.id == newItem.product.id && it.clientEmail == newItem.clientEmail }
            if (index != -1) {
                val existingItem = allOrders[index]
                val updatedQuantity = existingItem.quantity + newItem.quantity
                allOrders[index] = existingItem.copy(quantity = updatedQuantity)
            } else {
                allOrders.add(newItem)
            }
        }
    }

    fun getOrdersByEmail(email: String): List<CartItems> {
        return allOrders.filter { it.clientEmail == email }
    }

    fun clear() {
        allOrders.clear()
    }

    fun getOrdersByEmailForProduct(productId: String): List<CartItems> {
        return allOrders.filter { it.product.id == productId }
    }

}

