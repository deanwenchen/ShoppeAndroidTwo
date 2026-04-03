package com.shoppe.android.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

/**
 * TDD Tests for HelloCardPage
 */
class HelloCardPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHelloCardPage_displaysTitle() {
        composeTestRule.setContent {
            HelloCardPage(
                onNavigateToShop = {}
            )
        }

        // Verify title is displayed
        composeTestRule
            .onNodeWithText("Hello!", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testHelloCardPage_displaysCarouselContent() {
        composeTestRule.setContent {
            HelloCardPage(
                onNavigateToShop = {}
            )
        }

        // Verify carousel content is displayed (at least one slide)
        composeTestRule
            .onNodeWithText("Discover", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testHelloCardPage_displaysPaginationDots() {
        composeTestRule.setContent {
            HelloCardPage(
                onNavigateToShop = {}
            )
        }

        // Verify pagination dots are displayed
        composeTestRule
            .onNodeWithContentDescription("Page indicator")
            .assertIsDisplayed()
    }

    @Test
    fun testHelloCardPage_displaysNextButton() {
        composeTestRule.setContent {
            HelloCardPage(
                onNavigateToShop = {}
            )
        }

        // Verify Next button is displayed
        composeTestRule
            .onNodeWithText("Next", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testHelloCardPage_nextButtonClick_navigatesToShop() {
        var navigateToShopCalled = false

        composeTestRule.setContent {
            HelloCardPage(
                onNavigateToShop = {
                    navigateToShopCalled = true
                }
            )
        }

        // Click Next button (on last slide)
        composeTestRule
            .onNodeWithText("Next", substring = true)
            .performClick()

        // Verify navigation callback was called
        assert(navigateToShopCalled) { "Navigate to shop should be called on last slide" }
    }

    @Test
    fun testHelloCardPage_skipButtonClick_navigatesToShop() {
        var navigateToShopCalled = false

        composeTestRule.setContent {
            HelloCardPage(
                onNavigateToShop = {
                    navigateToShopCalled = true
                }
            )
        }

        // Click Skip button
        composeTestRule
            .onNodeWithText("Skip", substring = true)
            .performClick()

        // Verify navigation callback was called
        assert(navigateToShopCalled) { "Navigate to shop should be called on skip" }
    }
}
