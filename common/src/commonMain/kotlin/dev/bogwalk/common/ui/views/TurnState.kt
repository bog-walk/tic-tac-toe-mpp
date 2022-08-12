package dev.bogwalk.common.ui.views

import dev.bogwalk.common.model.GameState

internal data class TurnState(
    val gameState: GameState = GameState.PLAYING,
    val instruction: String,
    val board: String,
    val player1Streak: Int = 0,
    val player2Streak: Int = 0
)