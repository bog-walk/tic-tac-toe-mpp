package dev.bogwalk.common.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import dev.brackish.bogwalk.common.R

@Composable
actual fun getPainter(res: String): Painter {
    val imageName = res.substringAfterLast("/").substringBeforeLast(".")
    val drawableClass = R.drawable::class.java
    val field = drawableClass.getDeclaredField(imageName)
    val idValue = field.get(drawableClass) as Int
    return androidx.compose.ui.res.painterResource(idValue)
}