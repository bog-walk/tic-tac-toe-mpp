import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.bogwalk.common.TicTacToeApp
import dev.bogwalk.common.model.GameMode

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
            TicTacToeApp(GameMode.DOUBLE)
        }
    }
}