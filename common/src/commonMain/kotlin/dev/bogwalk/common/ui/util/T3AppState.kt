package dev.bogwalk.common.ui.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import dev.bogwalk.common.model.*
import dev.bogwalk.common.ui.style.*

class T3AppState(
    private val mode: GameMode
) {
    private val grid = Grid()
    private val bot: Bot? = when (mode) {
        GameMode.SINGLE -> Bot(grid)
        GameMode.DOUBLE -> null
    }
    private var turn = Player.X

    val board: List<MutableList<Cell>>
        get() = grid.cells

    var gameState by mutableStateOf(GameState.PLAYING)
    var currentInstruction by mutableStateOf(getInstruction())
    var botMode by mutableStateOf(bot?.mode)
    var player1Streak by mutableStateOf(0)
    var player2Streak by mutableStateOf(0)

    private fun getInstruction(): String {
        return when (gameState) {
            GameState.PLAYING -> when (mode) {
                GameMode.SINGLE -> when (turn) {
                    Player.X -> SP_MOVE_TEXT
                    Player.O -> BOT_MOVE_TEXT
                }
                GameMode.DOUBLE -> "Player ${turn.name} turn"
            }
            GameState.OVER_DRAW -> DRAW_TEXT
            GameState.OVER_WINNER -> when (mode) {
                GameMode.SINGLE -> when (turn) {
                    Player.X -> SP_WIN_TEXT
                    Player.O -> BOT_WIN_TEXT
                }
                GameMode.DOUBLE -> "Player ${turn.name} wins!"
            }
        }
    }

    fun toggleBot() = bot?.toggleMode()

    fun updateGame(row: Int, col: Int) {
        grid.mark(row, col, turn)
        updateState()
        bot?.let {
            val botMove = it.move()
            grid.mark(botMove.first, botMove.second, turn)
            updateState()
        }
    }

    /**
     * Analyses game grid for a winner; otherwise, checks amount of empty cells to either
     * declare a draw or to continue game play.
     */
    private fun updateState() {
        gameState = when {
            grid.findWinner() -> GameState.OVER_WINNER
            grid.findEmptySpots().isEmpty() -> GameState.OVER_DRAW
            else -> GameState.PLAYING
        }
        when (gameState) {
            GameState.PLAYING -> {
                turn = if (turn == Player.X) Player.O else Player.X
                currentInstruction = getInstruction()
            }
            else -> {
                currentInstruction = getInstruction()
                if (gameState == GameState.OVER_WINNER) {
                    when (turn) {
                        Player.X -> player1Streak++
                        Player.O -> player2Streak++
                    }
                }
            }
        }
    }

    fun playAgain() {
        grid.clear()
        turn = Player.X
        gameState = GameState.PLAYING
        currentInstruction = getInstruction()
    }
}