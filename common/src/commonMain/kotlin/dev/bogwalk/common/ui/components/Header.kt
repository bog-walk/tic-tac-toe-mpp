package dev.bogwalk.common.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import dev.bogwalk.common.model.BotMode
import dev.bogwalk.common.ui.style.HOME_DESCRIPTION
import dev.bogwalk.common.ui.style.HOME_ICON
import dev.bogwalk.common.ui.style.componentPadding
import dev.bogwalk.common.ui.style.iconSize

@Composable
fun Header(
    botMode: BotMode,
    instructions: String
) {
    Row(
        modifier = Modifier.padding(componentPadding).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier.padding(componentPadding).requiredSize(iconSize)
        ) {
            Icon(
                painter = getPainter(HOME_ICON),
                contentDescription = HOME_DESCRIPTION,
                tint = MaterialTheme.colors.onBackground
            )
        }
        Text(
            text = instructions,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
        BotToggle(botMode)
    }
}