package dev.bogwalk.common.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@Composable
actual fun getPainter(res: String): Painter = androidx.compose.ui.res.painterResource(res)