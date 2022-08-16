package dev.bogwalk.common.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import dev.bogwalk.common.ui.style.*
import dev.bogwalk.common.R

@Composable
actual fun getPainter(res: String): Painter {
    val imageName = res.substringAfterLast("/").substringBeforeLast(".")
    val drawableClass = R.drawable::class.java
    val field = drawableClass.getDeclaredField(imageName)
    val idValue = field.get(drawableClass) as Int
    return painterResource(idValue)
}

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
        modifier = Modifier.padding(componentPadding).requiredSize(dialogWidth, dialogHeight / 2),
        title = { Text(
            text = DIALOG_TEXT,
            color = MaterialTheme.colors.onPrimary
        ) },
        backgroundColor = MaterialTheme.colors.onBackground
    )
}