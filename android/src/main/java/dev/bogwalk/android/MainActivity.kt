package dev.bogwalk.android

import dev.bogwalk.common.ui.views.GameView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.bogwalk.common.ui.components.ExitDialog
import dev.bogwalk.common.ui.style.T3Theme
import dev.bogwalk.common.ui.views.EntryView
import dev.bogwalk.common.ui.views.Screen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var screenState by remember { mutableStateOf<Screen>(Screen.Entry) }
            var isAskingToGoHome by remember { mutableStateOf(false) }

            T3Theme {
                when (val screen = screenState) {
                    is Screen.Entry -> EntryView { screenState = Screen.Game(mode = it) }
                    is Screen.Game -> {
                        if (isAskingToGoHome) {
                            ExitDialog(
                                onCloseRequest = { isAskingToGoHome = false },
                                onConfirm = {
                                    screenState = Screen.Entry
                                    isAskingToGoHome = false
                                }
                            )
                        }
                        GameView(screen.mode) { isAskingToGoHome = true }
                    }
                }
            }
        }
    }
}