package com.example.vaerklar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.example.vaerklar.MainActivityViewModel
import com.example.vaerklar.data.WeatherData
import com.example.vaerklar.ui.components.Avatar
import com.example.vaerklar.ui.components.MainTile
import com.example.vaerklar.ui.components.TodayTile
import com.example.vaerklar.ui.components.WeekTile
import com.example.vaerklar.ui.theme.GradientClearDay

@Composable
// The main screen, shown when the application boots up. Loaded through MainActivity, and includes the avatar.
fun MainScreen(weatherData: WeatherData?) {
    Scaffold() {
        // Box that occupies the entire screen. The background is determined by the time of day and cloud condition. It is currently static.
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .background(
                    brush = Brush.verticalGradient(
                        colors = GradientClearDay
                    )
                )
        ) {
            Column() {
                Avatar()
                MainTile(weatherData)
                TodayTile(weatherData)
                WeekTile(weatherData)
            }
        }
    }
}


