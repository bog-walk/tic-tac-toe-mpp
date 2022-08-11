package dev.bogwalk.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.bogwalk.common.TicTacToeApp
import dev.bogwalk.common.model.Cell
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.style.*
import org.junit.Rule
import org.junit.Test

internal class TicTacToeAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `TicTacToeApp runs correctly in double player mode`() {
        composeTestRule.setContent {
            TicTacToeApp(GameMode.DOUBLE)
        }

        // initial set up as expected
        composeTestRule.onNodeWithContentDescription(HOME_DESCRIPTION).assertExists()
        composeTestRule.onNodeWithText("Player X turn").assertExists()
        composeTestRule.onNodeWithTag(TOGGLE_TEST_TAG). assertIsNotEnabled()
        composeTestRule
            .onNodeWithText(PX_TEXT).assertExists()
            .onSiblings()
            .assertAny(hasTestTag(SCORE_TEST_TAG) and hasTextExactly("0"))
        composeTestRule
            .onNodeWithText(PO_TEXT).assertExists()
            .onSiblings()
            .assertAny(hasTestTag(SCORE_TEST_TAG) and hasTextExactly("0"))
        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)
            .assertAll(isEnabled() and hasTextExactly(Cell.EMPTY.mark.toString()))
        composeTestRule.onNodeWithText(RESET_BUTTON_TEXT).assertIsNotEnabled()

        // first moves by each player
        composeTestRule.onAllNodesWithTag(CELL_TEST_TAG).onFirst().performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Player O turn").assertExists()
        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)
            .onFirst()
            .assertIsNotEnabled()
            .assert(hasTextExactly(Cell.X.mark.toString()))
        composeTestRule.onAllNodesWithTag(CELL_TEST_TAG).onLast().performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Player X turn").assertExists()
        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)
            .onLast()
            .assertIsNotEnabled()
            .assert(hasTextExactly(Cell.O.mark.toString()))

        // second moves by each player
        composeTestRule.onAllNodesWithTag(CELL_TEST_TAG)[1].performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Player O turn").assertExists()
        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)[1]
            .assertIsNotEnabled()
            .assert(hasTextExactly(Cell.X.mark.toString()))
        composeTestRule.onAllNodesWithTag(CELL_TEST_TAG)[7].performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Player X turn").assertExists()
        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)[7]
            .assertIsNotEnabled()
            .assert(hasTextExactly(Cell.O.mark.toString()))

        // winning move by player 1
        composeTestRule.onAllNodesWithTag(CELL_TEST_TAG)[2].performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Player X wins!").assertExists()
        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)[2]
            .assertIsNotEnabled()
            .assert(hasTextExactly(Cell.X.mark.toString()))
        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)
            .assertAll(isNotEnabled())
        composeTestRule
            .onNodeWithText(PX_TEXT)
            .onSiblings()
            .assertAny(hasTestTag(SCORE_TEST_TAG) and hasTextExactly("1"))
        composeTestRule
            .onNodeWithText(PO_TEXT)
            .onSiblings()
            .assertAny(hasTestTag(SCORE_TEST_TAG) and hasTextExactly("0"))
        composeTestRule.onNodeWithText(RESET_BUTTON_TEXT).assertIsEnabled()

        // play again
        composeTestRule.onNodeWithText(RESET_BUTTON_TEXT).performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Player X turn").assertExists()
        composeTestRule.onNodeWithTag(TOGGLE_TEST_TAG). assertIsNotEnabled()
        composeTestRule
            .onNodeWithText(PX_TEXT)
            .onSiblings()
            .assertAny(hasTestTag(SCORE_TEST_TAG) and hasTextExactly("1"))
        composeTestRule
            .onNodeWithText(PO_TEXT)
            .onSiblings()
            .assertAny(hasTestTag(SCORE_TEST_TAG) and hasTextExactly("0"))
        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)
            .assertAll(isEnabled() and hasTextExactly(Cell.EMPTY.mark.toString()))
        composeTestRule.onNodeWithText(RESET_BUTTON_TEXT).assertIsNotEnabled()
    }
}






