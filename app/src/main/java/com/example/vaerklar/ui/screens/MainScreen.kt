package com.example.vaerklar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    determineBase(weatherData, timeSeriesIndex)
    Scaffold {
        // Box that occupies the entire screen. The background is determined by the time of day and cloud condition. It is currently static.
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .background(
                    brush = Brush.verticalGradient(
                        colors = determineGradient(weatherData, timeSeriesIndex)
                    )
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                val avatar = Avatar()
                avatar.avatarMain(weatherData, locationName, 0, 1, "")
                MainTile(weatherData, timeSeriesIndex)
                TodayTile(weatherData, timeSeriesIndex)
                WeekTile(weatherData)
            }
        }
    }
}

// Determines the gradient of the background of the screen, based on time (2) and weather (3).
fun determineGradient(weatherData: WeatherData?, timeSeriesIndex: Int): List<Color> {
    var timeIcon: String?
    var timeInt: Int?

    if(timeSeriesIndex > 0){
        timeIcon = weatherData?.properties?.timeseries?.get(timeSeriesIndex - 1)?.data?.next_1_hours?.summary?.symbol_code
        timeInt = weatherData?.properties?.timeseries?.get(timeSeriesIndex - 1)?.time?.substring(11,13)?.toInt()
    }else{
        timeIcon = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.next_1_hours?.summary?.symbol_code
        timeInt = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.time?.substring(11,13)?.toInt()
    }

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
    var timeInt: Int
    if(timeSeriesIndex > 0){
        timeInt = weatherData?.properties?.timeseries?.get(timeSeriesIndex - 1)?.time?.substring(11,13)?.toInt()!!
    }else{
        timeInt = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.time?.substring(11,13)?.toInt()!!
    }

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
