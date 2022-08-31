package dev.bogwalk.common.ui.views

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver
import dev.bogwalk.common.model.*
import dev.bogwalk.common.ui.style.*

internal class T3AppState(
    val mode: GameMode,
    val grid: Grid = Grid()
) {
    var bot: Bot? = when (mode) {
        GameMode.SINGLE -> Bot(grid)
        GameMode.DOUBLE -> null
    }
    val botMode: BotMode?
        get() = bot?.mode

    var history by mutableStateOf(listOf(
        TurnState(
            instruction = if (mode == GameMode.SINGLE) SP_MOVE_TEXT else "Player X turn",
            board = grid.cells)
    ))

    private var gameState: GameState = history[0].gameState
    private var turn: Player = history[0].turn
    private var player1Streak: Int = history[0].player1Streak
    private var player2Streak: Int = history[0].player2Streak

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
            gameState, turn, player1Streak, player2Streak, getInstruction(), grid.cells
        )
    }

    fun playAgain() {
        grid.clear()
        gameState = GameState.PLAYING
        turn = Player.X
        history = listOf(
            TurnState(
                player1Streak = player1Streak, player2Streak = player2Streak,
                instruction = getInstruction(), board = grid.cells
            ))
    }
}

// Android module currently does not use ViewModel so needs to be bundled to be used
// with rememberSaveable() in GameView (to retain state during configuration changes)
internal val T3AppStateSaver = run {
    listSaver(
        save = { data: T3AppState ->
            listOf(data.mode, data.grid.toString(), data.bot?.mode, data.history)
        },
        restore = { restorationList: List<Any?> ->
            T3AppState(
                mode = restorationList[0] as GameMode,
                grid = Grid(restorationList[1] as String)
            ).apply {
                this.bot?.mode = restorationList[2] as BotMode
                @Suppress("UNCHECKED_CAST")
                this.history = restorationList[3] as List<TurnState>
            }
        }
    )
}