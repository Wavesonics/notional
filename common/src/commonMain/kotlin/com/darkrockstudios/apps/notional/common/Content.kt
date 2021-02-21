package com.darkrockstudios.apps.notional.common

import androidx.compose.runtime.Composable

typealias Content = @Composable () -> Unit

fun <T : Any> T.asContent(content: @Composable (T) -> Unit): Content = { content(this) }