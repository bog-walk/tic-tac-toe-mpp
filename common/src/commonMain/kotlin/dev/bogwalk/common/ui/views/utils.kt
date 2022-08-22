package dev.bogwalk.common.ui.views

import androidx.compose.runtime.Composable

expect sealed class Screen

@Composable
expect fun ExitDialog(onCloseRequest: () -> Unit, onConfirm: () -> Unit)