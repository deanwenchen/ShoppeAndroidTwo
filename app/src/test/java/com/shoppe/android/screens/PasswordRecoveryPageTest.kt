package com.shoppe.android.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

/**
 * TDD Tests for PasswordRecoveryPage
 */
class PasswordRecoveryPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPasswordRecoveryPage_displaysTitle() {
        composeTestRule.setContent {
            PasswordRecoveryPage(
                onNavigateBack = {},
                onRecoveryMethodSelected = {}
            )
        }

        // Verify title is displayed
        composeTestRule
            .onNodeWithText("Reset Password", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testPasswordRecoveryPage_displaysInstruction() {
        composeTestRule.setContent {
            PasswordRecoveryPage(
                onNavigateBack = {},
                onRecoveryMethodSelected = {}
            )
        }

        // Verify instruction text is displayed
        composeTestRule
            .onNodeWithText("Select how you want to receive the verification code", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testPasswordRecoveryPage_displaysSmsOption() {
        composeTestRule.setContent {
            PasswordRecoveryPage(
                onNavigateBack = {},
                onRecoveryMethodSelected = {}
            )
        }

        // Verify SMS option is displayed
        composeTestRule
            .onNodeWithText("Via SMS", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testPasswordRecoveryPage_displaysEmailOption() {
        composeTestRule.setContent {
            PasswordRecoveryPage(
                onNavigateBack = {},
                onRecoveryMethodSelected = {}
            )
        }

        // Verify Email option is displayed
        composeTestRule
            .onNodeWithText("Via Email", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testPasswordRecoveryPage_smsOptionClick_callsCallback() {
        var smsSelected = false

        composeTestRule.setContent {
            PasswordRecoveryPage(
                onNavigateBack = {},
                onRecoveryMethodSelected = { method ->
                    smsSelected = (method == "sms")
                }
            )
        }

        // Click SMS option
        composeTestRule
            .onNodeWithText("Via SMS", substring = true)
            .performClick()

        // Verify callback was called with sms
        assert(smsSelected) { "SMS method should be selected" }
    }

    @Test
    fun testPasswordRecoveryPage_emailOptionClick_callsCallback() {
        var emailSelected = false

        composeTestRule.setContent {
            PasswordRecoveryPage(
                onNavigateBack = {},
                onRecoveryMethodSelected = { method ->
                    emailSelected = (method == "email")
                }
            )
        }

        // Click Email option
        composeTestRule
            .onNodeWithText("Via Email", substring = true)
            .performClick()

        // Verify callback was called with email
        assert(emailSelected) { "Email method should be selected" }
    }
}
