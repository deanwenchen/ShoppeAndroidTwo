package com.shoppe.android.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

/**
 * TDD Tests for PasswordPage
 */
class PasswordPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPasswordPage_displaysTitle() {
        composeTestRule.setContent {
            PasswordPage(
                onNavigateBack = {},
                onLoginSuccess = {},
                onForgotPassword = {}
            )
        }

        // Verify title/greeting is displayed
        composeTestRule
            .onNodeWithText("Hello", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testPasswordPage_displaysPasswordInput() {
        composeTestRule.setContent {
            PasswordPage(
                onNavigateBack = {},
                onLoginSuccess = {},
                onForgotPassword = {}
            )
        }

        // Verify password input is displayed
        composeTestRule
            .onNodeWithText("Password", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testPasswordPage_loginButton_callsCallback() {
        var loginSuccessCalled = false

        composeTestRule.setContent {
            PasswordPage(
                onNavigateBack = {},
                onLoginSuccess = { loginSuccessCalled = true },
                onForgotPassword = {}
            )
        }

        // Click Login button
        composeTestRule
            .onNodeWithText("Login", substring = true)
            .performClick()

        // Verify callback was called (with correct password)
        assert(loginSuccessCalled) { "Login success callback should be called" }
    }

    @Test
    fun testPasswordPage_cancelButton_callsCallback() {
        var cancelCalled = false

        composeTestRule.setContent {
            PasswordPage(
                onNavigateBack = { cancelCalled = true },
                onLoginSuccess = {},
                onForgotPassword = {}
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
    fun testPasswordPage_notYouLink_callsCallback() {
        var notYouCalled = false

        composeTestRule.setContent {
            PasswordPage(
                onNavigateBack = { notYouCalled = true },
                onLoginSuccess = {},
                onForgotPassword = {}
            )
        }

        // Click Not you? link
        composeTestRule
            .onNodeWithText("Not you?", substring = true)
            .performClick()

        // Verify callback was called
        assert(notYouCalled) { "Not you callback should be called" }
    }

    @Test
    fun testPasswordPage_forgotPasswordLink_callsCallback() {
        var forgotPasswordCalled = false

        composeTestRule.setContent {
            PasswordPage(
                onNavigateBack = {},
                onLoginSuccess = {},
                onForgotPassword = { forgotPasswordCalled = true }
            )
        }

        // Enter wrong password to show error state
        composeTestRule
            .onNodeWithText("Login", substring = true)
            .performClick()

        // Click Forgot your password? link
        composeTestRule
            .onNodeWithText("Forgot your password?", substring = true)
            .performClick()

        // Verify callback was called
        assert(forgotPasswordCalled) { "Forgot password callback should be called" }
    }
}
