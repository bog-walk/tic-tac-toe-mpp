package dev.bogwalk.ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.bogwalk.common.model.BotMode
import dev.bogwalk.common.ui.components.HeaderBar
import dev.bogwalk.common.ui.components.HeaderText
import dev.bogwalk.common.ui.style.*
import org.junit.Rule
import org.junit.Test

internal class HeaderTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Header loads correctly in single player`() {
        composeTestRule.setContent {
            HeaderBar({}, BotMode.EASY) {}
            HeaderText(BOT_MOVE_TEXT)
        }

        composeTestRule
            .onNodeWithContentDescription(HOME_DESCRIPTION)
            .assertExists("Home icon missing")
            .assertIsEnabled()
            .assertHasClickAction()
        composeTestRule
            .onNodeWithText(BOT_MOVE_TEXT)
            .assertExists("Instruction text missing")
        composeTestRule.onNodeWithTag(TOGGLE_TEST_TAG)
            .assertIsEnabled()
            .assertIsToggleable()
    }

    @Test
    fun `Header loads correctly in double player`() {
        composeTestRule.setContent {
            HeaderBar({}, null) {}
            HeaderText(DRAW_TEXT)
        }

        composeTestRule
            .onNodeWithContentDescription(HOME_DESCRIPTION)
            .assertExists("Home icon missing")
            .assertIsEnabled()
            .assertHasClickAction()
        composeTestRule
            .onNodeWithText(DRAW_TEXT)
            .assertExists("Instruction text missing")
        composeTestRule.onNodeWithTag(TOGGLE_TEST_TAG)
            .assertIsNotEnabled()
            .assertIsOff()
    }
}