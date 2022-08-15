package dev.bogwalk.common.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.common.model.*
import dev.bogwalk.common.ui.style.*

@Preview
@Composable
private fun T3CellPreview() {
    T3Theme {
        Column {
            T3Cell(GameState.PLAYING, Cell(0 to 0)) {}
            T3Cell(GameState.PLAYING, Cell(0 to 0, Mark.X, false)) {}
            T3Cell(GameState.PLAYING, Cell(0 to 0, Mark.O, false)) {}
            T3Cell(GameState.OVER_DRAW, Cell(0 to 0)) {}
        }
    }
}

@Preview
@Composable
private fun T3GridInPlayPreview() {
    val board = listOf(
        Cell(0 to 0, Mark.X, false), Cell(0 to 1, Mark.O, false),
        Cell(0 to 2, Mark.X, false), Cell(1 to 0),
        Cell(1 to 1, Mark.O, false), Cell(1 to 2), Cell(2 to 0),
        Cell(2 to 1), Cell(2 to 2, Mark.X, false)
    )
    T3Theme {
        T3Grid(GameState.PLAYING, board) {}
    }
}

@Preview
@Composable
private fun T3GridWhenOverPreview() {
    val board = listOf(
        Cell(0 to 0, Mark.X, false), Cell(0 to 1, Mark.X, false),
        Cell(0 to 2, Mark.X, false), Cell(1 to 0), Cell(1 to 1),
        Cell(1 to 2), Cell(2 to 0, Mark.O, false), Cell(2 to 1),
        Cell(2 to 2, Mark.O, false)
    )
    T3Theme {
        T3Grid(GameState.OVER_WINNER, board) {}
    }
}

@Preview
@Composable
private fun ResetButtonPreview() {
    T3Theme {
        Column(
            modifier = Modifier.width(windowWidth)
        ) {
            ResetButton(GameState.PLAYING) {}
            ResetButton(GameState.OVER_DRAW) {}
            ResetButton(GameState.OVER_WINNER) {}
        }
    }
}

@Preview
@Composable
private fun ScoresPreview() {
    T3Theme {
        Column(
            modifier = Modifier.width(windowWidth),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
            BotToggle(null) {}
            BotToggle(BotMode.EASY) {}
            BotToggle(BotMode.HARD) {}
        }
    }
}

@Preview
@Composable
private fun HeaderPreview() {
    T3Theme {
        Column(
            modifier = Modifier.width(windowWidth)
        ) {
            Header({}, BOT_MOVE_TEXT, BotMode.HARD) {}
            Header({}, BOT_WIN_TEXT, BotMode.EASY) {}
            Header({}, SP_MOVE_TEXT, BotMode.HARD) {}
            Header({}, SP_WIN_TEXT, BotMode.EASY) {}
            Header({}, DRAW_TEXT, null) {}
            Header({}, "Player X wins!", null) {}
        }
    }
}