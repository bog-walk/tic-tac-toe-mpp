package dev.bogwalk.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.components.Header
import dev.bogwalk.common.ui.components.ResetButton
import dev.bogwalk.common.ui.components.Scores
import dev.bogwalk.common.ui.components.T3Grid
import dev.bogwalk.common.ui.util.T3AppState

@Composable
fun TicTacToeApp(mode: GameMode) {
    val t3AppState = remember { T3AppState(mode) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            t3AppState.botMode,
            t3AppState.currentInstruction,
            t3AppState::toggleBot
        )
        Scores(
            t3AppState.player1Streak,
            t3AppState.player2Streak,
            t3AppState.botMode != null
        )
        T3Grid(
            t3AppState.gameState,
            t3AppState.board
        ) { (r, c) ->
            t3AppState.updatePlayerMove(r, c)
        }
        ResetButton(t3AppState.gameState) {
            t3AppState.playAgain()
        }
    }
}