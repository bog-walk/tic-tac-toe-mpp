package dev.bogwalk.common.ui.util

import androidx.compose.runtime.*
import dev.bogwalk.common.model.*
import dev.bogwalk.common.ui.style.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

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
        private set
    var currentInstruction by mutableStateOf(getInstruction())
        private set
    var botMode by mutableStateOf(bot?.mode)
        private set
    var player1Streak by mutableStateOf(0)
        private set
    var player2Streak by mutableStateOf(0)
        private set

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

    fun toggleBot() {
        botMode = bot?.toggleMode()
    }

    fun updatePlayerMove(row: Int, col: Int) {
        grid.mark(row, col, turn)
        updateState()
    }

    fun updateBotMove() = runBlocking {
        if (gameState == GameState.PLAYING && bot != null) {
            val botMove = bot.move()
            delay(when (botMode) {
                BotMode.EASY -> 1000
                BotMode.HARD -> 2000
                else -> 1
            })
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
                turn = turn.next()
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