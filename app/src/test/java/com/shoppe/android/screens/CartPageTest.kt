package com.shoppe.android.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.shoppe.android.models.CartItem
import com.shoppe.android.viewmodels.CartViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * TDD Tests for CartPage
 * Tests written FIRST - these should fail initially (RED phase)
 */
class CartPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: CartViewModel

    // ===== Empty Cart State Tests =====

    @Test
    fun testCartPage_emptyCart_displaysEmptyState() {
        viewModel = CartViewModel()

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = {},
                onContinueShopping = {}
            )
        }

        // Verify empty cart message is displayed
        composeTestRule
            .onNodeWithText("Your cart is empty", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testCartPage_emptyCart_displaysContinueShoppingButton() {
        viewModel = CartViewModel()

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = {},
                onContinueShopping = {}
            )
        }

        // Verify continue shopping button is displayed
        composeTestRule
            .onNodeWithText("Continue Shopping", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testCartPage_emptyCart_continueShopping_callsCallback() {
        var continueShoppingCalled = false
        viewModel = CartViewModel()

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = {},
                onContinueShopping = { continueShoppingCalled = true }
            )
        }

        // Click continue shopping button
        composeTestRule
            .onNodeWithText("Continue Shopping", substring = true)
            .performClick()

        // Verify callback was called
        assert(continueShoppingCalled) { "Continue shopping callback should be called" }
    }

    // ===== Cart With Items Tests =====

    @Test
    fun testCartPage_withItems_displaysItems() {
        viewModel = CartViewModel()
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 1
        )
        viewModel.addItem(item)

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = {},
                onContinueShopping = {}
            )
        }

        // Verify product name is displayed
        composeTestRule
            .onNodeWithText("Classic White Sneakers")
            .assertIsDisplayed()
    }

    @Test
    fun testCartPage_withItems_displaysPrice() {
        viewModel = CartViewModel()
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 1
        )
        viewModel.addItem(item)

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = {},
                onContinueShopping = {}
            )
        }

        // Verify price is displayed
        composeTestRule
            .onNodeWithText("$89.99")
            .assertIsDisplayed()
    }

    @Test
    fun testCartPage_withItems_displaysQuantitySelector() {
        viewModel = CartViewModel()
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 2
        )
        viewModel.addItem(item)

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = {},
                onContinueShopping = {}
            )
        }

        // Verify quantity "2" is displayed
        composeTestRule
            .onNodeWithText("2")
            .assertIsDisplayed()
    }

    @Test
    fun testCartPage_withItems_displaysOrderSummary() {
        viewModel = CartViewModel()
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 1
        )
        viewModel.addItem(item)

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = {},
                onContinueShopping = {}
            )
        }

        // Verify order summary section is displayed
        composeTestRule
            .onNodeWithText("Order Summary", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testCartPage_withItems_displaysTotalPrice() {
        viewModel = CartViewModel()
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 1
        )
        viewModel.addItem(item)

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = {},
                onContinueShopping = {}
            )
        }

        // Verify total price is displayed
        composeTestRule
            .onNodeWithText("Total", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testCartPage_withItems_displaysRemoveButton() {
        viewModel = CartViewModel()
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 1
        )
        viewModel.addItem(item)

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = {},
                onContinueShopping = {}
            )
        }

        // Verify remove button/icon is displayed (using content description)
        composeTestRule
            .onNodeWithContentDescription("Remove item")
            .assertIsDisplayed()
    }

    // ===== Quantity Control Tests =====

    @Test
    fun testCartPage_quantityPlusButton_incrementsQuantity() {
        viewModel = CartViewModel()
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 1
        )
        viewModel.addItem(item)

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = {},
                onContinueShopping = {}
            )
        }

        // Click plus button
        composeTestRule
            .onNodeWithContentDescription("Increase quantity")
            .performClick()

        // Verify quantity increased (check that "2" is displayed)
        composeTestRule
            .onNodeWithText("2")
            .assertIsDisplayed()
    }

    @Test
    fun testCartPage_quantityMinusButton_decrementsQuantity() {
        viewModel = CartViewModel()
        val item = CartItem(
            productId = "1",
            name = "Classic White Sneakers",
            price = 89.99,
            image = "https://images.unsplash.com/photo-1549298916-b41d501d3772",
            quantity = 3
        )
        viewModel.addItem(item)

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = {},
                onContinueShopping = {}
            )
        }

        // Click minus button
        composeTestRule
            .onNodeWithContentDescription("Decrease quantity")
            .performClick()

        // Verify quantity decreased (check that "2" is displayed)
        composeTestRule
            .onNodeWithText("2")
            .assertIsDisplayed()
    }

    // ===== Multiple Items Tests =====

    @Test
    fun testCartPage_multipleItems_displaysAllItems() {
        viewModel = CartViewModel()
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

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = {},
                onContinueShopping = {}
            )
        }

        // Verify both products are displayed
        composeTestRule
            .onNodeWithText("Classic White Sneakers")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Leather Crossbody Bag")
            .assertIsDisplayed()
    }

    @Test
    fun testCartPage_multipleItems_calculatesCorrectTotal() {
        viewModel = CartViewModel()
        val item1 = CartItem(
            productId = "1",
            name = "Item 1",
            price = 50.0,
            image = "",
            quantity = 2
        )
        val item2 = CartItem(
            productId = "2",
            name = "Item 2",
            price = 30.0,
            image = "",
            quantity = 1
        )
        viewModel.addItem(item1)
        viewModel.addItem(item2)

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = {},
                onContinueShopping = {}
            )
        }

        // Total should be (50.0 * 2) + (30.0 * 1) = 130.0
        composeTestRule
            .onNodeWithText("$130.00")
            .assertIsDisplayed()
    }

    // ===== Navigation Tests =====

    @Test
    fun testCartPage_backButton_callsCallback() {
        var navigateBackCalled = false
        viewModel = CartViewModel()

        composeTestRule.setContent {
            CartPage(
                viewModel = viewModel,
                onNavigateBack = { navigateBackCalled = true },
                onContinueShopping = {}
            )
        }

        // Click back button
        composeTestRule
            .onNodeWithContentDescription("Back")
            .performClick()

        // Verify callback was called
        assert(navigateBackCalled) { "Navigate back callback should be called" }
    }
}
