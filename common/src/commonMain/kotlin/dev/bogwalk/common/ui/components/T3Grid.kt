package dev.bogwalk.common.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import dev.bogwalk.common.model.Grid

@Composable
fun T3Grid(grid: Grid) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(3) { row ->
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for ((i, cell) in grid.cells[row].withIndex()) {
                    key("$row$i") {
                        T3Cell(cell)
                    }
                }
            }
        }
    }
}