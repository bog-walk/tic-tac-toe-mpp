package dev.bogwalk.common.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import dev.bogwalk.common.model.Mark
import dev.bogwalk.common.model.GameState
import dev.bogwalk.common.model.Cell
import dev.bogwalk.common.ui.style.*

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
            .requiredSize(cellSize),
        enabled = gameState == GameState.PLAYING && cell.isEnabled,
        elevation = ButtonDefaults.elevation(defaultElevation = cellElevation),
        shape = MaterialTheme.shapes.medium,
        colors = getButtonColors(cell.mark, gameState)
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
    mark: Mark,
    gameState: GameState
): ButtonColors {
    return when (mark) {
        Mark.EMPTY -> {
            when (gameState) {
                // even though Cells are disabled when Bot is moving, they should look enabled
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