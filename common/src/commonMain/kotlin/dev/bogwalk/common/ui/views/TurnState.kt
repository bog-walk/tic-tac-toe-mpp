package dev.bogwalk.common.ui.views

import dev.bogwalk.common.model.GameState
import dev.bogwalk.common.model.Cell
import dev.bogwalk.common.model.Player

@T3Parcelize
internal data class TurnState(
    val gameState: GameState = GameState.PLAYING,
    val turn: Player = Player.X,
    val player1Streak: Int = 0,
    val player2Streak: Int = 0,
    val instruction: String,
    val board: List<Cell>
) : T3Parcelable