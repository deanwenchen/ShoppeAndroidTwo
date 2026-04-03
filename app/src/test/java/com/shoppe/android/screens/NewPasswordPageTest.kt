package com.shoppe.android.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

/**
 * TDD Tests for NewPasswordPage
 */
class NewPasswordPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testNewPasswordPage_displaysTitle() {
        composeTestRule.setContent {
            NewPasswordPage(
                onNavigateBack = {},
                onPasswordResetSuccess = {}
            )
        }

        // Verify title is displayed
        composeTestRule
            .onNodeWithText("New Password", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testNewPasswordPage_displaysInstruction() {
        composeTestRule.setContent {
            NewPasswordPage(
                onNavigateBack = {},
                onPasswordResetSuccess = {}
            )
        }

        // Verify instruction text is displayed
        composeTestRule
            .onNodeWithText("Create a new password", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testNewPasswordPage_hasPasswordInput() {
        composeTestRule.setContent {
            NewPasswordPage(
                onNavigateBack = {},
                onPasswordResetSuccess = {}
            )
        }

        // Verify password input is displayed
        composeTestRule
            .onNodeWithText("Password", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testNewPasswordPage_hasConfirmPasswordInput() {
        composeTestRule.setContent {
            NewPasswordPage(
                onNavigateBack = {},
                onPasswordResetSuccess = {}
            )
        }

        // Verify confirm password input is displayed
        composeTestRule
            .onNodeWithText("Confirm Password", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testNewPasswordPage_confirmClick_callsCallback() {
        var confirmClicked = false

        composeTestRule.setContent {
            NewPasswordPage(
                onNavigateBack = {},
                onPasswordResetSuccess = {
                    confirmClicked = true
                }
            )
        }

        // Click Confirm button
        composeTestRule
            .onNodeWithText("Confirm", substring = true)
            .performClick()

        // Verify callback was called
        assert(confirmClicked) { "Confirm callback should be called" }
    }

    @Test
    fun testNewPasswordPage_cancelClick_callsCallback() {
        var cancelClicked = false

        composeTestRule.setContent {
            NewPasswordPage(
                onNavigateBack = {},
                onPasswordResetSuccess = {}
            )
        }

        // Click Cancel
        composeTestRule
            .onNodeWithText("Cancel", substring = true)
            .performClick()

        // Verify callback was called
        assert(cancelClicked) { "Cancel callback should be called" }
    }
}
