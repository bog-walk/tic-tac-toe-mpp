package dev.bogwalk.common.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.common.model.BotMode
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.components.*
import dev.bogwalk.common.ui.style.*
import kotlinx.coroutines.delay

@Composable
fun GameView(
    mode: GameMode,
    isInLandscape: Boolean = false,
    onHomeRequest: () -> Unit
) {
    val t3AppState = rememberSaveable(stateSaver = T3AppStateSaver) {
        mutableStateOf(T3AppState(mode))
    }

    // produces successive observable state changes with a delayed response if second player is Bot
    val history = produceState(
        initialValue = t3AppState.value.history.first(),
        t3AppState.value.history
    ) {
        if (t3AppState.value.history.size == 1) {
            value = t3AppState.value.history.first()
        } else {
            // when GameMode.SINGLE, t3AppState will hold 2 TurnState instances for every turn
            value = t3AppState.value.history[0]
            delay(when (t3AppState.value.botMode) {
                BotMode.EASY -> SHORT_DELAY
                BotMode.HARD -> LONG_DELAY
                null -> 1L
            })
            value = t3AppState.value.history[1]
        }
     }

    Scaffold(
        topBar = { HeaderBar(onHomeRequest, t3AppState.value.botMode, t3AppState.value::toggleBot) },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        if (isInLandscape) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                T3Grid(
                    history.value.gameState,
                    history.value.board,
                    Modifier.weight(LANDSCAPE_WEIGHT_L)
                ) { (r, c) ->
                    t3AppState.value.updateGame(r, c)
                }
                Column(
                    modifier = Modifier.weight(LANDSCAPE_WEIGHT_S).padding(end = componentPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HeaderText(history.value.instruction)
                    Scores(
                        history.value.player1Streak,
                        history.value.player2Streak,
                        t3AppState.value.botMode != null
                    )
                    ResetButton(
                        history.value.gameState,
                        t3AppState.value::playAgain
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderText(history.value.instruction)
                Scores(
                    history.value.player1Streak,
                    history.value.player2Streak,
                    t3AppState.value.botMode != null
                )
                T3Grid(
                    history.value.gameState,
                    history.value.board
                ) { (r, c) ->
                    t3AppState.value.updateGame(r, c)
                }
                ResetButton(
                    history.value.gameState,
                    t3AppState.value::playAgain
                )
            }
        }
    }
}