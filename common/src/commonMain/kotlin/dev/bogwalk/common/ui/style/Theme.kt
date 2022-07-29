package dev.bogwalk.common.ui.style

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val T3Green = Color(0xff79be84)
private val T3Purple = Color(0xffbe79b8)
private val T3Blue = Color(0xff7e79be)

val T3Colors = darkColors(
    primary = T3Green,
    secondary = T3Purple,
    secondaryVariant = T3Blue,
    background = Color.DarkGray,
    surface = Color.DarkGray,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

private val T3Typography = Typography(
    defaultFontFamily = FontFamily.Monospace,
    h5 = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.25.sp,
        textAlign = TextAlign.Center,
        lineHeight = 24.sp
    ),
    body1 = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    ),
    button = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
    )
)

private val T3Shapes = Shapes(
    small = RoundedCornerShape(50),
    medium = RoundedCornerShape(10.dp)
)

@Composable
fun T3Theme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = T3Colors,
        typography = T3Typography,
        shapes = T3Shapes
    ) {
        Surface(content = content)
    }
}