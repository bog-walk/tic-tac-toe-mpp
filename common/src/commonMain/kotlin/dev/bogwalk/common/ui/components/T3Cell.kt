package dev.bogwalk.common.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import dev.bogwalk.common.model.Mark
import dev.bogwalk.common.model.GameState
import dev.bogwalk.common.model.Cell
import dev.bogwalk.common.ui.style.*

/**
 * Button representing each Cell in game Grid, with responsive size and contents based on
 * constraints set by parent T3Grid.
 */
@Composable
fun T3Cell(
    gameState: GameState,
    cell: Cell,
    onCellChosen: (Pair<Int, Int>) -> Unit
) {
    Button(
        onClick = { onCellChosen(cell.coordinates) },
        modifier = Modifier
            .testTag(CELL_TEST_TAG)
            .padding(smallPadding)
            .sizeIn(maxHeight = cellSize, maxWidth = cellSize)
            .aspectRatio(1.0F),
        enabled = gameState == GameState.PLAYING && cell.isEnabled,
        elevation = ButtonDefaults.elevation(defaultElevation = cellElevation),
        shape = MaterialTheme.shapes.medium,
        colors = getButtonColors(cell.mark, gameState)
    ) {
        Text(
            modifier = Modifier.wrapContentSize(Alignment.Center, true),
            text = cell.mark.toString(),
            fontSize = cellFontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun getButtonColors(
    mark: Mark,
    gameState: GameState
): ButtonColors {
    return when (mark) {
        Mark.EMPTY -> {
            when (gameState) {
                // even though unmarked Cells are disabled during Bot's turn,
                // their style remains unchanged
                GameState.PLAYING, GameState.BOT_TURN -> ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.onBackground,
                    disabledBackgroundColor = MaterialTheme.colors.onBackground
                )
                else -> ButtonDefaults.buttonColors()
            }
        }
        Mark.X -> {
            ButtonDefaults.buttonColors(
                disabledBackgroundColor = MaterialTheme.colors.primary,
                disabledContentColor = MaterialTheme.colors.onSurface
            )
        }
        Mark.O -> {
            ButtonDefaults.buttonColors(
                disabledBackgroundColor = MaterialTheme.colors.secondary,
                disabledContentColor = MaterialTheme.colors.onSurface
            )
        }
    }
}