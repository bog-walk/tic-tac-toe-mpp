package dev.bogwalk.common.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.common.model.*
import dev.bogwalk.common.ui.style.*
import dev.bogwalk.common.ui.util.EntryView

@Preview
@Composable
private fun T3CellPreview() {
    T3Theme {
        Column {
            T3Cell(Cell.EMPTY, 0 to 0) {}
            T3Cell(Cell.X, 1 to 1) {}
            T3Cell(Cell.O, 2 to 2) {}
        }
    }
}

@Preview
@Composable
private fun T3GridPreview() {
    val grid = Grid("XOX OX  X")
    T3Theme {
        T3Grid(grid.cells) {}
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
            Header(BotMode.HARD, BOT_MOVE_TEXT) {}
            Header(BotMode.EASY, BOT_WIN_TEXT) {}
            Header(BotMode.HARD, SP_MOVE_TEXT) {}
            Header(BotMode.EASY, SP_WIN_TEXT) {}
            Header(null, DRAW_TEXT) {}
            Header(null, "Player X wins!") {}
        }
    }
}

@Preview
@Composable
private fun EntryViewPreview() {
    T3Theme {
        Surface(
            modifier = Modifier.width(windowWidth).height(windowHeight)
        ) {
            EntryView()
        }
    }
}