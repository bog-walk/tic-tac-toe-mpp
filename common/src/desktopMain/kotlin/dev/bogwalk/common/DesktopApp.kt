package dev.bogwalk.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.style.T3Theme
import dev.bogwalk.common.ui.style.windowHeight
import dev.bogwalk.common.ui.style.windowWidth
import dev.bogwalk.common.ui.views.GameView

@Preview
@Composable
fun SinglePlayerAppPreview() {
    T3Theme {
        Surface(
            modifier = Modifier.width(windowWidth).height(windowHeight)
        ) {
            GameView(GameMode.SINGLE)
        }
    }
}

@Preview
@Composable
fun DoublePlayerAppPreview() {
    T3Theme {
        Surface(
            modifier = Modifier.width(windowWidth).height(windowHeight)
        ) {
            GameView(GameMode.DOUBLE)
        }
    }
}