package dev.bogwalk.common.model

import dev.bogwalk.common.isValidTestInput
import org.junit.BeforeClass
import kotlin.test.*

internal class BotTest {
    /**
     * The createComposeRule() used in testing requires JUnit4, so a static approach
     * is instead taken to share test resources, instead of using JUnit5's
     * @TestInstance(Lifecycle.PER_CLASS).
     */
    companion object {
        private lateinit var grid1: Grid
        private lateinit var grid2: Grid
        private lateinit var grid3: Grid

        @BeforeClass
        @JvmStatic
        fun setUp() {
            val input1 = "    X    ".also {
                assertTrue { it.isValidTestInput() }
            }
            grid1 = Grid(input1)
            val input2 = " X       ".also {
                assertTrue { it.isValidTestInput() }
            }
            grid2 = Grid(input2)
            val input3 = "  XX   O ".also {
                assertTrue { it.isValidTestInput() }
            }
            grid3 = Grid(input3)
        }
    }

    @Test
    fun `easy bot picks a random spot on first move`() {
        val bot = Bot(grid1)
        val freeCells = listOf(0 to 0, 0 to 1, 0 to 2, 1 to 0, 1 to 2, 2 to 0, 2 to 1, 2 to 2)
        assertTrue { bot.move() in freeCells }
    }

    @Test
    fun `easy bot picks a random spot on any move`() {
        val bot = Bot(grid3)
        val freeCells = listOf(0 to 0, 0 to 1, 1 to 1, 1 to 2, 2 to 0, 2 to 2)
        assertTrue { bot.move() in freeCells }
    }

    @Test
    fun `hard bot picks centre cell on first move if free`() {
        val bot = Bot(grid2).apply { mode = BotMode.HARD }
        val expected = 1 to 1
        assertEquals(expected, bot.move())
    }

    @Test
    fun `hard bot picks corner cell on first move if centre taken`() {
        val bot = Bot(grid1).apply { mode = BotMode.HARD }
        val cornerCells = listOf(0 to 0, 0 to 2, 2 to 0, 2 to 2)
        assertTrue { bot.move() in cornerCells }
    }

    @Test
    fun `hard bot uses offensive strategy first`() {
        val inputs = listOf("X  OO  XX", "XOX O   X", " O XXOXXO")
        val expected = listOf(1 to 2, 2 to 1, 0 to 2)
        for ((i, input) in inputs.withIndex()) {
            val grid = Grid(input).also {
                assertTrue { input.isValidTestInput() }
            }
            val bot = Bot(grid).apply { mode = BotMode.HARD }
            assertEquals(expected[i], bot.move())
        }
    }

    @Test
    fun `hard bot uses defensive strategy if no offensive option`() {
        val inputs = listOf("XO    X  ", "XO  O  XX", "    X  XO", " O XX  XO")
        val expected = listOf(1 to 0, 2 to 0, 0 to 1, 1 to 2)
        for ((i, input) in inputs.withIndex()) {
            val grid = Grid(input).also {
                assertTrue { input.isValidTestInput() }
            }
            val bot = Bot(grid).apply { mode = BotMode.HARD }
            assertEquals(expected[i], bot.move())
        }
    }

    @Test
    fun `hard bot picks a random spot if no strategic options`() {
        val bot = Bot(grid3).apply { mode = BotMode.HARD }
        val freeCells = listOf(0 to 0, 0 to 1, 1 to 1, 1 to 2, 2 to 0, 2 to 2)
        assertTrue { bot.move() in freeCells }
    }
}