package dev.bogwalk.common.ui.style

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Desktop App Window
const val WINDOW_TITLE = "Tic Tac Toe"
const val WINDOW_ICON = "t3_icon.svg"
val windowWidth = 530.dp
val windowHeight = 650.dp

// T3Cell
val cellSize = 100.dp
val cellPadding = 8.dp
val cellElevation = 6.dp
val cellFontSize = 57.sp
const val CELL_TEST_TAG = "grid cell"

// Common dimensions & values
val componentPadding = 15.dp
val iconSize = 30.dp
const val DIALOG_TEXT = "Scores will be lost"
const val DIALOG_CONFIRM_TEXT = "CONFIRM"
val dialogWidth = 300.dp
val dialogHeight = 250.dp
const val WARNING_ICON = "t3_warning.svg"
const val WARNING_DESCRIPTION = "Warning icon"
val dialogButtonBorder = 2.dp

// Bot
const val BOT_ICON = "t3_bot.svg"
const val BOT_DESCRIPTION = "Bot icon"
val togglePadding = 5.dp
const val TOGGLE_TEST_TAG = "bot switch"

// Home
const val HOME_ICON = "t3_home.svg"
const val HOME_DESCRIPTION = "Home icon"
const val GAME_MODE_TEST_TAG = "mode button"
const val OPTIONS_TEXT = "vs"
const val OPTIONS_DESCRIPTION = "Choose game type"

// Player
const val PLAYER_ICON = "t3_face.svg"
const val PLAYER_DESCRIPTION = "Player icon"

// Header
const val BOT_MOVE_TEXT = "Bot is deciding..."
const val SP_MOVE_TEXT = "Your turn"
const val BOT_WIN_TEXT = "The bot wins!"
const val SP_WIN_TEXT = "You win!"
const val DRAW_TEXT = "No winner"
val gridWidth = 320.dp
const val EASY_DELAY = 1000L
const val HARD_DELAY = 1800L

// Scores
const val PX_TEXT = "PLAYER X:"
const val PO_TEXT = "PLAYER O:"
const val BOT_TEXT = "BOT:"
const val SCORE_TEST_TAG = "player score"
const val SCORE_ANIM_DUR = 400

// ResetButton
const val RESET_BUTTON_TEXT = "PLAY AGAIN"