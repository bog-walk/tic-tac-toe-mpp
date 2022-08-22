package dev.bogwalk.common.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Composable
actual fun getPainter(res: String): Painter = painterResource(res)