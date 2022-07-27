package dev.bogwalk.common.model

enum class Cell(val mark: Char) {
    X('X'),
    O('O'),
    EMPTY(' ')
}

enum class Player {
    X,
    O
}

enum class BotMode {
    EASY,
    HARD
}

enum class GameState {
    PLAYING,
    OVER_WINNER,
    OVER_DRAW
}