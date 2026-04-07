package com.shoppe.android.viewmodels

import com.shoppe.android.models.CartItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * TDD Tests for CartViewModel
 * Tests written FIRST - these should fail initially (RED phase)
 */
class CartViewModelTest {

    private lateinit var viewModel: CartViewModel

    @Before
    fun setup() {
        viewModel = CartViewModel()
    }

    // ===== Initial State Tests =====

    @Test
    fun testCartViewModel_initialState_hasEmptyCart() = runTest {
        val state = viewModel.state.first()
        assertTrue("Cart should be empty initially", state.items.isEmpty())
        assertEquals(0, state.totalItems)
        assertEquals(0.0, state.totalPrice, 0.01)
    }

    // ===== Add Item Tests =====

    @Test
    fun testAddItem_addsProductToCart() = runTest {
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 1
        )

        viewModel.addItem(item)
        val state = viewModel.state.first()

        assertEquals(1, state.items.size)
        assertEquals("1", state.items[0].productId)
        assertEquals("Classic White Sneakers", state.items[0].name)
        assertEquals(89.99, state.items[0].price, 0.01)
        assertEquals(1, state.items[0].quantity)
    }

    @Test
    fun testAddItem_sameProductIncrementsQuantity() = runTest {
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 1
        )

        viewModel.addItem(item)
        viewModel.addItem(item)
        val state = viewModel.state.first()

        assertEquals(1, state.items.size)
        assertEquals(2, state.items[0].quantity)
    }

    @Test
    fun testAddItem_multipleProductsAddsSeparately() = runTest {
        val item1 = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 1
        )
        val item2 = CartItem(
            productId = "2",
            name = "Leather Crossbody Bag",
            price = 129.99,
            image = "https://images.unsplash.com/photo-1548036328-c9fa89d128fa",
            quantity = 1
        )

        viewModel.addItem(item1)
        viewModel.addItem(item2)
        val state = viewModel.state.first()

        assertEquals(2, state.items.size)
        assertEquals(2, state.totalItems)
    }

    // ===== Update Quantity Tests =====

    @Test
    fun testUpdateQuantity_increasesQuantity() = runTest {
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 1
        )

        viewModel.addItem(item)
        viewModel.updateQuantity("1", 3)
        val state = viewModel.state.first()

        assertEquals(1, state.items.size)
        assertEquals(3, state.items[0].quantity)
    }

    @Test
    fun testUpdateQuantity_decreasesQuantity() = runTest {
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 5
        )

        viewModel.addItem(item)
        viewModel.updateQuantity("1", 2)
        val state = viewModel.state.first()

        assertEquals(1, state.items.size)
        assertEquals(2, state.items[0].quantity)
    }

    @Test
    fun testUpdateQuantity_zeroRemovesItem() = runTest {
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 1
        )

        viewModel.addItem(item)
        viewModel.updateQuantity("1", 0)
        val state = viewModel.state.first()

        assertTrue("Cart should be empty after setting quantity to 0", state.items.isEmpty())
    }

    @Test
    fun testUpdateQuantity_nonExistentProduct_doesNothing() = runTest {
        val state = viewModel.state.first()
        val initialSize = state.items.size

        viewModel.updateQuantity("non-existent", 5)
        val newState = viewModel.state.first()

        assertEquals(initialSize, newState.items.size)
    }

    // ===== Remove Item Tests =====

    @Test
    fun testRemoveItem_removesFromCart() = runTest {
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 1
        )

        viewModel.addItem(item)
        viewModel.removeItem("1")
        val state = viewModel.state.first()

        assertTrue("Cart should be empty after removing item", state.items.isEmpty())
    }

    @Test
    fun testRemoveItem_nonExistentProduct_doesNothing() = runTest {
        val state = viewModel.state.first()
        val initialSize = state.items.size

        viewModel.removeItem("non-existent")
        val newState = viewModel.state.first()

        assertEquals(initialSize, newState.items.size)
    }

    // ===== Clear Cart Tests =====

    @Test
    fun testClearCart_removesAllItems() = runTest {
        val item1 = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 2
        )
        val item2 = CartItem(
            productId = "2",
            name = "Leather Crossbody Bag",
            price = 129.99,
            image = "https://images.unsplash.com/photo-1548036328-c9fa89d128fa",
            quantity = 1
        )

        viewModel.addItem(item1)
        viewModel.addItem(item2)
        viewModel.clearCart()
        val state = viewModel.state.first()

        assertTrue("Cart should be empty after clearCart", state.items.isEmpty())
        assertEquals(0, state.totalItems)
        assertEquals(0.0, state.totalPrice, 0.01)
    }

    // ===== Total Price Calculation Tests =====

    @Test
    fun testTotalPrice_singleItem() = runTest {
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 1
        )

        viewModel.addItem(item)
        val state = viewModel.state.first()

        assertEquals(89.99, state.totalPrice, 0.01)
    }

    @Test
    fun testTotalPrice_multipleItemsWithQuantities() = runTest {
        val item1 = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 2
        )
        val item2 = CartItem(
            productId = "2",
            name = "Leather Crossbody Bag",
            price = 129.99,
            image = "https://images.unsplash.com/photo-1548036328-c9fa89d128fa",
            quantity = 1
        )

        viewModel.addItem(item1)
        viewModel.addItem(item2)
        val state = viewModel.state.first()

        // (89.99 * 2) + (129.99 * 1) = 179.98 + 129.99 = 309.97
        assertEquals(309.97, state.totalPrice, 0.01)
    }

    // ===== Edge Cases =====

    @Test
    fun testAddItem_nullProductName_handlesGracefully() = runTest {
        val item = CartItem(
            productId = "1",
            name = "",
            price = 89.99,
            image = "",
            quantity = 1
        )

        viewModel.addItem(item)
        val state = viewModel.state.first()

        assertEquals(1, state.items.size)
    }

    @Test
    fun testAddItem_zeroPrice_handlesGracefully() = runTest {
        val item = CartItem(
            productId = "1",
            name = "Free Item",
            price = 0.0,
            image = "",
            quantity = 1
        )

        viewModel.addItem(item)
        val state = viewModel.state.first()

        assertEquals(1, state.items.size)
        assertEquals(0.0, state.totalPrice, 0.01)
    }

    @Test
    fun testCartState_totalItemsMatchesSumOfQuantities() = runTest {
        val item1 = CartItem(
            productId = "1",
            name = "Item 1",
            price = 10.0,
            image = "",
            quantity = 3
        )
        val item2 = CartItem(
            productId = "2",
            name = "Item 2",
            price = 20.0,
            image = "",
            quantity = 2
        )

        viewModel.addItem(item1)
        viewModel.addItem(item2)
        val state = viewModel.state.first()

        assertEquals(5, state.totalItems)
    }
}
