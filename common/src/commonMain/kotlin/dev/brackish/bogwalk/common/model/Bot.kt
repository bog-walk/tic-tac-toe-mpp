package dev.brackish.bogwalk.common.model

/**
 * Bot can observe Grid in its String representation but not act on it.
 */
class Bot {
    private var hardMode = false

    fun move(grid: String): Pair<Int, Int> {
        return if (hardMode) hardBotNextMove(grid) else easyBotNextMove(grid)
    }

    private fun easyBotNextMove(grid: String): Pair<Int, Int> {
        val index = nextRandomSpot(grid)
        return index / 3 to index % 3
    }

    private fun nextRandomSpot(grid: String): Int {
        return grid.mapIndexedNotNull { i, ch ->
            if (ch == ' ') i else null
        }.random()
    }

    /**
     * Assumes bot is always second player
     */
    private fun hardBotNextMove(grid: String): Pair<Int, Int> {
        // Grid would only be missing 'O' on first round
        val index: Int = if (!grid.contains('O')) {
            firstMove(grid)
        } else {
            scanGrid(grid, String::isMatch) ?:
            scanGrid(grid, String::isRisky) ?:
            nextRandomSpot(grid)
        }
        return index / 3 to index % 3
    }

    private fun firstMove(grid: String): Int {
        return if (grid[4] == ' ') {
            4 // grid centre is prime spot when not first player
        } else {
            // otherwise corner spot
            mutableSetOf(0, 2, 6, 8).apply {
                remove(grid.indexOf('X'))
            }.random()
        }
    }

    private fun String.isRisky(): Boolean = !contains('O') && count { it == 'X' } == 2

    private fun String.isMatch(): Boolean = !contains('X') && count { it == 'O' } == 2

    private fun scanGrid(grid: String, strategy: String.() -> Boolean): Int? {
        val diag1 = "${grid[0]}${grid[4]}${grid[8]}"
        if (diag1.strategy()) {
            return diag1.indexOf(' ') * 4
        }
        val diag2 = "${grid[2]}${grid[4]}${grid[6]}"
        if (diag2.strategy()) {
            return (diag2.indexOf(' ') + 1) * 2
        }
        repeat(3) { i ->
            val row = grid.substring((i * 3)..(i * 3 + 2))
            if (row.strategy()) return row.indexOf(' ') + i * 3
        }
        repeat(3) { i ->
            val col = "${grid[i]}${grid[i+3]}${grid[i+6]}"
            if (col.strategy()) return col.indexOf(' ') * 3 + i
        }
        return null
    }
}