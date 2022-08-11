package dev.bogwalk.ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import dev.bogwalk.common.model.GameState
import dev.bogwalk.common.model.Grid
import dev.bogwalk.common.ui.components.T3Grid
import dev.bogwalk.common.ui.style.CELL_TEST_TAG
import org.junit.Rule
import org.junit.Test

internal class T3GridTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `T3Grid loads correctly at game start`() {
        val grid = Grid("         ")
        composeTestRule.setContent {
            T3Grid(GameState.PLAYING, grid.cells) {}
        }
        composeTestRule
            .onAllNodesWithTag(CELL_TEST_TAG)
            .assertCountEquals(9)
            .assertAll(isEnabled() and hasText(" ") and hasClickAction())
    }

    @Test
    fun `T3Grid loads correctly during game play`() {
        val grid = Grid("XOX  O X ")
        composeTestRule.setContent {
            T3Grid(GameState.PLAYING, grid.cells) {}
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
}