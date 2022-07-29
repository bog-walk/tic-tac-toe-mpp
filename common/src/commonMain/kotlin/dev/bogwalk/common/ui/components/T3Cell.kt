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
import dev.bogwalk.common.ui.style.*

@Composable
fun T3Cell(cell: Cell) {
    Button(
        onClick = {},
        modifier = Modifier
            .testTag(CELL_TEST_TAG)
            .padding(cellPadding)
            .requiredSize(cellSize),
        enabled = cell == Cell.EMPTY,
        elevation = ButtonDefaults.elevation(defaultElevation = cellElevation),
        shape = MaterialTheme.shapes.medium,
        colors = getButtonColors(cell)
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
private fun getButtonColors(cell: Cell): ButtonColors {
    return when (cell) {
        Cell.EMPTY -> {
            ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.onBackground
            )
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