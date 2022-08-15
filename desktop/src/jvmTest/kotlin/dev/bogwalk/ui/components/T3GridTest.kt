package dev.bogwalk.ui.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.bogwalk.common.model.Cell
import dev.bogwalk.common.model.GameState
import dev.bogwalk.common.model.Mark
import dev.bogwalk.common.ui.components.T3Grid
import dev.bogwalk.common.ui.style.CELL_TEST_TAG
import org.junit.Rule
import org.junit.Test

internal class T3GridTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `T3Grid loads correctly at game start`() {
        val board = List(3) { r -> List(3) { c -> Cell(r to c)} }.flatten()
        composeTestRule.setContent {
            T3Grid(GameState.PLAYING, board) {}
        }

        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)
            .assertCountEquals(9)
            .assertAll(isEnabled() and hasText(" ") and hasClickAction())
    }

    @Test
    fun `T3Grid loads correctly during game play`() {
        val board = listOf(
            Cell(0 to 0, Mark.X, false), Cell(0 to 1, Mark.O, false),
            Cell(0 to 2, Mark.X, false), Cell(1 to 0),
            Cell(1 to 1), Cell(1 to 2, Mark.O, false), Cell(2 to 0),
            Cell(2 to 1, Mark.X, false), Cell(2 to 2)
        )
        composeTestRule.setContent {
            T3Grid(GameState.PLAYING, board) {}
        }

        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)
            .assertCountEquals(9)
            .filter(isEnabled())
            .assertCountEquals(4)
            .assertAll(hasText(" "))
        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)
            .assertCountEquals(9)
            .filter(isNotEnabled())
            .assertCountEquals(5)
            .onFirst()
            .assertTextEquals("X")
    }

    @Test
    fun `T3Grid is temporarily disabled during bot turn`() {
        val state = mutableStateOf(GameState.BOT_TURN)
        val board = listOf(
            Cell(0 to 0, Mark.X, false), Cell(0 to 1, Mark.O, false),
            Cell(0 to 2, Mark.X, false), Cell(1 to 0, Mark.O, false),
            Cell(1 to 1, Mark.X, false), Cell(1 to 2), Cell(2 to 0),
            Cell(2 to 1), Cell(2 to 2)
        )
        composeTestRule.setContent {
            T3Grid(state.value, board) {}
        }

        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)
            .assertAll(isNotEnabled())

        state.value = GameState.PLAYING
        composeTestRule.waitForIdle()

        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)
            .filter(isEnabled())
            .assertCountEquals(4)
    }

    @Test
    fun `T3Grid is fully disabled when game over`() {
        val board = listOf(
            Cell(0 to 0, Mark.X, false), Cell(0 to 1, Mark.X, false),
            Cell(0 to 2, Mark.X, false), Cell(1 to 0, Mark.O, false),
            Cell(1 to 1, Mark.O, false), Cell(1 to 2), Cell(2 to 0),
            Cell(2 to 1), Cell(2 to 2)
        )
        composeTestRule.setContent {
            T3Grid(GameState.OVER_WINNER, board) {}
        }

        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)
            .assertAll(isNotEnabled())
    }
}