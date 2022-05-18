package com.example.vaerklar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.R
import com.example.vaerklar.data.WeatherData
import com.example.vaerklar.data.iconTranslation
import com.example.vaerklar.data.weatherDescriptionTranslation
import com.example.vaerklar.ui.screens.baseColor
import com.example.vaerklar.ui.theme.Rubik
import kotlin.math.roundToInt


@Composable
// The primary tile, responsible for displaying weather information beneath the avatar.
fun MainTile(weatherData: WeatherData?, timeSeriesIndex: Int) {

    val airTemp = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.air_temperature?.toInt()
    val airTempText = if (airTemp != null) "$airTemp°" else "Fant ikke temp"

    val precipitation = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.next_1_hours?.details?.precipitation_amount
    val precipitationText = precipitation?.toString() ?: "Fant ikke nedbør"

    val weather = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.next_1_hours?.summary?.symbol_code
    val translatedWeather = weather?.let { weatherDescriptionTranslation[it] } ?: "Fant ikke vær"

    // Wind data.
    val wind = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.wind_speed
    val windGust = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.wind_speed_of_gust

    var windText = wind?.roundToInt()?.toString() ?: "0"
    windText += if (windGust != null) " - " + windGust.roundToInt().toString() else "0"


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(195.dp)
            .padding(10.dp),
        backgroundColor = baseColor,
        shape = RoundedCornerShape(15.dp),
        elevation = 0.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(195.dp)
        ) {
            // Weather icon.
            Image(
                painter = painterResource(iconTranslation.getValue(weather)),
                "Icon",
                modifier = Modifier
                    .padding(20.dp)
            )
            Column(
                modifier = Modifier.width(100.dp)
            ) {
                // Weather description.
                Text (
                    text = translatedWeather,
                    color = Color.White,
                    fontFamily = Rubik,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp
                )
                // Temperature in Celsius.
                Text (
                    text = airTempText,
                    color = Color.White,
                    fontFamily = Rubik,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(20.dp)
            ) {
                Row {
                    // Precipitation measured in millimeters (mm).
                    Icon(
                        painter = painterResource(R.drawable.precipitation),
                        "Precipitation",
                        modifier = Modifier
                            .size(20.dp)
                            .absolutePadding(0.dp, 0.dp, 5.dp, 0.dp),
                        tint = Color.White
                    )
                    Text(
                        text = precipitationText,
                        textAlign = TextAlign.Right,
                        color = Color.White,
                        fontFamily = Rubik,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = " mm",
                        textAlign = TextAlign.Right,
                        color = Color.White.copy(alpha = 0.5f),
                        fontFamily = Rubik,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
                Row {
                    // Wind measured in meters per second (m/s).
                    Icon(
                        painter = painterResource(R.drawable.wind),
                        "Wind",
                        modifier = Modifier
                            .size(25.dp)
                            .absolutePadding(0.dp, 0.dp, 5.dp, 0.dp)
                            .align(Alignment.CenterVertically),
                        tint = Color.White
                    )
                    Text(
                        text = windText,
                        textAlign = TextAlign.Right,
                        color = Color.White,
                        fontFamily = Rubik,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = " m/s",
                        textAlign = TextAlign.Right,
                        color = Color.White.copy(alpha = 0.5f),
                        fontFamily = Rubik,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}