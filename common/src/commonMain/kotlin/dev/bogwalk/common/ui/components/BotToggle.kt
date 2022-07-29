package dev.bogwalk.common.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.ui.Alignment
import dev.bogwalk.common.model.BotMode
import androidx.compose.ui.Modifier
import dev.bogwalk.common.ui.style.*

@Composable
fun BotToggle(botMode: BotMode) {
    Row(
        modifier = Modifier.padding(componentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = getPainter(BOT_ICON),
            contentDescription = BOT_DESCRIPTION,
            modifier = Modifier.requiredSize(iconSize),
            tint = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.width(togglePadding))
        Switch(
            checked = botMode == BotMode.HARD,
            onCheckedChange = {},
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.secondary,
                uncheckedThumbColor = MaterialTheme.colors.onBackground
            )
        )
        Spacer(modifier = Modifier.width(togglePadding))
        Icon(
            painter = getPainter(BOT_ICON),
            contentDescription = BOT_DESCRIPTION,
            modifier = Modifier.requiredSize(iconSize),
            tint = MaterialTheme.colors.secondary
        )
    }
}