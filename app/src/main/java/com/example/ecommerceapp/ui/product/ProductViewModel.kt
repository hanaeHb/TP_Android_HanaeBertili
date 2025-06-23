package com.example.ecommerceapp.ui.product

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.Entities.CartItems
import com.example.ecommerceapp.data.Entities.Product
import com.example.ecommerceapp.data.Repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _favoriteProducts = mutableStateListOf<Product>()
    val favoriteProducts: List<Product> get() = _favoriteProducts

    private val _state = MutableStateFlow(ProductViewState())
    val state: StateFlow<ProductViewState> = _state
    fun getProductById(id: String): Product? {
        return _state.value.products.find { it.id == id }
    }

    fun handleIntent(intent: ProductIntent) {
        when (intent) {
            is ProductIntent.LoadProducts -> {
                viewModelScope.launch {
                    loadProducts()
                }
            }

            is ProductIntent.ToggleFavorite -> {
                _state.update { currentState ->
                    val updatedProducts = currentState.products.map { product ->
                        if (product.id == intent.productId) {
                            product.copy(isFavorite = !product.isFavorite)
                        } else product
                    }
                    currentState.copy(products = updatedProducts)
                }
            }
            is ProductIntent.RateProduct -> {
                _state.update { currentState ->
                    val updatedProducts = currentState.products.map { product ->
                        if (product.id == intent.productId) {
                            product.copy(rating = intent.rating)
                        } else product
                    }
                    currentState.copy(products = updatedProducts)
                }
            }
        }
    }

    private suspend fun loadProducts() {
        _state.value = _state.value.copy(isLoading = true, error = null)
        try {
            val products = repository.getProducts()
            _state.value = ProductViewState(isLoading = false, products = products)
        } catch (e: Exception) {
            _state.value =
                ProductViewState(isLoading = false, error = e.message ?: "Error fetching products")
        }
    }

    fun addToCart(product: Product) {
        _state.update { currentState ->
            val updatedCart = currentState.cartItems.toMutableList()
            val existingItem = updatedCart.find { it.product.id == product.id }
            if (existingItem != null) {
                existingItem.quantity += 1
            } else {
                updatedCart.add(CartItems(product, 1))
            }
            currentState.copy(cartItems = updatedCart)
        }
    }

    fun removeFromCart(productId: String) {
        _state.update { currentState ->
            val updatedCart = currentState.cartItems.filterNot { it.product.id == productId }
            currentState.copy(cartItems = updatedCart)
        }
    }

    fun increaseQuantity(productId: String) {
        val currentState = _state.value
        val updatedCart = currentState.cartItems.map { item ->
            val maxStock = item.product.quantity.toIntOrNull() ?: 0
            if (item.product.id == productId && item.quantity < maxStock) {
                item.copy(quantity = item.quantity + 1)
            } else item
        }
        _state.value = currentState.copy(cartItems = updatedCart)
    }

    fun decreaseQuantity(productId: String) {
        _state.update { currentState ->
            val updatedCart = currentState.cartItems.mapNotNull {
                if (it.product.id == productId) {
                    if (it.quantity > 1) it.copy(quantity = it.quantity - 1) else null
                } else it
            }
            currentState.copy(cartItems = updatedCart)
        }
    }

    fun toggleFavorite(productId: String) {
        _state.update { currentState ->
            val updatedProducts = currentState.products.map {
                if (it.id == productId) it.copy(isFavorite = !it.isFavorite)
                else it
            }
            currentState.copy(products = updatedProducts)
        }
    }
}