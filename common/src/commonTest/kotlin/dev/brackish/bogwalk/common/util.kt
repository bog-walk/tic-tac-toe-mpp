package dev.brackish.bogwalk.common

import kotlin.math.abs

/**
 * Ensures String representation of test grid is provided in the correct form, namely that it is: 9
 * elements in length, consisting of only X, O, or space characters, and that the difference
 * between the amount of X & O characters does not exceed 1.
 */
fun String.isValidTestInput(): Boolean {
    return matches(Regex("""^[XO ]{9}$""")) &&
            abs(count { it == 'X' } - count { it == 'O' }) <= 1
}
