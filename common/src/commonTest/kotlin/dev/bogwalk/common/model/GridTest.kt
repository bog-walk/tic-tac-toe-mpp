package dev.bogwalk.common.model

import dev.bogwalk.common.isValidTestInput
import kotlin.test.*

internal class GridTest {
    @Test
    fun `isValidTestInput() catches invalid test input`() {
        val input = listOf(" ", "XOXO?OXOX", "XXXO OXOX")
        input.forEach {
            assertFalse { it.isValidTestInput() }
        }
    }

    @Test
    fun `Grid initialises correctly for game play`() {
        val grid = Grid()
        val expected = " ".repeat(9).also {
            assertTrue { it.isValidTestInput() }
        }
        assertEquals(expected, grid.toString())
    }

    @Test
    fun `Grid initialises correctly for testing`() {
        val input = "XOXO OXOX".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertEquals(input, grid.toString())
    }

    @Test
    fun `mark() prevents invalid cell selections`() {
        val input = "XOXO OXOX".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertFailsWith<IllegalArgumentException> {
            grid.mark(0, 4, Player.X)
        }
        assertFailsWith<IllegalArgumentException> {
            grid.mark(-2, 2, Player.X)
        }
        assertFailsWith<IllegalArgumentException> {
            grid.mark(0, 0, Player.X)
        }
    }

    @Test
    fun `mark() correctly modifies cell`() {
        val input = "XOXO OX X".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        grid.mark(1, 1, Player.O)
        val expected = "XOXOOOX X".also {
            assertTrue { it.isValidTestInput() }
        }
        assertEquals(expected, grid.toString())
    }

    @Test
    fun `findWinner() correctly finds winner on full grid`() {
        val input = "XOXXOOXXO".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertTrue { grid.findWinner() }
    }

    @Test
    fun `findWinner() does not mistake draw for win on full grid`() {
        val input = "XOXOOXXXO".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertFalse { grid.findWinner() }
    }

    @Test
    fun `findWinner() correctly determines that game is still in play`() {
        val input = "XOX      ".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertFalse { grid.findWinner() }
    }

    @Test
    fun `findWInner() correctly finds regular win`() {
        val input = "XXX O OO ".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertTrue { grid.findWinner() }
    }

    @Test
    fun `findWinner() correctly finds transposed win`() {
        val input = "XOXXO  O ".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertTrue { grid.findWinner() }
    }

    @Test
    fun `findWinner() correctly finds diagonals win`() {
        val input = "XO  X  OX".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertTrue { grid.findWinner() }
    }

    @Test
    fun `allRows() yields the requested amount of rows`() {
        val input = "XXX O OO ".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        val expectedMarks = listOf("XO ", "XOO", "XXX", " O ", "OO ", "X O")
        val expectedIndices = listOf(
            listOf(0, 4, 8), listOf(2, 4, 6),
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 3, 6)
        )
        val actual = grid.allRows().take(6).toList()
        assertContentEquals(expectedMarks, actual.unzip().first)
        assertContentEquals(expectedIndices, actual.unzip().second)
    }

    @Test
    fun `allRows() correctly yields until iterator predicate fulfilled`() {
        // both X and O are winners in this odd example
        val input = "XXXOOO   ".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        // but X should be caught first based on function implementation (i.e. yield order)
        val expected = "XXX" to listOf(0, 1, 2)
        var actual = "" to emptyList<Int>()
        val iter = grid.allRows().iterator()
        while (iter.hasNext()) {
            actual = iter.next()
            if (actual.first in listOf("OOO", "XXX")) break
        }
        assertEquals(expected, actual)
    }

    @Test
    fun `indexOf() correctly returns -1 if mark absent`() {
        val input = "X        ".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertEquals(-1, grid.indexOf("O"))
    }

    @Test
    fun `indexOf() correctly finds only first occurrence`() {
        val input = "  O XXO  ".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertEquals(4, grid.indexOf("X"))
        assertEquals(2, grid.indexOf("O"))
    }

    @Test
    fun `findEmptySpots() returns empty list with full grid`() {
        val input = "XOXOOXXXO".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertTrue { grid.findEmptySpots().isEmpty() }
    }

    @Test
    fun `findEmptySpots() returns correct list`() {
        val input = "XXX O OO ".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        val expected = listOf(3, 5, 8)
        assertContentEquals(expected, grid.findEmptySpots())
    }

    @Test
    fun `clear() empties all marks from grid`() {
        val input = "XOXOOXXXO".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        grid.clear()
        val expected = "         ".also {
            assertTrue { it.isValidTestInput() }
        }
        assertEquals(expected, grid.toString())
    }
}