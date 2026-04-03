package com.shoppe.android.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

/**
 * TDD Tests for LoginScreen
 */
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginScreen_displaysTitle() {
        composeTestRule.setContent {
            LoginScreen(
                onNavigateBack = {},
                onNavigateToPassword = {}
            )
        }

        // Verify title is displayed
        composeTestRule
            .onNodeWithText("Login", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testLoginScreen_displaysEmailInput() {
        composeTestRule.setContent {
            LoginScreen(
                onNavigateBack = {},
                onNavigateToPassword = {}
            )
        }

        // Verify email input is displayed
        composeTestRule
            .onNodeWithText("Email", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testLoginScreen_nextButton_callsCallback() {
        var navigateToPasswordCalled = false

        composeTestRule.setContent {
            LoginScreen(
                onNavigateBack = {},
                onNavigateToPassword = { navigateToPasswordCalled = true }
            )
        }

        // Enter valid email
        composeTestRule
            .onNodeWithText("Email", substring = true)
            .performTextInput("test@example.com")

        // Click Next button
        composeTestRule
            .onNodeWithText("Next", substring = true)
            .performClick()

        // Verify callback was called
        assert(navigateToPasswordCalled) { "Navigate to Password should be called" }
    }

    @Test
    fun testLoginScreen_cancelButton_callsCallback() {
        var cancelCalled = false

        composeTestRule.setContent {
            LoginScreen(
                onNavigateBack = { cancelCalled = true },
                onNavigateToPassword = {}
            )
        }

        // Click Cancel button
        composeTestRule
            .onNodeWithText("Cancel", substring = true)
            .performClick()

        // Verify callback was called
        assert(cancelCalled) { "Cancel callback should be called" }
    }

    @Test
    fun testLoginScreen_validationShowsEmailError() {
        composeTestRule.setContent {
            LoginScreen(
                onNavigateBack = {},
                onNavigateToPassword = {}
            )
        }

        // Click Next without entering email
        composeTestRule
            .onNodeWithText("Next", substring = true)
            .performClick()

        // Verify email error is displayed
        composeTestRule
            .onNodeWithText("Please enter your email", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testLoginScreen_validationShowsInvalidEmailError() {
        composeTestRule.setContent {
            LoginScreen(
                onNavigateBack = {},
                onNavigateToPassword = {}
            )
        }

        // Enter invalid email
        composeTestRule
            .onNodeWithText("Email", substring = true)
            .performTextInput("invalid-email")

        // Click Next button
        composeTestRule
            .onNodeWithText("Next", substring = true)
            .performClick()

        // Verify invalid email error is displayed
        composeTestRule
            .onNodeWithText("Please enter a valid email address", substring = true)
            .assertIsDisplayed()
    }
}
