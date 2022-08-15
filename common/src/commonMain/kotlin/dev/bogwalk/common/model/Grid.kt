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
     * Alters the game grid with the player's chosen move, if valid. Accepts [row] and [col] as
     * already 0-indexed Cell coordinates.
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
     * If a winner is found, it must be current player; otherwise, this function would have
     * returned `true` on the previous assess. There is, therefore, no need to include an argument
     * for current player.
     */
    fun findWinner(): Boolean {
        val winning = listOf("XXX", "OOO")
        val combos = allRows().iterator()
        while (combos.hasNext()) {
            //if (combos.next().first in listOf("XXX", "OOO")) return true
            if (combos.next().joinToString("") { it.mark.toString() } in winning) {
                return true
            }
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
        //for (row in diagonals() zip listOf(listOf(0, 4, 8), listOf(2, 4, 6))) {
        for (row in diagonals()) {
            yield(row)
        }
        //for ((i, row) in _cells.withIndex()) {
        for (row in _cells) {
            //yield(row.stringify() to listOf(i * 3, i * 3 + 1, i * 3 + 2))
            yield(row)
        }
        //for ((i, row) in transpose().withIndex()) {
        for (row in transpose()) {
            //yield(row.stringify() to listOf(i, i + 3, i + 6))
            yield(row)
        }
    }

    //private fun diagonals(): List<String> {
    private fun diagonals(): List<List<Cell>> {
        //val principal = List(3) { _cells[it][it] }.stringify()
        //val secondary = List(3) { _cells[it][2-it] }.stringify()
        val principal = List(3) { _cells[it][it] }
        val secondary = List(3) { _cells[it][2-it] }
        return listOf(principal, secondary)
    }

    private fun transpose(): List<List<Cell>> {
        return List(3) { row -> List(3) { col -> _cells[col][row] } }
    }

    /**
     * Returns the coordinates of the first occurrence of the specified mark, or null if the mark
     * is not present in the grid.
     */
    fun coordinatesOf(mark: String): Pair<Int, Int>? {
        //return _cells.flatten().indexOf(Cell.valueOf(mark))
        return cells.firstOrNull { it.mark == Mark.valueOf(mark) }?.coordinates
    }

    /**
     * Returns a List of all Cells that are empty.
     */
    fun findEmptyCells(): List<Cell> {
        return cells.filter { it.mark == Mark.EMPTY }
    }

    fun clear() {
        for (r in 0..2) {
            for (c in 0..2) {
                _cells[r][c] = Cell(r to c)
            }
        }
    }
}