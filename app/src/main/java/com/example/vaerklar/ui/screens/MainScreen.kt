package com.example.vaerklar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.vaerklar.data.WeatherData
import com.example.vaerklar.data.getTimeSeriesIndex
import com.example.vaerklar.ui.components.Avatar
import com.example.vaerklar.ui.components.MainTile
import com.example.vaerklar.ui.components.TodayTile
import com.example.vaerklar.ui.components.WeekTile
import com.example.vaerklar.ui.theme.*

var timeSeriesIndex = 0

@Composable
// The main screen, shown when the application boots up. Loaded through MainActivity, and includes the avatar.
fun MainScreen(weatherData: WeatherData?, locationName: String) {
    timeSeriesIndex = getTimeSeriesIndex(weatherData)
    Scaffold {
        // Box that occupies the entire screen. The background is determined by the time of day and cloud condition. It is currently static.
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .background(
                    brush = Brush.verticalGradient(
                        colors = determineGradient(weatherData)
                    )
                )
        ) {
            Column {
                println("TIMESERIESINDEX ER FUCKINGS $timeSeriesIndex")
                Avatar(weatherData, locationName)
                MainTile(weatherData, timeSeriesIndex)
                TodayTile(weatherData, timeSeriesIndex)
                WeekTile(weatherData, timeSeriesIndex)
            }
        }
    }
}

// Determines the gradient of the background of the screen, based on time (2) and weather (3).
fun determineGradient(weatherData: WeatherData?): List<Color> {
    timeSeriesIndex = getTimeSeriesIndex(weatherData)
    val timeIcon = weatherData?.properties?.timeseries?.get(timeSeriesIndex - 1)?.data?.next_1_hours?.summary?.symbol_code
    val timeInt = weatherData?.properties?.timeseries?.get(timeSeriesIndex - 1)?.time?.substring(11,13)?.toInt()

    if (timeInt != null) {
        if (timeInt < 21) {
            if (timeIcon?.contains("clear") == true || timeIcon?.contains("fair") == true) {
                return ClearDay
            }

            else if (timeIcon?.contains("heavy") == true) {
                return StormyDay
            }

            return CloudyDay
        }

        else {
            if (timeIcon?.contains("clear") == true || timeIcon?.contains("fair") == true) {
                return ClearNight
            }

            else if (timeIcon?.contains("heavy") == true) {
                return StormyNight
            }

            return CloudyNight
        }
    }

    return CloudyDay
}

// Determines color for tile bases.
var baseColor = DayTile // NULL-SAFE
var altColor = DayTileAlt // NULL-SAFE

fun determineBase(weatherData: WeatherData?, timeSeriesIndex: Int) {
    val timeInt = weatherData?.properties?.timeseries?.get(timeSeriesIndex - 1)?.time?.substring(11,13)?.toInt()

    if (timeInt != null) {
        if (timeInt < 21) {
            baseColor = DayTile
            altColor = DayTileAlt
        }

        else {
            baseColor = NightTile
            altColor = NightTileAlt
        }
    }
}
