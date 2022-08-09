package dev.bogwalk.common.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import dev.bogwalk.common.model.GameState
import dev.bogwalk.common.ui.style.RESET_BUTTON_TEXT
import dev.bogwalk.common.ui.style.componentPadding

@Composable
fun ResetButton(
    gameState: GameState,
    onReplayRequest: () -> Unit
) {
    Row(
        modifier = Modifier.padding(componentPadding).fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { onReplayRequest() },
            enabled = gameState != GameState.PLAYING,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondaryVariant,
                contentColor = MaterialTheme.colors.onSurface
            )
        ) {
            Text(
                text = RESET_BUTTON_TEXT,
                style = MaterialTheme.typography.button
            )
        }
    }
}