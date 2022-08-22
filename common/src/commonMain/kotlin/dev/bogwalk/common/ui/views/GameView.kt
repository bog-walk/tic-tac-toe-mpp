package dev.bogwalk.common.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.common.model.BotMode
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.components.*
import dev.bogwalk.common.ui.style.SHORT_DELAY
import dev.bogwalk.common.ui.style.LONG_DELAY
import kotlinx.coroutines.delay

@Composable
fun GameView(
    mode: GameMode,
    onHomeRequest: () -> Unit
) {
    val t3AppState = remember { T3AppState(mode) }

    // produces successive observable state changes with a delayed response if second player is Bot
    val history = produceState(
        initialValue = t3AppState.history.first(),
        t3AppState.history
    ) {
        if (t3AppState.history.size == 1) {
            value = t3AppState.history.first()
        } else {
            // when GameMode.SINGLE, t3AppState will hold 2 TurnState instances for every turn
            value = t3AppState.history[0]
            delay(when (t3AppState.botMode) {
                BotMode.EASY -> SHORT_DELAY
                BotMode.HARD -> LONG_DELAY
                null -> 1L
            })
            value = t3AppState.history[1]
        }
     }

    Scaffold(
        topBar = { HeaderBar(onHomeRequest, t3AppState.botMode, t3AppState::toggleBot) },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText(history.value.instruction)
            Scores(
                history.value.player1Streak,
                history.value.player2Streak,
                t3AppState.botMode != null
            )
            T3Grid(
                history.value.gameState,
                history.value.board
            ) { (r, c) ->
                t3AppState.updateGame(r, c)
            }
            ResetButton(
                history.value.gameState,
                t3AppState::playAgain
            )
        }
    }
}