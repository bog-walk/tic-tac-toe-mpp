package dev.bogwalk.common.model

enum class Cell(val mark: Char) {
    X('X'),
    O('O'),
    EMPTY(' ')
}

enum class Player {
    X,
    O;

    fun next(): Player {
        return when (this) {
            X -> O
            O -> X
        }
    }
}

enum class BotMode {
    EASY,
    HARD;

    fun toggle(): BotMode {
        return when (this) {
            EASY -> HARD
            HARD -> EASY
        }
    }
}

// Should OVER_WINNER be separated based on winner?
enum class GameState {
    PLAYING,
    OVER_WINNER,
    OVER_DRAW
}

enum class GameMode {
    SINGLE,
    DOUBLE
}