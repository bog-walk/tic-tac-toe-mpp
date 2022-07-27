package dev.bogwalk.common.model

/**
 * Bot can only access an immutable encapsulated Grid for analysis.
 */
class Bot(
    private val grid: Grid
) {
    var mode = BotMode.EASY
        private set

    fun toggleMode() {
        mode = if (mode == BotMode.EASY) BotMode.HARD else BotMode.EASY
    }

    /**
     * Returns 0-indexed Cell coordinates (row, col) of Bot's chosen move.
     */
    fun move(): Pair<Int, Int> {
        val index = when (mode) {
            BotMode.EASY -> nextRandomSpot()
            BotMode.HARD -> hardBotNextMove()
        }
        return index / 3 to index % 3
    }

    /**
     * There is no concern of random() throwing a NoSuchElementException, as a full grid would
     * have been caught in the previous round.
     */
    private fun nextRandomSpot(): Int = grid.findEmptySpots().random()

    private fun hardBotNextMove(): Int {
        // assumes that Bot always moves second, as Player.O
        return if (grid.indexOf("O") == -1) {
            // grid would only be missing 'O' on first round
            firstMove()
        } else {
            scanGrid(::isOffensive) ?:
            scanGrid(::isDefensive) ?:
            nextRandomSpot()
        }
    }

    private fun firstMove(): Int {
        return if (4 in grid.findEmptySpots()) {
            4  // grid centre is prime spot when not first player
        } else {
            // pick any free corner cell
            (mutableSetOf(0, 2, 6, 8) - grid.indexOf("X")).random()
        }
    }

    /**
     * Scans all 3-character combinations (diagonals, rows, columns) for a free cell based on a
     * Strategy Pattern set by BotMode.
     */
    private fun scanGrid(strategy: (String) -> Boolean): Int? {
        val combos = grid.allRows().iterator()
        while (combos.hasNext()) {
            val (marks, indices) = combos.next()
            if (strategy(marks)) return indices[marks.indexOf(" ")]
        }
        return null
    }

    /**
     * Returns `true` if a match is found that allows Bot to win this round.
     */
    private fun isOffensive(cells: String): Boolean {
        return 'X' !in cells && cells.count { it == 'O' } == 2
    }

    /**
     * Returns `true` if a match is found that blocks Player.X from having a winning match next
     * round.
     */
    private fun isDefensive(cells: String): Boolean {
        return 'O' !in cells && cells.count { it == 'X' } == 2
    }
}