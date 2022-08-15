package dev.bogwalk.common.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

/**
 * Bot can only access an immutable encapsulated Grid for analysis.
 */
class Bot(
    private val grid: Grid
) {
    var mode by mutableStateOf(BotMode.EASY)

    /**
     * Returns 0-indexed Cell coordinates (row, col) of Bot's chosen move.
     */
    fun move(): Pair<Int, Int> {
        return when (mode) {
            BotMode.EASY -> nextRandomSpot()
            BotMode.HARD -> hardBotNextMove()
        }
    }

    /**
     * There is no concern of random() throwing a NoSuchElementException, as a full grid would
     * have been caught in the previous round.
     */
    private fun nextRandomSpot(): Pair<Int, Int> {
        return grid.findEmptyCells().random().coordinates
    }

    private fun hardBotNextMove(): Pair<Int, Int> {
        // assumes that Bot always moves second, as Player.O
        return if (grid.coordinatesOf("O") == null) {
            // grid would only be missing 'O' on first round
            firstMove()
        } else {
            scanGrid(::isOffensive) ?:
            scanGrid(::isDefensive) ?:
            nextRandomSpot()
        }
    }

    private fun firstMove(): Pair<Int, Int> {
        return if (Cell(1 to 1) in grid.findEmptyCells()) {
            1 to 1  // grid centre is prime spot when not first player
        } else {
            // pick any free corner cell
            val corners = mutableSetOf(0 to 0, 0 to 2, 2 to 0, 2 to 2)
            // could safely use !! as Bot always moves second
            grid.coordinatesOf("X")?.let {
                corners -= it
            }
            corners.random()
        }
    }

    /**
     * Scans all 3-character combinations (diagonals, rows, columns) for a free cell based on a
     * Strategy Pattern set by BotMode.
     */
    private fun scanGrid(strategy: (List<Cell>) -> Pair<Int, Int>?): Pair<Int, Int>? {
        val combos = grid.allRows().iterator()
        while (combos.hasNext()) {
            //val coordinates = strategy(combos.next())
            //if (coordinates.first != -1) return coordinates
            return strategy(combos.next()) ?: continue
        }
        return null
    }

    /**
     * Returns `true` if a match is found that allows Bot to win this round.
     *
     * single() is safe to use due to short-circuit evaluation (row will always have 3 Cells).
     */
    private fun isOffensive(cells: List<Cell>): Pair<Int, Int>? {
        val (match, extra) = cells.partition { it.mark == Mark.O }
        return if (match.size == 2 && extra.single().mark == Mark.EMPTY) {
            extra.single().coordinates
        } else {
            null
        }
        //return 'X' !in cells && cells.count { it == 'O' } == 2
    }

    /**
     * Returns `true` if a match is found that blocks Player.X from having a winning match next
     * round.
     *
     * single() is safe to use due to short-circuit evaluation (row will always have 3 Cells).
     */
    private fun isDefensive(cells: List<Cell>): Pair<Int, Int>? {
        val (match, extra) = cells.partition { it.mark == Mark.X }
        return if (match.size == 2 && extra.single().mark == Mark.EMPTY) {
            extra.single().coordinates
        } else {
            null
        }
        //return 'O' !in cells && cells.count { it == 'X' } == 2
    }
}