package dev.bogwalk.common.ui.views

import dev.bogwalk.common.model.GameState
import dev.bogwalk.common.model.Cell

internal data class TurnState(
    val gameState: GameState = GameState.PLAYING,
    val instruction: String,
    val board: List<Cell>,
    val player1Streak: Int = 0,
    val player2Streak: Int = 0
)