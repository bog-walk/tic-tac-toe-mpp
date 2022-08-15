import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import dev.bogwalk.common.ui.components.ExitDialog
import dev.bogwalk.common.ui.style.*
import dev.bogwalk.common.ui.views.EntryView
import dev.bogwalk.common.ui.views.GameView
import dev.bogwalk.common.ui.views.Screen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize(windowWidth, windowHeight)),
        title = APP_TITLE,
        icon = painterResource(APP_ICON),
        resizable = false
    ) {
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