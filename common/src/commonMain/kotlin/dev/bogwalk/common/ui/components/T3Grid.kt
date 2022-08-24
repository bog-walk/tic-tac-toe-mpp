package dev.bogwalk.common.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.common.model.GameState
import dev.bogwalk.common.model.Cell
import dev.bogwalk.common.ui.style.smallPadding

@Composable
fun T3Grid(
    gameState: GameState,
    board: List<Cell>,
    modifier: Modifier = Modifier,
    onCellChosen: (Pair<Int, Int>) -> Unit
) {
    Column(
        modifier = modifier.padding(top = smallPadding, bottom = smallPadding).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(3) { row ->
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (col in 0..2) {
                    key("$row$col") {
                        T3Cell(
                            gameState,
                            board[row * 3 + col],
                            onCellChosen
                        )
                    }
                }
            }
        }
    }
}