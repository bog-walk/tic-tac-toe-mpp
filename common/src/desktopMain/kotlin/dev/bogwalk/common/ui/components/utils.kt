package dev.bogwalk.common.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.rememberDialogState
import dev.bogwalk.common.ui.style.*

@Composable
actual fun getPainter(res: String): Painter = androidx.compose.ui.res.painterResource(res)

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