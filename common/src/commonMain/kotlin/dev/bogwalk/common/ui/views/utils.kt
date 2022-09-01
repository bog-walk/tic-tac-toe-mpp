package dev.bogwalk.common.ui.views

import androidx.compose.runtime.Composable

expect sealed class Screen

expect interface T3Parcelable

// Following annotations cause error:
// Declaration annotated with '@OptionalExpectation' can only be used in common module sources.
// @OptIn(ExperimentalMultiplatform::class)
// @OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class T3Parcelize()

@Composable
expect fun ExitDialog(onCloseRequest: () -> Unit, onConfirm: () -> Unit)