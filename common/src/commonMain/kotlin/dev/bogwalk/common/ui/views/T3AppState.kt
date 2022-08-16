package dev.bogwalk.common.ui.views

import androidx.compose.runtime.*
import dev.bogwalk.common.model.*
import dev.bogwalk.common.ui.style.*

internal class T3AppState(
    private val mode: GameMode
) {
    private val grid = Grid()
    private val bot: Bot? = when (mode) {
        GameMode.SINGLE -> Bot(grid)
        GameMode.DOUBLE -> null
    }

    private var turn = Player.X
    private var gameState = GameState.PLAYING
    private var player1Streak = 0
    private var player2Streak = 0

    val botMode: BotMode?
        get() = bot?.mode

    var history by mutableStateOf(listOf(TurnState(instruction = getInstruction(), board = grid.cells)))
        private set

    private fun getInstruction(): String {
        return when (gameState) {
            GameState.PLAYING -> when (mode) {
                GameMode.SINGLE -> SP_MOVE_TEXT
                GameMode.DOUBLE -> "Player ${turn.name} turn"
            }
            GameState.BOT_TURN -> BOT_MOVE_TEXT
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
        bot?.let {
            it.mode = it.mode.toggle()
        }
    }

    /**
     * If Player.O is a Bot instance, 2 TurnState instances are instantiated, to ensure that each
     * player's move is observed and composed (instead of just the final state upon termination
     * of this function).
     */
    fun updateGame(row: Int, col: Int) {
        grid.mark(row, col, turn)
        val currentChanges = mutableListOf(updateTurnState())
        if (gameState == GameState.BOT_TURN) {
            bot?.let {
                val botM = it.move()
                grid.mark(botM.first, botM.second, turn)
            }
            currentChanges.add(updateTurnState())
        }
        history = currentChanges
    }

    /**
     * Analyses game Grid for a winner. If none is found, checks amount of empty cells to either
     * declare a draw or to continue game play. Then returns a new TurnState that stores all
     * relevant state for the current turn.
     */
    private fun updateTurnState(): TurnState {
        gameState = when {
            grid.findWinner() -> {
                when (turn) {
                    Player.X -> player1Streak++
                    Player.O -> player2Streak++
                }
                GameState.OVER_WINNER
            }
            grid.findEmptyCells().isEmpty() -> GameState.OVER_DRAW
            else -> {
                turn = turn.next()
                if (bot != null && turn == Player.O) {
                    GameState.BOT_TURN
                } else {
                    GameState.PLAYING
                }
            }
        }
        return TurnState(
            gameState, getInstruction(), grid.cells, player1Streak, player2Streak
        )
    }

    fun playAgain() {
        grid.clear()
        turn = Player.X
        gameState = GameState.PLAYING
        history = listOf(
            TurnState(
            instruction = getInstruction(), board = grid.cells,
            player1Streak = player1Streak, player2Streak = player2Streak
        ))
    }
}