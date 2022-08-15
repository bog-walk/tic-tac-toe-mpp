package dev.bogwalk.common.model

import androidx.compose.runtime.Immutable

@Immutable
data class Cell(
    val coordinates: Pair<Int, Int>,
    val mark: Mark = Mark.EMPTY,
    val isEnabled: Boolean = true
)