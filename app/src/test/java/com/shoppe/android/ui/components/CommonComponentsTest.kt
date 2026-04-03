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
}
