package dev.bogwalk.common.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@Composable
expect fun getPainter(res: String): Painter

@Composable
expect fun ExitDialog(onCloseRequest: () -> Unit, onConfirm: () -> Unit)