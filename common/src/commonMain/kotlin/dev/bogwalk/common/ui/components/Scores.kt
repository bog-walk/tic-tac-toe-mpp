package dev.bogwalk.common.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
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
    Column(
        modifier = Modifier.padding(top = smallPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SingleScore(
            PX_TEXT,
            MaterialTheme.colors.primary,
            p1Score
        )
        Spacer(modifier = Modifier.height(componentPadding))
        SingleScore(
            if (p2IsBot) BOT_TEXT else PO_TEXT,
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
        horizontalArrangement = Arrangement.Center,
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
                (slideInVertically(tween(SCORE_ANIM_DUR, easing = FastOutSlowInEasing)) { y -> y }
                        + fadeIn(tween(SCORE_ANIM_DUR)))
                    .with(slideOutVertically(tween(SCORE_ANIM_DUR, easing = FastOutSlowInEasing)) { y -> -y }
                            + fadeOut(tween(SCORE_ANIM_DUR)))
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