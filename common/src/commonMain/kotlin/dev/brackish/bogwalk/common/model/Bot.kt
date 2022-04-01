package dev.brackish.bogwalk.common.model

/**
 * Bot can observe Grid in its String representation but not act on it.
 */
class Bot {
    var mode = BotMode.EASY

    fun move(grid: String): Pair<Int, Int> {
        return when (mode) {
            BotMode.EASY -> easyBotNextMove(grid)
            BotMode.HARD -> hardBotNextMove(grid)
        }
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
     * Assumes bot is always Player2 ('O').
     */
    private fun hardBotNextMove(grid: String): Pair<Int, Int> {
        // Grid would only be missing 'O' on first round
        val index: Int = if (!grid.contains('O')) {
            firstMove(grid)
        } else {
            scanGrid(grid, ::isOffensive) ?:
            scanGrid(grid, ::isDefensive) ?:
            nextRandomSpot(grid)
        }
        return index / 3 to index % 3
    }

    private fun firstMove(grid: String): Int {
        return if (grid[4] == ' ') {
            4 // grid centre is prime spot when not first player
        } else {
            // otherwise, pick a corner spot
            mutableSetOf(0, 2, 6, 8).apply {
                remove(grid.indexOf('X'))
            }.random()
        }
    }

    private fun scanGrid(grid: String, strategy: (String) -> Boolean): Int? {
        val diagonal1 = "${grid[0]}${grid[4]}${grid[8]}"
        if (strategy(diagonal1)) {
            return diagonal1.indexOf(' ') * 4
        }
        val diagonal2 = "${grid[2]}${grid[4]}${grid[6]}"
        if (strategy(diagonal2)) {
            return (diagonal2.indexOf(' ') + 1) * 2
        }
        repeat(3) { i ->
            val row = grid.substring((i * 3)..(i * 3 + 2))
            if (strategy(row)) {
                return row.indexOf(' ') + i * 3
            }
        }
        repeat(3) { i ->
            val col = "${grid[i]}${grid[i+3]}${grid[i+6]}"
            if (strategy(col)) {
                return col.indexOf(' ') * 3 + i
            }
        }
        return null
    }

    private fun isOffensive(cells: String): Boolean {
        return 'X' !in cells && cells.count { it == 'O' } == 2
    }

    private fun isDefensive(cells: String): Boolean {
        return '0' !in cells && cells.count { it == 'X' } == 2
    }
}