package dev.bogwalk.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.style.*
import dev.bogwalk.common.ui.views.EntryView
import dev.bogwalk.common.ui.views.GameView

@Preview
@Composable
fun SinglePlayerGamePreview() {
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
fun DoublePlayerGamePreview() {
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
private fun EntryViewPreview() {
    T3Theme {
        Surface(
            modifier = Modifier.width(windowWidth).height(windowHeight)
        ) {
            EntryView {}
        }
    }
}