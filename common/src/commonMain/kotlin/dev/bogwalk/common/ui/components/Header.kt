package dev.bogwalk.common.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.bogwalk.common.model.BotMode
import dev.bogwalk.common.ui.style.*

@Composable
fun Header(
    botMode: BotMode?,
    instruction: String,
    onToggleRequest: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentPadding = PaddingValues(vertical = 5.dp, horizontal = componentPadding)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.alpha(ContentAlpha.high)
            ) {
                Icon(
                    painter = getPainter(HOME_ICON),
                    contentDescription = HOME_DESCRIPTION,
                    modifier = Modifier.requiredSize(iconSize),
                    tint = MaterialTheme.colors.onBackground
                )
            }
            BotToggle(botMode, onToggleRequest)
        }
    }
    Text(
        text = instruction,
        modifier = Modifier.padding(bottom = componentPadding).fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h5
    )
}