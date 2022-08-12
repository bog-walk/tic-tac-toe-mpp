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
            T3Cell(GameState.PLAYING, Cell.EMPTY, 0 to 0) {}
            T3Cell(GameState.PLAYING, Cell.X, 1 to 1) {}
            T3Cell(GameState.PLAYING, Cell.O, 2 to 2) {}
            T3Cell(GameState.OVER_DRAW, Cell.EMPTY, 0 to 0) {}
        }
    }
}

@Preview
@Composable
private fun T3GridInPlayPreview() {
    T3Theme {
        T3Grid(GameState.PLAYING, "XOX OX  X") {}
    }
}

@Preview
@Composable
private fun T3GridWhenOverPreview() {
    T3Theme {
        T3Grid(GameState.OVER_WINNER, "XXX   O O") {}
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