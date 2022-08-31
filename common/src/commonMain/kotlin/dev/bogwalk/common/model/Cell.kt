package dev.bogwalk.common.model

import androidx.compose.runtime.Immutable
import dev.bogwalk.common.ui.views.T3Parcelize
import dev.bogwalk.common.ui.views.T3Parcelable

@Immutable
@T3Parcelize
data class Cell(
    val coordinates: Pair<Int, Int>,
    val mark: Mark = Mark.EMPTY,
    val isEnabled: Boolean = true
) : T3Parcelable