package dev.bogwalk.ui.views

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.bogwalk.common.ui.style.*
import dev.bogwalk.common.ui.views.EntryView
import org.junit.Rule
import org.junit.Test

internal class EntryViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `EntryView loads correctly`() {
        composeTestRule.setContent {
            EntryView {}
        }

        composeTestRule
            .onNodeWithText(APP_TITLE)
            .assertExists("Title text missing")
        composeTestRule
            .onAllNodesWithContentDescription(PLAYER_DESCRIPTION, useUnmergedTree = true)
            .assertCountEquals(3)
        composeTestRule
            .onAllNodesWithText(OPTIONS_TEXT, useUnmergedTree = true)
            .assertCountEquals(2)
        composeTestRule
            .onAllNodesWithContentDescription(EASY_BOT_DESCRIPTION, useUnmergedTree = true)
            .assertCountEquals(1)
        composeTestRule
            .onAllNodesWithTag(GAME_MODE_TEST_TAG)
            .assertCountEquals(2)
            .assertAll(isEnabled() and isFocusable() and hasClickAction())
    }
}