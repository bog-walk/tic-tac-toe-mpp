package dev.bogwalk.common.ui.components

import androidx.compose.animation.*
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import dev.bogwalk.common.ui.style.*

@Composable
fun Scores(
    p1Score: Int,
    p2Score: Int,
    p2IsBot: Boolean
) {
    Row(
        modifier = Modifier.padding(componentPadding).requiredWidth(windowWidth),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SingleScore(
            if (p2IsBot) SP_PX_TEXT else MP_P1_TEXT,
            MaterialTheme.colors.primary,
            p1Score
        )
        SingleScore(
            if (p2IsBot) BOT_TEXT else MP_P2_TEXT,
            MaterialTheme.colors.secondary,
            p2Score
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SingleScore(
    label: String,
    pColor: Color,
    score: Int
) {
    Row(
        modifier = Modifier.requiredWidth(scoreRow),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = pColor,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.width(componentPadding))
        // Sourced from the official Jetpack Compose Animation guide.
        // https://developer.android.com/jetpack/compose/animation
        AnimatedContent(
            targetState = score,
            transitionSpec = {
                // score will only ever increase
                (slideInVertically { y -> y } + fadeIn())
                    .with(slideOutVertically { y -> -y } + fadeOut())
                    .using(
                        // if not disabled, slide animation will not be displayed out of bounds
                        SizeTransform(clip = false)
                    )
            }
        ) { targetCount ->
            Text(
                text = "$targetCount",
                modifier = Modifier.testTag(SCORE_TEST_TAG),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body1
            )
        }
    }
}