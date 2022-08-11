package dev.bogwalk.common.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import dev.bogwalk.common.model.Cell
import dev.bogwalk.common.model.GameState
import dev.bogwalk.common.ui.style.*

@Composable
fun T3Cell(
    gameState: GameState,
    cell: Cell,
    coordinates: Pair<Int, Int>,
    onCellChosen: (Pair<Int, Int>) -> Unit
) {
    Button(
        onClick = { onCellChosen(coordinates) },
        modifier = Modifier
            .testTag(CELL_TEST_TAG)
            .padding(cellPadding)
            .requiredSize(cellSize),
        enabled = gameState == GameState.PLAYING && cell == Cell.EMPTY,
        elevation = ButtonDefaults.elevation(defaultElevation = cellElevation),
        shape = MaterialTheme.shapes.medium,
        colors = getButtonColors(cell, gameState)
    ) {
        Text(
            text = cell.mark.toString(),
            fontSize = cellFontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun getButtonColors(
    cell: Cell,
    gameState: GameState
): ButtonColors {
    return when (cell) {
        Cell.EMPTY -> {
            when (gameState) {
                GameState.PLAYING -> ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.onBackground
                )
                else -> ButtonDefaults.buttonColors()
            }
        }
        Cell.X -> {
            ButtonDefaults.buttonColors(
                disabledBackgroundColor = MaterialTheme.colors.primary,
                disabledContentColor = MaterialTheme.colors.onSurface
            )
        }
        Cell.O -> {
            ButtonDefaults.buttonColors(
                disabledBackgroundColor = MaterialTheme.colors.secondary,
                disabledContentColor = MaterialTheme.colors.onSurface
            )
        }
    }
}