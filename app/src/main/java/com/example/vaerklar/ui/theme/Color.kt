package com.example.vaerklar.ui.theme

import androidx.compose.ui.graphics.Color

// DEFAULT - do not use
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

// TIME OF DAY - determines the color of tiles on MainScreen, not affected by Dark or Light mode
val DayTile = Color(0xFF23323C)
val DayTileAlt = Color(0xFF2C3E4A)
val InactiveWhite = Color(0xFFD5D5D5)

// WEATHER - determines the gradient background on MainScreen, not affected by Dark or Light mode
val ClearDay = listOf<Color>(Color(0xFF77ACBC), Color(0xFFBAB886), Color(0xFF133B47))
val CloudyDay = listOf<Color>(Color(0xFF77ACBC), Color(0xFF799DA9), Color(0xFF133B47))
val StormyDay = listOf<Color>(Color(0xFF5C6A75), Color(0xFF133B47))

val ClearNight = listOf<Color>(Color(0xFF051621), Color(0xFF235267), Color(0xFF143B47))
val CloudyNight = listOf<Color>(Color(0xFF051621), Color(0xFF133B47), Color(0xFF143B47))
val StormyNight = listOf<Color>(Color(0xFF1F2022), Color(0xFF143B47))

// LIGHT MODE - determines color of UI base and text for Light Mode
val BaseLightMode = Color(0xFFDEFEFF)
val BaseAltLightMode = Color(0xFFA2DBD9)
val TextLightMode = Color(0xFF000000)
val InactiveLightMode = Color(0xFF1C1C1C)

// DARK MODE - determines color of UI base and text for Dark Mode
val BaseDarkMode = Color(0xFF23323C)
val BaseAltDarkMode = Color(0xFF182228)
val TextDarkMode = Color(0xFFFFFFFF)
val InactiveDarkMode = Color(0xFFD5D5D5)

// CONSTANT
val Secondary = Color(0xFF28B9B9)
val Warning = Color(0xFF80373C)