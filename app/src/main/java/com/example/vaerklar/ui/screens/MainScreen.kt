package com.example.vaerklar.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vaerklar.MainActivityViewModel
import com.example.vaerklar.data.LocationData
import com.example.vaerklar.data.WeatherData
import com.example.vaerklar.ui.components.Avatar
import com.example.vaerklar.ui.components.MainTile
import com.example.vaerklar.ui.components.TodayTile
import com.example.vaerklar.ui.components.WeekTile
import com.example.vaerklar.ui.theme.GradientClearDay

@Composable
// The main screen, shown when the application boots up. Loaded through MainActivity, and includes the avatar.
fun MainScreen(weatherData: WeatherData?, locationName: String) {
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
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                val avatar = Avatar()
                avatar.avatarMain(weatherData, locationName, 0)
                MainTile(weatherData)
                TodayTile(weatherData)
                WeekTile(weatherData)
            }
        }
    }
}





