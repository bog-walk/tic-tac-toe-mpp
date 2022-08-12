package dev.bogwalk.common.ui.views

import dev.bogwalk.common.model.GameMode

sealed class Screen {
    object Entry : Screen()
    data class Game(val mode: GameMode) : Screen()
}