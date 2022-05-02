package com.example.vaerklar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.example.vaerklar.ui.components.SettingsTile
import com.example.vaerklar.ui.theme.ClearDay
import kotlin.math.roundToInt

@Composable
fun SettingsScreen() {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = ClearDay
                    )
                )
        ) {
            Column {
                SettingsTile()
            }
        }
    }
}

fun toggleTheme(darkmode: Boolean) {
    TODO("Toggle between light and dark theme")
}

fun notifications(notify: Boolean) {
    TODO("Toggles notifications onn and off")
}

fun performanceMode(performance: Boolean) {
    TODO("Toggles performance mode, saving battery")
}

fun adjustWarmth(hotness: Float) {
    val hot: Int = hotness.roundToInt()
    TODO("Make this adjust the recommended clothes")
}

fun clearData() {
    TODO("Clears all saved data the app has on you")
}

fun termsOfService() {
    TODO("Shows a page with terms of Service")
}

fun invite() {
    TODO("Invite friends to download the app")
}

fun helpAndSupport() {
    TODO("Sends the user to a help page")
}