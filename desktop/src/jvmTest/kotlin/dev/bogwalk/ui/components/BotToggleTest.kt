package dev.bogwalk.ui.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.bogwalk.common.model.BotMode
import dev.bogwalk.common.ui.components.BotToggle
import dev.bogwalk.common.ui.style.EASY_BOT_DESCRIPTION
import dev.bogwalk.common.ui.style.HARD_BOT_DESCRIPTION
import dev.bogwalk.common.ui.style.TOGGLE_TEST_TAG
import org.junit.Rule
import org.junit.Test

internal class BotToggleTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `BotToggle loads correctly in single player`() {
        composeTestRule.setContent {
            BotToggle(BotMode.EASY) {}
        }

        composeTestRule
            .onNodeWithContentDescription(EASY_BOT_DESCRIPTION)
            .assertExists()
            .assert(isNotFocusable() and !isSelectable())
        composeTestRule
            .onNodeWithTag(TOGGLE_TEST_TAG)
            .assertIsEnabled()
            .assertIsToggleable()
    }

    @Test
    fun `BotToggle switches modes correctly`() {
        val mode = mutableStateOf(BotMode.EASY)
        composeTestRule.setContent {
            BotToggle(mode.value) {}
        }

        composeTestRule
            .onNodeWithContentDescription(EASY_BOT_DESCRIPTION)
            .assertExists()
            .assert(isNotFocusable() and !isSelectable())

        mode.value = BotMode.HARD
        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithContentDescription(HARD_BOT_DESCRIPTION)
            .assertExists()
            .assert(isNotFocusable() and !isSelectable())
    }

    @Test
    fun `BotToggle loads correctly in double player`() {
        composeTestRule.setContent {
            BotToggle(null) {}
        }

        composeTestRule
            .onNodeWithContentDescription(EASY_BOT_DESCRIPTION)
            .assertExists()
            .assert(isNotFocusable() and !isSelectable())
        composeTestRule
            .onNodeWithTag(TOGGLE_TEST_TAG)
            .assertIsNotEnabled()
    }
}