package dev.bogwalk.ui.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.bogwalk.common.model.Cell
import dev.bogwalk.common.model.Mark
import dev.bogwalk.common.model.GameState
import dev.bogwalk.common.ui.components.T3Cell
import org.junit.Rule
import org.junit.Test

internal class T3CellTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `T3Cell is disabled when marked`() {
        val cell = mutableStateOf(Cell(0 to 0))
        composeTestRule.setContent {
            T3Cell(GameState.PLAYING, cell.value) {}
        }

        composeTestRule
            .onNodeWithText(" ")
            .assertExists("Empty cell not found")
            .assertIsEnabled()

        cell.value = Cell(0 to 0, Mark.X, false)
        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithText("X")
            .assertExists("Marked cell not found")
            .assertIsNotEnabled()
    }

    @Test
    fun `T3Cell is disabled when bot is moving`() {
        val state = mutableStateOf(GameState.PLAYING)
        composeTestRule.setContent {
            T3Cell(state.value, Cell(0 to 0)) {}
        }

        composeTestRule
            .onNodeWithText(" ")
            .assertExists("Empty cell not found")
            .assertIsEnabled()

        state.value = GameState.BOT_TURN
        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithText(" ")
            .assertExists("Empty cell not found")
            .assertIsNotEnabled()
    }

    @Test
    fun `T3Cell is disabled when game over`() {
        val state = mutableStateOf(GameState.PLAYING)
        composeTestRule.setContent {
            T3Cell(state.value, Cell(0 to 0)) {}
        }

        composeTestRule
            .onNodeWithText(" ")
            .assertExists("Empty cell not found")
            .assertIsEnabled()

        state.value = GameState.OVER_WINNER
        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithText(" ")
            .assertExists("Empty cell not found")
            .assertIsNotEnabled()
    }
}