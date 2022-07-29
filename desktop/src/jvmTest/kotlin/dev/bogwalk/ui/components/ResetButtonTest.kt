package dev.bogwalk.ui.components

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.bogwalk.common.model.GameState
import dev.bogwalk.common.ui.components.ResetButton
import dev.bogwalk.common.ui.style.RESET_BUTTON_TEXT
import org.junit.Rule
import org.junit.Test

internal class ResetButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `ResetButton is disabled during game play`() {
        composeTestRule.setContent {
            ResetButton(GameState.PLAYING)
        }
        composeTestRule
            .onNodeWithText(RESET_BUTTON_TEXT)
            .assertExists("ResetButton not found")
            .assertIsNotEnabled()
    }

    @Test
    fun `ResetButton is enabled once winner found`() {
        composeTestRule.setContent {
            ResetButton(GameState.OVER_WINNER)
        }
        composeTestRule
            .onNodeWithText(RESET_BUTTON_TEXT)
            .assertExists("ResetButton not found")
            .assertIsEnabled()
    }

    @Test
    fun `ResetButton is enabled once a draw occurs`() {
        composeTestRule.setContent {
            ResetButton(GameState.OVER_DRAW)
        }
        composeTestRule
            .onNodeWithText(RESET_BUTTON_TEXT)
            .assertExists("ResetButton not found")
            .assertIsEnabled()
    }
}