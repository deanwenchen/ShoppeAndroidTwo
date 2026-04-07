package com.shoppe.android.viewmodels

import androidx.lifecycle.ViewModel
import com.shoppe.android.models.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for managing shopping cart state
 * Uses StateFlow for reactive state management
 */
class CartViewModel : ViewModel() {

    /**
     * Cart state data class
     */
    data class CartState(
        val items: List<CartItem> = emptyList(),
        val totalItems: Int = 0,
        val totalPrice: Double = 0.0
    )

    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> = _state.asStateFlow()

    /**
     * Add an item to the cart
     * If item already exists, increment its quantity
     */
    fun addItem(item: CartItem) {
        _state.update { currentState ->
            val existingItemIndex = currentState.items.indexOfFirst { it.productId == item.productId }

            if (existingItemIndex >= 0) {
                // Item exists, increment quantity
                val existingItem = currentState.items[existingItemIndex]
                val updatedItems = currentState.items.toMutableList().apply {
                    set(existingItemIndex, existingItem.copy(quantity = existingItem.quantity + item.quantity))
                }
                calculateCartState(updatedItems)
            } else {
                // New item, add to cart
                val updatedItems = currentState.items + item
                calculateCartState(updatedItems)
            }
        }
    }

    /**
     * Update quantity of an item
     * If quantity is 0 or less, remove the item
     */
    fun updateQuantity(productId: String, quantity: Int) {
        _state.update { currentState ->
            val existingItemIndex = currentState.items.indexOfFirst { it.productId == productId }

            if (existingItemIndex >= 0) {
                val updatedItems = currentState.items.toMutableList().apply {
                    if (quantity <= 0) {
                        // Remove item
                        removeAt(existingItemIndex)
                    } else {
                        // Update quantity
                        set(existingItemIndex, get(existingItemIndex).copy(quantity = quantity))
                    }
                }
                calculateCartState(updatedItems)
            } else {
                // Item not found, no change
                currentState
            }
        }
    }

    /**
     * Remove an item from the cart
     */
    fun removeItem(productId: String) {
        _state.update { currentState ->
            val updatedItems = currentState.items.filter { it.productId != productId }
            calculateCartState(updatedItems)
        }
    }

    /**
     * Clear all items from the cart
     */
    fun clearCart() {
        _state.update {
            CartState()
        }
    }

    /**
     * Helper function to calculate cart totals
     */
    private fun calculateCartState(items: List<CartItem>): CartState {
        val totalItems = items.sumOf { it.quantity }
        val totalPrice = items.sumOf { it.price * it.quantity }
        return CartState(
            items = items,
            totalItems = totalItems,
            totalPrice = totalPrice
        )
    }
}
