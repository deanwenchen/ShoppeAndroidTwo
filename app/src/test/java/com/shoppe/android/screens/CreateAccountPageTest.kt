package com.shoppe.android.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test

/**
 * TDD Tests for CreateAccountPage
 */
class CreateAccountPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCreateAccountPage_displaysTitle() {
        composeTestRule.setContent {
            CreateAccountPage(
                onNavigateBack = {},
                onNavigateToLogin = {}
            )
        }

        // Verify title is displayed
        composeTestRule
            .onNodeWithText("Create Account", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testCreateAccountPage_displaysAvatarUpload() {
        composeTestRule.setContent {
            CreateAccountPage(
                onNavigateBack = {},
                onNavigateToLogin = {}
            )
        }

        // Verify avatar upload is displayed
        composeTestRule
            .onNodeWithContentDescription("Upload avatar")
            .assertIsDisplayed()
    }

    @Test
    fun testCreateAccountPage_displaysEmailInput() {
        composeTestRule.setContent {
            CreateAccountPage(
                onNavigateBack = {},
                onNavigateToLogin = {}
            )
        }

        // Verify email input is displayed
        composeTestRule
            .onNodeWithText("Email", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testCreateAccountPage_displaysPasswordInput() {
        composeTestRule.setContent {
            CreateAccountPage(
                onNavigateBack = {},
                onNavigateToLogin = {}
            )
        }

        // Verify password input is displayed
        composeTestRule
            .onNodeWithText("Password", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testCreateAccountPage_displaysPhoneInput() {
        composeTestRule.setContent {
            CreateAccountPage(
                onNavigateBack = {},
                onNavigateToLogin = {}
            )
        }

        // Verify phone input is displayed
        composeTestRule
            .onNodeWithText("Phone", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testCreateAccountPage_doneButton_callsCallback() {
        var doneCalled = false

        composeTestRule.setContent {
            CreateAccountPage(
                onNavigateBack = {},
                onNavigateToLogin = { doneCalled = true }
            )
        }

        // Fill in all fields
        composeTestRule
            .onNodeWithText("Email", substring = true)
            .performTextInput("test@example.com")

        composeTestRule
            .onNodeWithText("Password", substring = true)
            .performTextInput("password123")

        composeTestRule
            .onNodeWithText("Phone", substring = true)
            .performTextInput("+1234567890")

        // Click Done button
        composeTestRule
            .onNodeWithText("Done", substring = true)
            .performClick()

        // Verify callback was called
        assert(doneCalled) { "Done callback should be called" }
    }

    @Test
    fun testCreateAccountPage_cancelButton_callsCallback() {
        var cancelCalled = false

        composeTestRule.setContent {
            CreateAccountPage(
                onNavigateBack = { cancelCalled = true },
                onNavigateToLogin = {}
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
    fun testCreateAccountPage_validationShowsEmailError() {
        composeTestRule.setContent {
            CreateAccountPage(
                onNavigateBack = {},
                onNavigateToLogin = {}
            )
        }

        // Fill password but leave email empty
        composeTestRule
            .onNodeWithText("Password", substring = true)
            .performTextInput("password123")

        // Click Done button
        composeTestRule
            .onNodeWithText("Done", substring = true)
            .performClick()

        // Verify email error is displayed
        composeTestRule
            .onNodeWithText("Please enter your email", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testCreateAccountPage_validationShowsPasswordError() {
        composeTestRule.setContent {
            CreateAccountPage(
                onNavigateBack = {},
                onNavigateToLogin = {}
            )
        }

        // Fill email but leave password empty
        composeTestRule
            .onNodeWithText("Email", substring = true)
            .performTextInput("test@example.com")

        // Click Done button
        composeTestRule
            .onNodeWithText("Done", substring = true)
            .performClick()

        // Verify password error is displayed
        composeTestRule
            .onNodeWithText("Please enter a password", substring = true)
            .assertIsDisplayed()
    }
}
