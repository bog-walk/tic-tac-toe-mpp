package dev.brackish.bogwalk.common.model

class Grid(
    elements: String = ""
) {
    private val cells = Array(3) { r -> Array(3) { c ->
            if (elements.isEmpty()) Cell.EMPTY else Cell.valueOf(elements[r / 3 + c % 3].toString())
        }
    }

    override fun toString(): String {
        val output = StringBuilder()
        cells.forEach { row ->
            output.append(row.joinToString("") { it.mark })
        }
        return output.toString()
    }

    /**
     * Accepts already 0-indexed Cell coordinates.
     *
     * @throws IllegalArgumentException if coordinates are out of bounds or if the Cell is
     * already marked.
     */
    fun mark(row: Int, col: Int, player: Player) {
        require(row in 0..2 && col in 0..2) { "Invalid cell coordinates" }
        require(cells[row][col] == Cell.EMPTY) { "Cell already occupied" }
        cells[row][col] = Cell.valueOf(player.mark)
    }

    fun assess(): GameState {
        val hasEmptyCells = cells.flatten().any { it == Cell.EMPTY }
        val hasWinner = findWinner()
        return when {
            !hasEmptyCells -> GameState.OVER_DRAW
            hasWinner -> GameState.OVER_WINNER
            else -> GameState.PLAYING
        }
    }

    private fun findWinner(): Boolean {
        return cells.findWinningMatch() ||
                transpose().findWinningMatch() ||
                diagonals().findWinningMatch()
    }

    private fun Array<Array<Cell>>.findWinningMatch(): Boolean {
        return any { row ->
            row.all { it.mark == "X" } || row.all { it.mark == "O" }
        }
    }

    private fun transpose(): Array<Array<Cell>> {
        val transposed = Array(3) { Array(3) { Cell.EMPTY } }
        repeat(3) { row ->
            repeat(3) { col ->
                transposed[col][row] = cells[row][col]
            }
        }
        return transposed
    }

    private fun diagonals(): Array<Array<Cell>> {
        val principal = arrayOf(cells[0][0], cells[1][1], cells[2][2])
        val secondary = arrayOf(cells[0][2], cells[1][1], cells[2][0])
        return arrayOf(principal, secondary)
    }
}