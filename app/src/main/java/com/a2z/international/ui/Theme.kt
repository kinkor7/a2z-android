package com.a2z.international.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val A2ZDarkBlue = Color(0xFF04194E)
val A2ZGold = Color(0xFFCBBA8D)
val A2ZWhite = Color(0xFFFFFFFF)
val A2ZSoftGray = Color(0xFFF5F5F5)

@Composable
fun A2ZTheme(content: @Composable () -> Unit) {
    val colorScheme = lightColorScheme(
        primary = A2ZDarkBlue,
        secondary = A2ZGold,
        tertiary = A2ZDarkBlue,
        background = A2ZWhite,
        surface = A2ZWhite,
        onPrimary = A2ZWhite,
        onSecondary = A2ZDarkBlue,
        onBackground = A2ZDarkBlue,
        onSurface = A2ZDarkBlue
    )

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
