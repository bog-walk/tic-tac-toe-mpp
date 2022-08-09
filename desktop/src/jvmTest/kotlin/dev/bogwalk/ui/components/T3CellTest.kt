package dev.bogwalk.ui.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.bogwalk.common.model.Cell
import dev.bogwalk.common.ui.components.T3Cell
import org.junit.Rule
import org.junit.Test

internal class T3CellTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `T3Cell is enabled only when unmarked`() {
        val mark = mutableStateOf(Cell.EMPTY)
        composeTestRule.setContent {
            T3Cell(mark.value, 0 to 0) {}
        }
        composeTestRule
            .onNodeWithText(" ")
            .assertExists("Empty cell not found")
            .assertIsEnabled()

        mark.value = Cell.X
        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithText("X")
            .assertExists("Marked cell not found")
            .assertIsNotEnabled()
    }
}