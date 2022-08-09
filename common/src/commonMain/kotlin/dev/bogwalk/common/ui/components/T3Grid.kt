package dev.bogwalk.common.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.common.model.Cell
import dev.bogwalk.common.ui.style.componentPadding

@Composable
fun T3Grid(
    board: List<MutableList<Cell>>,
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
                for ((col, cell) in board[row].withIndex()) {
                    key("$row$col") {
                        T3Cell(cell, row to col, onCellChosen)
                    }
                }
            }
        }
    }
}