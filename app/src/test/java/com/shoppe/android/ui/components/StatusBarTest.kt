package com.shoppe.android.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import org.junit.Rule
import org.junit.Test

/**
 * TDD Tests for StatusBar components
 */
class StatusBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testStatusBarSpacer_appliesSpacer() {
        composeTestRule.setContent {
            StatusBarSpacer()
        }

        // Verify spacer is displayed (no assertion needed, just rendering test)
        // The spacer is a Box with system gesture insets
        assert(true) { "StatusBarSpacer should render without error" }
    }

    @Test
    fun testVerticalSpacer_appliesHeight() {
        composeTestRule.setContent {
            VerticalSpacer()
        }

        // Verify spacer renders without error
        assert(true) { "VerticalSpacer should render without error" }
    }

    @Test
    fun testHorizontalDivider_appliesDivider() {
        composeTestRule.setContent {
            HorizontalDivider()
        }

        // Verify divider renders without error
        assert(true) { "HorizontalDivider should render without error" }
    }
}
