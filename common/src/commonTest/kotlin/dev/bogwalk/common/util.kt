package dev.bogwalk.common

import kotlin.math.abs

/**
 * Ensures that String representation of the test grid is provided in the correct format:
 *
 *  - 9 elements in length
 *  - consists of only X, O, or space characters
 *  - the difference between the amount of X & O characters does not exceed 1 (if X always starts
 *  first, it can only ever be 1 step ahead of O).
 */
fun String.isValidTestInput(): Boolean {
    return this matches Regex("""^[XO ]{9}$""") &&
            abs(count { it == 'X' } - count { it == 'O' }) <= 1
}