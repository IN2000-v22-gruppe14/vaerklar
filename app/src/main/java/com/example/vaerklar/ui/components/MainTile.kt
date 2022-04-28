package com.example.vaerklar.ui.components

import androidx.compose.foundation.Image
import com.example.vaerklar.ui.theme.DayTile
import com.example.vaerklar.ui.theme.Rubik
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import com.example.vaerklar.data.WeatherData
import com.example.vaerklar.data.Translation
import com.example.vaerklar.data.iconTranslation
import kotlin.math.roundToInt

@Composable
// The primary tile, responsible for displaying weather information beneath the avatar.
fun MainTile(data: WeatherData?) {
    val minTemp = data?.properties?.timeseries?.get(0)?.data?.next_6_hours?.details?.air_temperature_min
    val maxTemp = data?.properties?.timeseries?.get(0)?.data?.next_6_hours?.details?.air_temperature_max
    val avgTemp = (minTemp?.plus(maxTemp!!))?.div(2)?.toInt()

    val precipitation = data?.properties?.timeseries?.get(0)?.data?.next_6_hours?.details?.precipitation_amount
    val weather = data?.properties?.timeseries?.get(0)?.data?.next_6_hours?.summary?.symbol_code

    var translatedWeather = weather?.let { Translation.getTranslation(it) }
    if (translatedWeather == null) translatedWeather = "(...)"

    val wind90 = data?.properties?.timeseries?.get(0)?.data?.instant?.details?.wind_speed_percentile_90
    val wind10 = data?.properties?.timeseries?.get(0)?.data?.instant?.details?.wind_speed_percentile_10

    // The base of the card with colors.
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(195.dp)
            .padding(10.dp),
        backgroundColor = DayTile,
        shape = RoundedCornerShape(15.dp),
        elevation = 0.dp
    ) {

        // Row element that allows us to place items horizontally.
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

            // Column element for placing text on top of each other.
            Column(
                modifier = Modifier.width(100.dp)
            ) {

                // Weather description.
                Text (
                    text = translatedWeather,
                    color = Color.White,
                    fontFamily = Rubik,
                    fontWeight = FontWeight.Normal
                )

                // Temperature in Celsius.
                Text (
                    text = avgTemp.toString() + "°",
                    color = Color.White,
                    fontFamily = Rubik,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // A second column responsible for additional information.
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(20.dp)
            ) {

                Row() {
                    // Precipitation measured in millimeters (mm).
                    Text(
                        text = precipitation.toString(),
                        textAlign = TextAlign.Right,
                        color = Color.White,
                        fontFamily = Rubik,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "mm",
                        textAlign = TextAlign.Right,
                        color = Color.White,
                        fontFamily = Rubik,
                        fontSize = 15.sp,
                    )
                }

                Row() {
                    // Wind measured in meters per second (m/s).
                    Text(
                        text = wind10?.roundToInt().toString() + "(" + wind90?.roundToInt().toString() + ")",
                        textAlign = TextAlign.Right,
                        color = Color.White,
                        fontFamily = Rubik,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "m/s",
                        textAlign = TextAlign.Right,
                        color = Color.White,
                        fontFamily = Rubik,
                        fontSize = 15.sp,
                    )
                }
            }
        }
    }
}