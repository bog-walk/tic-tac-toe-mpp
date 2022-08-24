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
import androidx.compose.ui.platform.testTag
import dev.bogwalk.common.ui.style.*

@Composable
fun BotToggle(
    botMode: BotMode?,
    onToggleRequest: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = when (botMode) {
                BotMode.HARD -> getPainter(HARD_BOT_ICON)
                else -> getPainter(EASY_BOT_ICON)
            },
            contentDescription = when (botMode) {
                BotMode.HARD -> HARD_BOT_DESCRIPTION
                else -> EASY_BOT_DESCRIPTION
            },
            modifier = Modifier.requiredSize(iconSize),
            tint = when (botMode) {
                null -> MaterialTheme.colors.background
                BotMode.EASY -> MaterialTheme.colors.onBackground
                BotMode.HARD -> MaterialTheme.colors.secondary
            }
        )
        Spacer(modifier = Modifier.width(togglePadding))
        Switch(
            checked = botMode == BotMode.HARD,
            onCheckedChange = { onToggleRequest() },
            modifier = Modifier.testTag(TOGGLE_TEST_TAG),
            enabled = botMode != null,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.secondary,
                uncheckedThumbColor = MaterialTheme.colors.onBackground
            )
        )
    }
}