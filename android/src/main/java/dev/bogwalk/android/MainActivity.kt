package dev.bogwalk.android

import dev.bogwalk.common.ui.views.GameView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.style.T3Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            T3Theme {
                GameView(GameMode.DOUBLE) {}
            }
        }
    }
}