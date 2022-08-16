package dev.bogwalk.common.model

/**
 * Class representing the standard 3x3 game board with encapsulated functionality that progresses
 * game play and allows safe bot analysis.
 */
class Grid(
    elements: String = ""
) {
    private val _cells = if (elements.isEmpty()) {
        List(3) { row -> MutableList(3) { col -> Cell(row to col) } }
    } else {
        List(3) { row -> MutableList(3) { col ->
            when (val ch = elements[row * 3 + col % 3].toString()) {
                " " -> Cell(row to col)
                else -> Cell(row to col, Mark.valueOf(ch), false)
            }
        } }
    }
    val cells: List<Cell>
        get() = _cells.flatten()

    override fun toString(): String {
        return cells.joinToString("") { it.mark.toString() }
    }

    /**
     * Alters the game Grid with the player's chosen move, if valid.
     *
     * Accepts [row] and [col] as already 0-indexed Cell coordinates.
     *
     * @throws IllegalArgumentException if coordinates are out of bounds or if the Cell is
     * already marked.
     */
    fun mark(row: Int, col: Int, player: Player) {
        require(row in 0..2 && col in 0..2) { "Invalid cell coordinates" }
        require(_cells[row][col].mark == Mark.EMPTY) { "Cell already occupied" }
        _cells[row][col] = _cells[row][col].copy(
            mark = Mark.valueOf(player.name), isEnabled = false
        )
    }

    /**
     * If a winner is found, it must be current Player; otherwise, this function would have
     * returned `true` on the previous assess. There is, therefore, no need to include an argument
     * for current Player.
     */
    fun findWinner(): Boolean {
        val winning = listOf("XXX", "OOO")
        val rows = allRows().iterator()
        while (rows.hasNext()) {
            if (rows.next().joinToString("") { it.mark.toString() } in winning) {
                return true
            }
        }
        return false
    }

    /**
     * Yields List representations of every row combination, i.e. 2 diagonals, 3 rows, and 3
     * columns, then suspends until the next List is requested by the iterator.
     *
     * @return Sequence of Lists with 3 Cells each, representing a row combination in current Grid.
     */
    internal fun allRows() = sequence {
        diagonals().forEach { yield(it) }
        _cells.forEach { yield(it) }
        transpose().forEach { yield(it) }
    }

    private fun diagonals(): List<List<Cell>> {
        val principal = List(3) { _cells[it][it] }
        val secondary = List(3) { _cells[it][2-it] }
        return listOf(principal, secondary)
    }

    private fun transpose(): List<List<Cell>> {
        return List(3) { row -> List(3) { col -> _cells[col][row] } }
    }

    fun findEmptyCells(): List<Cell> {
        return cells.filter { it.mark == Mark.EMPTY }
    }

    fun clear() {
        for (row in 0..2) {
            for (col in 0..2) {
                _cells[row][col] = Cell(row to col)
            }
        }
    }

    /**
     * Returns the coordinates of the first occurrence of the specified Mark, or null if the Mark
     * is not present in the Grid.
     */
    internal fun coordinatesOf(mark: Mark): Pair<Int, Int>? {
        return cells.firstOrNull { it.mark == mark }?.coordinates
    }
}