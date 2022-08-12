package dev.bogwalk.common.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.common.model.Cell
import dev.bogwalk.common.model.GameState
import dev.bogwalk.common.ui.style.componentPadding

@Composable
fun T3Grid(
    gameState: GameState,
    board: String,
    onCellChosen: (Pair<Int, Int>) -> Unit
) {
    Column(
        modifier = Modifier.padding(componentPadding).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(3) { row ->
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (col in 0..2) {
                    key("$row$col") {
                        T3Cell(
                            gameState,
                            Cell.fromChar(board[row * 3 + col]),
                            row to col,
                            onCellChosen
                        )
                    }
                }
            }
        }
    }
}