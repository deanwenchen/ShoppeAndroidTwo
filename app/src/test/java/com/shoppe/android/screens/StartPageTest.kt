package com.shoppe.android.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

/**
 * TDD Tests for StartPage
 */
class StartPageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testStartPage_displaysShoppeTitle() {
        composeTestRule.setContent {
            StartPage(
                onNavigateToCreateAccount = {},
                onNavigateToLogin = {}
            )
        }

        // Verify Shoppe title is displayed
        composeTestRule
            .onNodeWithText("Shoppe")
            .assertIsDisplayed()
    }

    @Test
    fun testStartPage_displaysTagline() {
        composeTestRule.setContent {
            StartPage(
                onNavigateToCreateAccount = {},
                onNavigateToLogin = {}
            )
        }

        // Verify tagline is displayed
        composeTestRule
            .onNodeWithText("Your style, your way", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun testStartPage_displaysGetStartedButton() {
        composeTestRule.setContent {
            StartPage(
                onNavigateToCreateAccount = {},
                onNavigateToLogin = {}
            )
        }

        // Verify Get Started button is displayed
        composeTestRule
            .onNodeWithText("Let's get started")
            .assertIsDisplayed()
    }

    @Test
    fun testStartPage_displaysLoginLink() {
        composeTestRule.setContent {
            StartPage(
                onNavigateToCreateAccount = {},
                onNavigateToLogin = {}
            )
        }

        // Verify login link is displayed
        composeTestRule
            .onNodeWithText("I already have an account")
            .assertIsDisplayed()
    }

    @Test
    fun testStartPage_getStartedButton_clickNavigatesToCreateAccount() {
        var navigateToCreateAccountCalled = false

        composeTestRule.setContent {
            StartPage(
                onNavigateToCreateAccount = { navigateToCreateAccountCalled = true },
                onNavigateToLogin = {}
            )
        }

        // Click Get Started button
        composeTestRule
            .onNodeWithText("Let's get started")
            .performClick()

        // Verify navigation was called
        assert(navigateToCreateAccountCalled) { "Navigation to Create Account should be called" }
    }

    @Test
    fun testStartPage_loginLink_clickNavigatesToLogin() {
        var navigateToLoginCalled = false

        composeTestRule.setContent {
            StartPage(
                onNavigateToCreateAccount = {},
                onNavigateToLogin = { navigateToLoginCalled = true }
            )
        }

        // Click login link
        composeTestRule
            .onNodeWithText("I already have an account")
            .performClick()

        // Verify navigation was called
        assert(navigateToLoginCalled) { "Navigation to Login should be called" }
    }
}
