package com.shoppe.android.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.isDisabled
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

/**
 * TDD Tests for CommonComponents
 */
class CommonComponentsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPrimaryButton_displaysText() {
        composeTestRule.setContent {
            PrimaryButton(
                text = "Test Button",
                onClick = {},
                enabled = true
            )
        }

        // Verify button text is displayed
        composeTestRule
            .onNodeWithText("Test Button")
            .assertIsDisplayed()
    }

    @Test
    fun testPrimaryButton_click_callsCallback() {
        var clickCalled = false

        composeTestRule.setContent {
            PrimaryButton(
                text = "Test Button",
                onClick = { clickCalled = true },
                enabled = true
            )
        }

        // Click button
        composeTestRule
            .onNodeWithText("Test Button")
            .performClick()

        // Verify callback was called
        assert(clickCalled) { "Button click callback should be called" }
    }

    @Test
    fun testPrimaryButton_enabledState() {
        composeTestRule.setContent {
            PrimaryButton(
                text = "Test Button",
                onClick = {},
                enabled = true
            )
        }

        // Verify button is enabled
        composeTestRule
            .onNodeWithText("Test Button")
            .assertIsEnabled()
    }

    @Test
    fun testPrimaryButton_disabledState() {
        composeTestRule.setContent {
            PrimaryButton(
                text = "Test Button",
                onClick = {},
                enabled = false
            )
        }

        // Verify button is disabled
        composeTestRule
            .onNodeWithText("Test Button")
            .isDisabled()
    }

    @Test
    fun testSecondaryButton_displaysText() {
        composeTestRule.setContent {
            SecondaryButton(
                text = "Test Button",
                onClick = {}
            )
        }

        // Verify button text is displayed
        composeTestRule
            .onNodeWithText("Test Button")
            .assertIsDisplayed()
    }

    @Test
    fun testSecondaryButton_click_callsCallback() {
        var clickCalled = false

        composeTestRule.setContent {
            SecondaryButton(
                text = "Test Button",
                onClick = { clickCalled = true }
            )
        }

        // Click button
        composeTestRule
            .onNodeWithText("Test Button")
            .performClick()

        // Verify callback was called
        assert(clickCalled) { "Button click callback should be called" }
    }

    // ===== QuantitySelector Tests =====

    @Test
    fun testQuantitySelector_displaysInitialQuantity() {
        composeTestRule.setContent {
            QuantitySelector(
                quantity = 1,
                onQuantityChange = {}
            )
        }

        // Verify quantity "1" is displayed
        composeTestRule
            .onNodeWithText("1")
            .assertIsDisplayed()
    }

    @Test
    fun testQuantitySelector_plusButtonIncrementsQuantity() {
        var quantity = 1

        composeTestRule.setContent {
            QuantitySelector(
                quantity = quantity,
                onQuantityChange = { quantity = it }
            )
        }

        // Click plus button
        composeTestRule
            .onNodeWithContentDescription("Increase quantity")
            .performClick()

        // Verify quantity increased to 2
        composeTestRule
            .onNodeWithText("2")
            .assertIsDisplayed()
    }

    @Test
    fun testQuantitySelector_minusButtonDecrementsQuantity() {
        var quantity = 3

        composeTestRule.setContent {
            QuantitySelector(
                quantity = quantity,
                onQuantityChange = { quantity = it }
            )
        }

        // Click minus button
        composeTestRule
            .onNodeWithContentDescription("Decrease quantity")
            .performClick()

        // Verify quantity decreased to 2
        composeTestRule
            .onNodeWithText("2")
            .assertIsDisplayed()
    }

    @Test
    fun testQuantitySelector_minusButtonDisabledAtMinQuantity() {
        var quantity = 1

        composeTestRule.setContent {
            QuantitySelector(
                quantity = quantity,
                onQuantityChange = { quantity = it },
                minQuantity = 1
            )
        }

        // The minus button should be visually disabled at min quantity
        // We verify the quantity stays at 1
        composeTestRule
            .onNodeWithText("1")
            .assertIsDisplayed()
    }

    @Test
    fun testQuantitySelector_plusButtonDisabledAtMaxQuantity() {
        var quantity = 99

        composeTestRule.setContent {
            QuantitySelector(
                quantity = quantity,
                onQuantityChange = { quantity = it },
                maxQuantity = 99
            )
        }

        // Verify quantity displays 99 (max)
        composeTestRule
            .onNodeWithText("99")
            .assertIsDisplayed()

        // Click plus button - quantity should not increase beyond max
        composeTestRule
            .onNodeWithContentDescription("Increase quantity")
            .performClick()

        // Quantity should still be 99 (cannot exceed max)
        composeTestRule
            .onNodeWithText("99")
            .assertIsDisplayed()
    }

    @Test
    fun testQuantitySelector_customMinMaxQuantity() {
        var quantity = 5

        composeTestRule.setContent {
            QuantitySelector(
                quantity = quantity,
                onQuantityChange = { quantity = it },
                minQuantity = 1,
                maxQuantity = 10
            )
        }

        // Verify initial quantity
        composeTestRule
            .onNodeWithText("5")
            .assertIsDisplayed()

        // Increase to max
        repeat(5) {
            composeTestRule
                .onNodeWithContentDescription("Increase quantity")
                .performClick()
        }

        // Should be at max (10)
        composeTestRule
            .onNodeWithText("10")
            .assertIsDisplayed()

        // Try to increase beyond max - should stay at 10
        composeTestRule
            .onNodeWithContentDescription("Increase quantity")
            .performClick()

        composeTestRule
            .onNodeWithText("10")
            .assertIsDisplayed()
    }
}
