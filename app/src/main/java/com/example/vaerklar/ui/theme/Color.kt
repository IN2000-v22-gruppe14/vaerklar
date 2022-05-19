package com.example.vaerklar.ui.theme

import androidx.compose.ui.graphics.Color

// TIME OF DAY - determines the color of tiles on MainScreen, not affected by Dark or Light mode
val DayTile = Color(0xFF23323C)
val DayTileAlt = Color(0xFF2C3E4A)
val NightTile = Color(0xFF040D13)
val NightTileAlt = Color(0xFF061219)

// WEATHER - determines the gradient background on MainScreen, not affected by Dark or Light mode
val ClearDay = listOf(Color(0xFF77ACBC), Color(0xFFBAB886), Color(0xFF133B47))
val CloudyDay = listOf(Color(0xFF77ACBC), Color(0xFF799DA9), Color(0xFF133B47))
val StormyDay = listOf(Color(0xFF5C6A75), Color(0xFF133B47))

val ClearNight = listOf(Color(0xFF051621), Color(0xFF235267), Color(0xFF143B47))
val CloudyNight = listOf(Color(0xFF051621), Color(0xFF133B47), Color(0xFF143B47))
val StormyNight = listOf(Color(0xFF1F2022), Color(0xFF143B47))

// LIGHT MODE - determines color of UI base and text for Light Mode
val BaseLightMode = Color(0xFFDEFEFF)
val BaseAltLightMode = Color(0xFFA2DBD9)
val TextLightMode = Color(0xFF000000)

// DARK MODE - determines color of UI base and text for Dark Mode
val BaseDarkMode = Color(0xFF23323C)
val BaseAltDarkMode = Color(0xFF182228)
val TextDarkMode = Color(0xFFFFFFFF)

// CONSTANT
val Secondary = Color(0xFF28B9B9)
val Warning = Color(0xFF80373C)
val InactiveWhite = Color(0xFFD5D5D5)