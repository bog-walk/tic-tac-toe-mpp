package dev.bogwalk.ui.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.bogwalk.common.ui.components.Scores
import dev.bogwalk.common.ui.style.*
import org.junit.Rule
import org.junit.Test

internal class ScoresTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Scores loads correctly when P2 is not a bot`() {
        composeTestRule.setContent {
            Scores(0, 0, false)
        }
        composeTestRule
            .onNodeWithText(MP_P1_TEXT)
            .assertExists("Multiplayer not engaged")
        composeTestRule
            .onNodeWithText(MP_P2_TEXT)
            .assertExists("Multiplayer not engaged")
        composeTestRule
            .onAllNodesWithTag(SCORE_TEST_TAG)
            .assertCountEquals(2)
            .assertAll(hasText("0"))
    }

    @Test
    fun `Scores loads correctly when P2 is a bot`() {
        composeTestRule.setContent {
            Scores(0, 0, true)
        }
        composeTestRule
            .onNodeWithText(SP_PX_TEXT)
            .assertExists("Single player not engaged")
        composeTestRule
            .onNodeWithText(BOT_TEXT)
            .assertExists("Single player not engaged")
        composeTestRule
            .onAllNodesWithTag(SCORE_TEST_TAG)
            .assertCountEquals(2)
            .assertAll(hasText("0"))
    }

    @Test
    fun `Scores increment correctly`() {
        val p1Score = mutableStateOf(0)
        val p2Score = mutableStateOf(0)
        composeTestRule.setContent {
            Scores(p1Score.value, p2Score.value, true)
        }
        composeTestRule
            .onAllNodesWithTag(SCORE_TEST_TAG)
            .assertCountEquals(2)
            .assertAll(hasText("0"))

        p1Score.value = 1
        composeTestRule.waitForIdle()

        composeTestRule
            .onAllNodesWithTag(SCORE_TEST_TAG)
            .onFirst()
            .assertTextEquals("1")
        composeTestRule
            .onAllNodesWithTag(SCORE_TEST_TAG)
            .onLast()
            .assertTextEquals("0")

        p2Score.value = 5
        composeTestRule.waitForIdle()

        composeTestRule
            .onAllNodesWithTag(SCORE_TEST_TAG)
            .onFirst()
            .assertTextEquals("1")
        composeTestRule
            .onAllNodesWithTag(SCORE_TEST_TAG)
            .onLast()
            .assertTextEquals("5")
    }
}