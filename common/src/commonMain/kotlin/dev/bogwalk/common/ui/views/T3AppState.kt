package dev.bogwalk.common.ui.views

import androidx.compose.runtime.*
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
    val botMode: BotMode?
        get() = bot?.mode
    private var turn = Player.X
    private var gameState = GameState.PLAYING
    private var player1Streak = 0
    private var player2Streak = 0

    var history by mutableStateOf(listOf(
        TurnState(instruction = getInstruction(), board = grid.toString())
    ))
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
        bot?.let {
            it.mode = it.mode.toggle()
        }
    }

    fun updateGame(row: Int, col: Int) {
        grid.mark(row, col, turn)
        val currentChanges = mutableListOf(updateTurnState())
        if (gameState == GameState.PLAYING && bot != null) {
            val botMove = bot.move()
            grid.mark(botMove.first, botMove.second, turn)
            currentChanges.add(updateTurnState())
        }
        history = currentChanges
    }

    /**
     * Analyses game grid for a winner; otherwise, checks amount of empty cells to either
     * declare a draw or to continue game play.
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
            grid.findEmptySpots().isEmpty() -> GameState.OVER_DRAW
            else -> {
                turn = turn.next()
                GameState.PLAYING
            }
        }
        return TurnState(
            gameState, getInstruction(), grid.toString(), player1Streak, player2Streak
        )
    }

    fun playAgain() {
        grid.clear()
        turn = Player.X
        gameState = GameState.PLAYING
        history = listOf(
            TurnState(
            instruction = getInstruction(), board = grid.toString(),
            player1Streak = player1Streak, player2Streak = player2Streak
        ))
    }
}