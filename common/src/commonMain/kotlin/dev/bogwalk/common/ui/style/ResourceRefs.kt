package dev.bogwalk.common.ui.style

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Common dimensions & values
const val APP_TITLE = "Tic Tac Toe"
const val APP_ICON = "t3_icon.svg"
val componentPadding = 15.dp
val smallPadding = 8.dp
val zeroDP = 0.dp
val iconSize = 30.dp

// Desktop App Window
val windowWidth = 530.dp
val windowHeight = 650.dp

// EntryView
const val OPTIONS_TEXT = "vs"
const val OPTIONS_DESCRIPTION = "Choose game type"
const val GAME_MODE_TEST_TAG = "mode button"
const val PLAYER_ICON = "t3_face.svg"
const val PLAYER_DESCRIPTION = "Player icon"
const val BOT_ICON = "t3_bot.svg"
const val BOT_DESCRIPTION = "Bot icon"

// BotToggle
val togglePadding = 5.dp
const val TOGGLE_TEST_TAG = "bot switch"
const val SHORT_DELAY = 1000L
const val LONG_DELAY = 1800L

// Home
const val HOME_ICON = "t3_home.svg"
const val HOME_DESCRIPTION = "Home icon"

// ExitDialog
const val DIALOG_TEXT = "Scores will be lost"
const val DIALOG_BUTTON_TEXT = "CONFIRM"
val dialogWidth = 300.dp
val dialogHeight = 250.dp
const val WARNING_ICON = "t3_warning.svg"
val dialogButtonBorder = 2.dp

// HeaderText
const val BOT_MOVE_TEXT = "Bot is deciding..."
const val SP_MOVE_TEXT = "Your turn"
const val BOT_WIN_TEXT = "The bot wins!"
const val SP_WIN_TEXT = "You win!"
const val DRAW_TEXT = "It's a draw"

// Scores
const val PX_TEXT = "PLAYER X:"
const val PO_TEXT = "PLAYER O:"
const val BOT_TEXT = "BOT:"
const val SCORE_TEST_TAG = "player score"
const val SCORE_ANIM_DUR = 400

// T3Cell
val cellSize = 100.dp
val cellElevation = 6.dp
val cellFontSize = 57.sp
const val CELL_TEST_TAG = "grid cell"

// ResetButton
const val RESET_BUTTON_TEXT = "PLAY AGAIN"