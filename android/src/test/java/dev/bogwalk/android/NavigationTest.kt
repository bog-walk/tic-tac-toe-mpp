package dev.bogwalk.android

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dev.bogwalk.common.model.GameMode
import dev.bogwalk.common.ui.style.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * TODO!!!!
 *
 * Unsure of how to create and link Android resource generated ids through NavHost without
 * creating a XML nav graph....
 *
 * Searching for a way to link composeTestRule.activity to a navController. Using
 * navController.graph.startDestinationId is not possible without finding it first?
 */
internal class NavigationTest {
    /*
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun `app launches in EntryView`() {
        assertEquals(getNavController().currentDestination?.id, R.id.???)
    }

    @Test
    fun `navigate from entry to 2-player`() {
        navigateToGame(GameMode.DOUBLE)

        assertEquals(getNavController().currentDestination?.id, R.id.???)
        composeTestRule
            .onNodeWithTag(TOGGLE_TEST_TAG)
            .assertIsNotEnabled()
    }

    @Test
    fun `navigate from entry to 1-player`() {
        navigateToGame(GameMode.SINGLE)

        assertEquals(getNavController().currentDestination?.id, R.id.???)
        composeTestRule
            .onNodeWithTag(TOGGLE_TEST_TAG)
            .assertIsEnabled()
    }

    @Test
    fun `navigate from GameView to EntryView using TopAppBar icon`() {
        navigateToGame(GameMode.SINGLE)
        composeTestRule
            .onNodeWithContentDescription(HOME_DESCRIPTION)
            .performClick()

        // Dialog should appear
        composeTestRule
            .onNodeWithText(DIALOG_TEXT)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(DIALOG_BUTTON_TEXT)
            .performClick()

        assertEquals(getNavController().currentDestination?.id, R.id.???)
    }

    @Test
    fun `navigate from GameView to EntryView using back button`() {
        navigateToGame(GameMode.SINGLE)
        // Press back button only possible with Espresso?

        // Dialog should appear
        composeTestRule
            .onNodeWithText(DIALOG_TEXT)
            .assertIsDisplayed()

        // Dialog should not be dismissed by back button
        // Press back button only possible with Espresso?
        composeTestRule
            .onNodeWithText(DIALOG_TEXT)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(DIALOG_BUTTON_TEXT)
            .performClick()

        assertEquals(getNavController().currentDestination?.id, R.id.???)
    }

    private fun navigateToGame(mode: GameMode) {
        when (mode) {
            GameMode.DOUBLE -> {
                composeTestRule
                    .onAllNodesWithTag(GAME_MODE_TEST_TAG)
                    .onFirst()
                    .performClick()
            }
            GameMode.SINGLE -> {
                composeTestRule
                    .onAllNodesWithTag(GAME_MODE_TEST_TAG)
                    .onLast()
                    .performClick()
            }
        }
    }

    private fun getNavController(): NavController {
        return composeTestRule.activity.findNavController(R.id.???)
    }*/
}