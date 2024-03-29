package dev.bogwalk.android

import android.content.res.Configuration
import dev.bogwalk.common.ui.views.GameView
import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.style.T3Theme
import dev.bogwalk.common.ui.views.EntryView
import dev.bogwalk.common.ui.views.ExitDialog
import dev.bogwalk.common.ui.views.Screen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // must be declared separately as current is a composable call that cannot be used
            // inside remember {}
            val configuration = LocalConfiguration.current
            val orientation by remember { mutableStateOf(configuration.orientation) }
            var isAskingToGoHome by remember { mutableStateOf(false) }

            val navController = rememberNavController()

            T3Theme {
                if (isAskingToGoHome) {
                    ExitDialog(
                        onCloseRequest = { isAskingToGoHome = false },
                        onConfirm = {
                            isAskingToGoHome = false
                            navController.navigate("entry") {
                                // otherwise back button from EntryView returns to GameView instead
                                // of exiting the app entirely
                                popUpTo(Screen.Game.route) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    )
                }
                NavHost(navController = navController, startDestination = Screen.Entry.route) {
                    composable(Screen.Entry.route) {
                        EntryView {
                            navController.navigate("game/$it") {
                                popUpTo(Screen.Entry.route)
                                launchSingleTop = true
                            }
                        } 
                    }
                    composable(
                        Screen.Game.route,
                        arguments = listOf(navArgument("mode") {
                            type = NavType.EnumType(GameMode::class.java)
                        })
                    ) { backStackEntry ->
                        BackHandler { isAskingToGoHome = true }
                        GameView(
                            backStackEntry.arguments?.getSerializable("mode") as GameMode,
                            orientation == Configuration.ORIENTATION_LANDSCAPE
                        ) {
                            isAskingToGoHome = true
                        }
                    }
                }
            }
        }
    }
}