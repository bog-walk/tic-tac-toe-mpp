package dev.bogwalk.common.ui.views

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import dev.bogwalk.common.ui.style.DIALOG_BUTTON_TEXT
import dev.bogwalk.common.ui.style.DIALOG_TEXT
import dev.bogwalk.common.ui.style.dialogHeight

actual sealed class Screen(val route: String) {
    object Entry : Screen("entry")
    object Game : Screen("game/{mode}")
}

actual typealias T3Parcelable = Parcelable

actual typealias T3Parcelize = Parcelize

@Composable
actual fun ExitDialog(
    onCloseRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCloseRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colors.secondaryVariant
                )
            ) {
                Text(
                    text = DIALOG_BUTTON_TEXT,
                    style = MaterialTheme.typography.button
                )
            }
        },
        modifier = Modifier.requiredHeight(dialogHeight / 2),
        title = { Text(
            text = DIALOG_TEXT,
            color = MaterialTheme.colors.onPrimary
        ) },
        backgroundColor = MaterialTheme.colors.onBackground,
        properties = DialogProperties(
            dismissOnBackPress = false
        )
    )
}