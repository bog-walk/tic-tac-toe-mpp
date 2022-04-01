package dev.brackish.bogwalk.common.model

import dev.brackish.bogwalk.common.isValidTestInput
import kotlin.test.*

internal class GridTest {
    @Test
    fun `helper functions catch invalid test input`() {
        val input = listOf(" ", "XXXO OXOX", "XOXO?OXOX")
        input.forEach {
            assertFalse { it.isValidTestInput() }
        }
    }

    @Test
    fun `Grid initialises correctly for game play`() {
        val grid = Grid()
        val expected = "         ".also {
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
    fun `Grid prevents invalid cell selections`() {
        val input = "XOXO OXOX".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertFailsWith<IllegalArgumentException> {
            grid.mark(0, 4, Player.PLAYER1)
        }
        assertFailsWith<IllegalArgumentException> {
            grid.mark(0, 0, Player.PLAYER1)
        }
    }

    @Test
    fun `mark correctly modifies cell`() {
        val input = "XOXO OX X".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        grid.mark(1, 1, Player.PLAYER2)
        val expected = "XOXOOOX X".also {
            assertTrue { it.isValidTestInput() }
        }
        assertEquals(expected, grid.toString())
    }

    @Test
    fun `assess correctly determines draw`() {
        val input = "XOXOOXXXO".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertEquals(GameState.OVER_DRAW, grid.assess())
    }

    @Test
    fun `assess correctly determines that game is still in play`() {
        val input = "XOX      ".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertEquals(GameState.PLAYING, grid.assess())
    }

    @Test
    fun `assess correctly finds regular win`() {
        val input = "XOXOXOX  ".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertEquals(GameState.OVER_WINNER, grid.assess())
    }

    @Test
    fun `assess correctly finds transposed win`() {
        val input = "XOXXO  O ".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertEquals(GameState.OVER_WINNER, grid.assess())
    }

    @Test
    fun `assess correctly finds diagonals win`() {
        val input = "XO  X  OX".also {
            assertTrue { it.isValidTestInput() }
        }
        val grid = Grid(input)
        assertEquals(GameState.OVER_WINNER, grid.assess())
    }
}