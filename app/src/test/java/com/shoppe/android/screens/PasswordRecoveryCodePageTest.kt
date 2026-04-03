package com.shoppe.android.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

/**
 * TDD Tests for PasswordRecoveryCodePage
 */
class PasswordRecoveryCodePageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPasswordRecoveryCodePage_displaysTitle() {
        composeTestRule.setContent {
            PasswordRecoveryCodePage(
                onNavigateBack = {},
                onCodeVerified = {},
                onResendCode = {}
            )
        }

        // Verify title is displayed
        composeTestRule
            .onNodeWithText("Verification Code", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testPasswordRecoveryCodePage_displaysInstruction() {
        composeTestRule.setContent {
            PasswordRecoveryCodePage(
                onNavigateBack = {},
                onCodeVerified = {},
                onResendCode = {}
            )
        }

        // Verify instruction text is displayed
        composeTestRule
            .onNodeWithText("Enter the verification code", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testPasswordRecoveryCodePage_displaysResendOption() {
        composeTestRule.setContent {
            PasswordRecoveryCodePage(
                onNavigateBack = {},
                onCodeVerified = {},
                onResendCode = {}
            )
        }

        // Verify Resend option is displayed
        composeTestRule
            .onNodeWithText("Resend", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testPasswordRecoveryCodePage_resendClick_callsCallback() {
        var resendClicked = false

        composeTestRule.setContent {
            PasswordRecoveryCodePage(
                onNavigateBack = {},
                onCodeVerified = {},
                onResendCode = {
                    resendClicked = true
                }
            )
        }

        // Click Resend
        composeTestRule
            .onNodeWithText("Resend", substring = true)
            .performClick()

        // Verify callback was called
        assert(resendClicked) { "Resend callback should be called" }
    }

    @Test
    fun testPasswordRecoveryCodePage_cancelClick_callsCallback() {
        var cancelClicked = false

        composeTestRule.setContent {
            PasswordRecoveryCodePage(
                onNavigateBack = {},
                onCodeVerified = {},
                onResendCode = {}
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
