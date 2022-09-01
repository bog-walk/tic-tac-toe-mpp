package dev.bogwalk.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.style.*
import dev.bogwalk.common.ui.views.EntryView
import dev.bogwalk.common.ui.views.GameView

@Preview
@Composable
private fun SinglePlayerGamePreview() {
    T3Theme {
        Surface(
            modifier = Modifier.width(windowWidth).height(windowHeight)
        ) {
            GameView(GameMode.SINGLE) {}
        }
    }
}

@Preview
@Composable
private fun DoublePlayerGamePreview() {
    T3Theme {
        Surface(
            modifier = Modifier.width(windowWidth).height(windowHeight)
        ) {
            GameView(GameMode.DOUBLE) {}
        }
    }
}

@Preview
@Composable
private fun LandscapeGamePreview() {
    T3Theme {
        Surface(
            modifier = Modifier.width(1080.dp).height(350.dp)
        ) {
            GameView(GameMode.DOUBLE, true) {}
        }
    }
}

@Preview
@Composable
private fun EntryViewPreview() {
    T3Theme {
        Surface(
            modifier = Modifier.width(windowWidth).height(windowHeight)
        ) {
            EntryView {}
        }
    }
}