import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import dev.bogwalk.common.ui.views.GameView
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.style.*

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize(windowWidth, windowHeight)),
        title = WINDOW_TITLE,
        icon = painterResource(WINDOW_ICON),
        resizable = false
    ) {
        T3Theme {
            GameView(GameMode.SINGLE)
        }
    }
}