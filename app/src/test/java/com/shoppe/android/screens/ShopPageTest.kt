package com.shoppe.android.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

/**
 * TDD Tests for ShopPage
 */
class ShopPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testShopPage_displaysTitle() {
        composeTestRule.setContent {
            ShopPage()
        }

        // Verify title is displayed
        composeTestRule
            .onNodeWithText("Shop", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testShopPage_displaysSearchBar() {
        composeTestRule.setContent {
            ShopPage()
        }

        // Verify search bar is displayed
        composeTestRule
            .onNodeWithText("Search", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testShopPage_displaysCategories() {
        composeTestRule.setContent {
            ShopPage()
        }

        // Verify categories are displayed
        composeTestRule
            .onNodeWithText("Categories", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testShopPage_displaysProducts() {
        composeTestRule.setContent {
            ShopPage()
        }

        // Verify products grid is displayed
        composeTestRule
            .onNodeWithText("Featured Products", substring = true)
            .assertIsDisplayed()
    }
}
