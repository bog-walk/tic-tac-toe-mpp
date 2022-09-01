package dev.bogwalk.common.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

/**
 * Class representing a computer opponent with a switchable difficulty setting.
 *
 * A move is chosen at random when BotMode.EASY (default); otherwise, a mixed offensive/defensive
 * Strategy Pattern is used when BotMode.Hard.
 */
class Bot(
    private val grid: Grid
) {
    var mode by mutableStateOf(BotMode.EASY)

    /**
     * Returns 0-indexed Cell coordinates (row, col) of the Bot instance's chosen move.
     */
    fun move(): Pair<Int, Int> {
        return when (mode) {
            BotMode.EASY -> nextRandomSpot()
            BotMode.HARD -> strategicNextMove()
        }
    }

    private fun nextRandomSpot(): Pair<Int, Int> {
        // there is no risk of random() throwing NoSuchElementException
        // as a full Grid would have been caught in the previous turn
        return grid.findEmptyCells().random().coordinates
    }

    private fun strategicNextMove(): Pair<Int, Int> {
        // assumes that Bot always moves second, as Player.O
        return if (grid.coordinatesOf(Mark.O) == null) {
            // grid would only be missing 'O' on first round
            firstMove()
        } else {
            scanGrid(::beOffensive) ?:
            scanGrid(::beDefensive) ?:
            nextRandomSpot()
        }
    }

    private fun firstMove(): Pair<Int, Int> {
        return if (Cell(1 to 1) in grid.findEmptyCells()) {
            1 to 1  // grid centre is prime spot when not first player
        } else {
            // otherwise, pick any free corner cell
            val corners = mutableSetOf(0 to 0, 0 to 2, 2 to 0, 2 to 2)
            grid.coordinatesOf(Mark.X)?.let {
                corners -= it
            }
            corners.random()
        }
    }

    /**
     * Scans all 3-Cell List combinations (diagonals, rows, columns) for an unmarked Cell based on a
     * Strategy Pattern.
     */
    private fun scanGrid(
        strategy: (List<Cell>) -> Pair<Int, Int>?
    ): Pair<Int, Int>? {
        val rows = grid.allRows().iterator()
        while (rows.hasNext()) {
            return strategy(rows.next()) ?: continue
        }
        return null
    }

    /**
     * Returns unmarked Cell coordinates if a match is found that allows Bot to win this
     * round; otherwise, returns null.
     */
    private fun beOffensive(cells: List<Cell>) = strategicCheck(cells, Mark.O)

    /**
     * Returns unmarked Cell coordinates if a match is found that blocks Player.X from having a
     * winning match next round; otherwise, returns null.
     */
    private fun beDefensive(cells: List<Cell>) = strategicCheck(cells, Mark.X)

    private fun strategicCheck(cells: List<Cell>, mark: Mark): Pair<Int, Int>? {
        val (match, extra) = cells.partition { it.mark == mark }

        // single() is safe to use due to short-circuit evaluation
        return if (match.size == 2 && extra.single().mark == Mark.EMPTY) {
            extra.single().coordinates
        } else {
            null
        }
    }
}