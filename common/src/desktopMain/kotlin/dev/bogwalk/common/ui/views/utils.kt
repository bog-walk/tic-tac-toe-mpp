package dev.bogwalk.common.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.rememberDialogState
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.components.getPainter
import dev.bogwalk.common.ui.style.*

actual sealed class Screen {
    object Entry : Screen()
    data class Game(val mode: GameMode) : Screen()
}

// only exists to allow state retention on Android configuration change
actual interface T3Parcelable

// this should normally not exist as expect should be annotated with
// @OptIn(ExperimentalMultiplatform::class)
// @OptionalExpectation
// but this causes error: Declaration annotated with '@OptionalExpectation' can only be used in
// common module sources, even when it is being used in the common module...
actual annotation class T3Parcelize

@Composable
actual fun ExitDialog(
    onCloseRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(
        onCloseRequest = onCloseRequest,
        state = rememberDialogState(size = DpSize(dialogWidth, dialogHeight)),
        title = "",
        icon = getPainter(WARNING_ICON),
        resizable = false
    ) {
        Column(
            modifier = Modifier.padding(componentPadding).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = DIALOG_TEXT,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(componentPadding))
            OutlinedButton(
                onClick = onConfirm,
                border = BorderStroke(dialogButtonBorder, MaterialTheme.colors.secondaryVariant),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = MaterialTheme.colors.onSurface,
                    contentColor = MaterialTheme.colors.secondaryVariant
                )
            ) {
                Text(
                    text = DIALOG_BUTTON_TEXT,
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}