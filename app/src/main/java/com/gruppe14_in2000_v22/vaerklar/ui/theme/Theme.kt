package com.gruppe14_in2000_v22.vaerklar.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = BaseDarkMode,
    primaryVariant = BaseAltDarkMode,
    secondary = Secondary,
    background = DayTile,
    surface = DayTile,
    onPrimary = TextDarkMode,
    onSecondary = TextDarkMode,
    onBackground = TextDarkMode,
    onSurface = TextDarkMode,
    error = Warning
)

private val LightColorPalette = lightColors(
    primary = BaseLightMode,
    primaryVariant = BaseAltLightMode,
    secondary = Secondary,
    background = DayTile,
    surface = DayTile,
    onPrimary = TextLightMode,
    onSecondary = TextLightMode,
    onBackground = TextLightMode,
    onSurface = TextLightMode,
    error = Warning
)

@Composable
fun Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}