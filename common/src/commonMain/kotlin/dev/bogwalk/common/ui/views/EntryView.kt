package dev.bogwalk.common.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.components.getPainter
import dev.bogwalk.common.ui.style.*

@Composable
fun EntryView(onOptionClick: (GameMode) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = APP_TITLE,
            modifier = Modifier.padding(componentPadding),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h3
        )
        OptionsButton(
            PLAYER_ICON to PLAYER_DESCRIPTION,
            PLAYER_ICON to PLAYER_DESCRIPTION,
            onOptionClick
        )
        OptionsButton(
            PLAYER_ICON to PLAYER_DESCRIPTION,
            BOT_ICON to BOT_DESCRIPTION,
            onOptionClick
        )
    }
}

@Composable
private fun OptionsButton(
    option1: Pair<String, String>,
    option2: Pair<String, String>,
    onOptionClick: (GameMode) -> Unit
) {
    Row(
        modifier = Modifier
            .testTag(GAME_MODE_TEST_TAG)
            .padding(componentPadding)
            .clickable(
                onClickLabel = OPTIONS_DESCRIPTION,
                role = Role.Button
            ) { onOptionClick(
                if (option2.second == BOT_DESCRIPTION) GameMode.SINGLE else GameMode.DOUBLE)
              },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = getPainter(option1.first),
            contentDescription = option1.second,
            modifier = Modifier.padding(componentPadding).requiredSize(iconSize)
        )
        Text(
            text = OPTIONS_TEXT,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h5
        )
        Icon(
            painter = getPainter(option2.first),
            contentDescription = option2.second,
            modifier = Modifier.padding(componentPadding).requiredSize(iconSize)
        )
    }
}