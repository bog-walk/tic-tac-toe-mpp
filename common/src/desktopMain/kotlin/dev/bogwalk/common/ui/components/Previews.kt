package dev.bogwalk.common.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            T3Cell(GameState.BOT_TURN, Cell(0 to 0)) {}
            T3Cell(GameState.BOT_TURN, Cell(0 to 0, Mark.X, false)) {}
        }
    }
}

@Preview
@Composable
private fun T3CellConstrainedPreview() {
    T3Theme {
        Column(
            modifier = Modifier.width(cellSize * 0.8F)
        ) {
            T3Cell(GameState.PLAYING, Cell(0 to 0)) {}
            T3Cell(GameState.PLAYING, Cell(0 to 0, Mark.X, false)) {}
            T3Cell(GameState.PLAYING, Cell(0 to 0, Mark.O, false)) {}
            T3Cell(GameState.OVER_DRAW, Cell(0 to 0)) {}
            T3Cell(GameState.BOT_TURN, Cell(0 to 0)) {}
            T3Cell(GameState.BOT_TURN, Cell(0 to 0, Mark.X, false)) {}
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
private fun T3GridDuringBotTurnPreview() {
    val board = listOf(
        Cell(0 to 0, Mark.X, false), Cell(0 to 1, Mark.O, false),
        Cell(0 to 2, Mark.X, false), Cell(1 to 0),
        Cell(1 to 1, Mark.O, false), Cell(1 to 2), Cell(2 to 0),
        Cell(2 to 1), Cell(2 to 2, Mark.X, false)
    )
    T3Theme {
        T3Grid(GameState.BOT_TURN, board) {}
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
private fun T3GridInLandscapePreview() {
    val board = listOf(
        Cell(0 to 0, Mark.X, false), Cell(0 to 1, Mark.O, false),
        Cell(0 to 2, Mark.X, false), Cell(1 to 0),
        Cell(1 to 1, Mark.O, false), Cell(1 to 2), Cell(2 to 0),
        Cell(2 to 1), Cell(2 to 2, Mark.X, false)
    )
    T3Theme {
        Row(
            modifier = Modifier.size(width = 800.dp, height = 280.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            T3Grid(GameState.PLAYING, board, Modifier.weight(LANDSCAPE_WEIGHT_L)) {}
            Box(modifier = Modifier.weight(LANDSCAPE_WEIGHT_S).padding(end = componentPadding)) {
                HeaderText(BOT_MOVE_TEXT)
            }
        }
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
            HeaderBar({}, BotMode.HARD) {}
            HeaderText(BOT_MOVE_TEXT)
            HeaderBar({}, BotMode.EASY) {}
            HeaderText(BOT_WIN_TEXT)
            HeaderBar({}, BotMode.HARD) {}
            HeaderText(SP_MOVE_TEXT)
            HeaderBar({}, BotMode.EASY) {}
            HeaderText(SP_WIN_TEXT)
            HeaderBar({}, null) {}
            HeaderText(DRAW_TEXT)
            HeaderBar({}, null) {}
            HeaderText("Player X wins?")
        }
    }
}