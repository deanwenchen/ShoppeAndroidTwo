package com.shoppe.android.models

/**
 * Data class representing an item in the shopping cart
 */
data class CartItem(
    val productId: String,
    val name: String,
    val price: Double,
    val image: String,
    val quantity: Int = 1
)
