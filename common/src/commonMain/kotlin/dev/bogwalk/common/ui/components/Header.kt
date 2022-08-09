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
import dev.bogwalk.common.ui.style.*

@Composable
fun Header(
    botMode: BotMode?,
    instruction: String,
    onToggleRequest: () -> Unit
) {
    Row(
        modifier = Modifier.padding(componentPadding).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {},
        ) {
            Icon(
                painter = getPainter(HOME_ICON),
                contentDescription = HOME_DESCRIPTION,
                modifier = Modifier.requiredSize(iconSize),
                tint = MaterialTheme.colors.onBackground
            )
        }
        Text(
            text = instruction,
            modifier = Modifier.padding(componentPadding).requiredWidth(gridWidth),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
        BotToggle(botMode, onToggleRequest)
    }
}