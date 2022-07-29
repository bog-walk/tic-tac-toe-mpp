package dev.bogwalk.common.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.bogwalk.common.model.*
import dev.bogwalk.common.ui.style.*

@Preview
@Composable
private fun T3CellPreview() {
    T3Theme {
        Column {
            T3Cell(Cell.EMPTY)
            T3Cell(Cell.X)
            T3Cell(Cell.O)
        }
    }
}

@Preview
@Composable
private fun T3GridPreview() {
    val grid = Grid("XOX OX  X")
    T3Theme {
        T3Grid(grid)
    }
}

@Preview
@Composable
private fun ResetButtonPreview() {
    T3Theme {
        Column {
            ResetButton(GameState.PLAYING)
            ResetButton(GameState.OVER_DRAW)
            ResetButton(GameState.OVER_WINNER)
        }
    }
}

@Preview
@Composable
private fun ScoresPreview() {
    T3Theme {
        Column {
            Scores(0, 0, false)
            Scores(0, 0, true)
            Scores(5, 9, false)
            Scores(12, 28, true)
            Scores(100, 134, true)
            Scores(100, 134, false)
        }
    }
}

@Preview
@Composable
private fun BotTogglePreview() {
    T3Theme {
        Column {
            BotToggle(BotMode.EASY)
            BotToggle(BotMode.HARD)
        }
    }
}

@Preview
@Composable
private fun HeaderPreview() {
    T3Theme {
        Column(
            modifier = Modifier.requiredWidth(windowWidth)
        ) {
            Header(BotMode.HARD, BOT_MOVE_TEXT)
            Header(BotMode.EASY, BOT_WIN_TEXT)
            Header(BotMode.HARD, SP_MOVE_TEXT)
            Header(BotMode.EASY, "Player X$MP_MOVE_TEXT")
            Header(BotMode.HARD, "Player X$MP_WIN_TEXT")
        }
    }
}