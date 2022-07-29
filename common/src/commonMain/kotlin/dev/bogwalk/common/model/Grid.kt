package dev.bogwalk.common.model

/**
 * Class representing the standard 3x3 game board with encapsulated functionality that progresses
 * game play and allows safe bot analysis.
 */
class Grid(
    elements: String = ""
) {
    private val _cells = if (elements.isEmpty()) {
        List(3) { MutableList(3) { Cell.EMPTY } }
    } else {
        List(3) { row -> MutableList(3) { col ->
            when (val ch = elements[row * 3 + col % 3].toString()) {
                " " -> Cell.EMPTY
                else -> Cell.valueOf(ch)
            }
        } }
    }
    val cells: List<List<Cell>>
        get() = _cells

    override fun toString(): String {
        return _cells.flatten().stringify()
    }

    private fun List<Cell>.stringify(): String {
        return this.joinToString("") { it.mark.toString() }
    }

    /**
     * Alters the game grid with the player's chosen move, if valid. Accepts [row] and [col] as
     * already 0-indexed Cell coordinates.
     *
     * @throws IllegalArgumentException if coordinates are out of bounds or if the Cell is
     * already marked.
     */
    fun mark(row: Int, col: Int, player: Player) {
        require(row in 0..2 && col in 0..2) { "Invalid cell coordinates" }
        require(_cells[row][col] == Cell.EMPTY) { "Cell already occupied" }
        _cells[row][col] = Cell.valueOf(player.name)
    }

    /**
     * Analyses game grid for a winner; otherwise, checks amount of empty cells to either
     * declare a draw or to continue game play.
     */
    fun assessState(): GameState {
        return when {
            findWinner() -> GameState.OVER_WINNER
            _cells.flatten().none { it == Cell.EMPTY } -> GameState.OVER_DRAW
            else -> GameState.PLAYING
        }
    }

    /**
     * If a winner is found, it must be current player; otherwise, this function would have
     * returned `true` on the previous assess. There is, therefore, no need to include an argument
     * for current player.
     */
    private fun findWinner(): Boolean {
        val combos = allRows().iterator()
        while (combos.hasNext()) {
            if (combos.next().first in listOf("XXX", "OOO")) return true
        }
        return false
    }

    /**
     * Yields 3-character String representations of all row combinations, i.e. 3 rows,
     * 3 columns, and 2 diagonals, then suspends until the next Pair is requested by the iterator.
     *
     * @return Pairs of (String representing cell marks, List of corresponding flattened grid
     * indices).
     */
    fun allRows() = sequence {
        for (row in diagonals() zip listOf(listOf(0, 4, 8), listOf(2, 4, 6))) {
            yield(row)
        }
        for ((i, row) in _cells.withIndex()) {
            yield(row.stringify() to listOf(i * 3, i * 3 + 1, i * 3 + 2))
        }
        for ((i, row) in transpose().withIndex()) {
            yield(row.stringify() to listOf(i, i + 3, i + 6))
        }
    }

    private fun diagonals(): List<String> {
        val principal = List(3) { _cells[it][it] }.stringify()
        val secondary = List(3) { _cells[it][2-it] }.stringify()
        return listOf(principal, secondary)
    }

    private fun transpose(): List<List<Cell>> {
        return List(3) { row -> List(3) { col -> _cells[col][row] } }
    }

    /**
     * Returns the index (0 to 8 for flattened grid) of the first occurrence of the specified
     * mark, or -1 if the mark is not present in the grid.
     */
    fun indexOf(mark: String): Int {
        return _cells.flatten().indexOf(Cell.valueOf(mark))
    }

    /**
     * Returns a List of the indices (0 to 8 for flattened grid) of all cells that are empty.
     */
    fun findEmptySpots(): List<Int> {
        return _cells.flatten().mapIndexedNotNull { i, cell ->
            if (cell == Cell.EMPTY) i else null
        }
    }
}