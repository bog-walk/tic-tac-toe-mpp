package dev.bogwalk.common.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.common.model.BotMode
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.components.*
import dev.bogwalk.common.ui.style.EASY_DELAY
import dev.bogwalk.common.ui.style.HARD_DELAY
import kotlinx.coroutines.delay

@Composable
fun GameView(mode: GameMode) {
    val t3AppState = remember { T3AppState(mode) }

    val history = produceState(
        initialValue = t3AppState.history.first(),
        t3AppState.history
    ) {
        if (t3AppState.history.size == 1) {
            value = t3AppState.history.first()
        } else {
            value = t3AppState.history[0]
            delay(when (t3AppState.botMode) {
                BotMode.EASY -> EASY_DELAY
                BotMode.HARD -> HARD_DELAY
                null -> 1L
            })
            value = t3AppState.history[1]
        }
     }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            t3AppState.botMode,
            history.value.instruction,
            t3AppState::toggleBot
        )
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
        ResetButton(history.value.gameState) {
            t3AppState.playAgain()
        }
    }
}