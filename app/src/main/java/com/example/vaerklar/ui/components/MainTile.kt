package com.example.vaerklar.ui.components

import androidx.compose.foundation.Image
import com.example.vaerklar.R
import com.example.vaerklar.ui.theme.DayTile1
import com.example.vaerklar.ui.theme.Rubik
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.MainActivity
import com.example.vaerklar.MainActivityViewModel
import com.example.vaerklar.data.ForecastTimeInstant
import com.example.vaerklar.data.WeatherData
import com.example.vaerklar.data.WeatherTranslation


@Composable
// The primary tile, responsible for displaying weather information beneath the avatar.
fun MainTile(data: WeatherData?) {
    //kaller på funksjonen for å sette timeseriesindex så vi kan bruke den her også
    setTimeSeriesIndex(data)
    val min_temp = data?.properties?.timeseries?.get(timeSeriesIndex)?.data?.next_6_hours?.details?.air_temperature_min
    val max_temp = data?.properties?.timeseries?.get(timeSeriesIndex)?.data?.next_6_hours?.details?.air_temperature_max
    val avg_temp = (min_temp?.plus(max_temp!!))?.div(2)?.toInt()
    val downfall = data?.properties?.timeseries?.get(timeSeriesIndex)?.data?.next_6_hours?.details?.precipitation_amount
    val weather = data?.properties?.timeseries?.get(timeSeriesIndex)?.data?.next_6_hours?.summary?.symbol_code
    var translatedWeather = weather?.let { WeatherTranslation.getTranslation(it) }
    if (translatedWeather == null) translatedWeather = "Fant ikke vær"


    //Har begge her fordi jeg er litt usikker på hvilken som skal brukes
    val wind90 = data?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.wind_speed_percentile_90
    val wind10 = data?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.wind_speed_percentile_10
    //endre til dette?
    val windAvgRn = data?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.wind_speed
    val windText = windAvgRn.toString() + "m/s"

    // The base of the card with colors.
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(195.dp)
            .padding(10.dp),
        backgroundColor = DayTile1,
        shape = RoundedCornerShape(15.dp)
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
            Column() {

                // Weather description.
                Text (
                    text = translatedWeather,
                    color = Color.White,
                    fontFamily = Rubik,
                    fontWeight = FontWeight.Normal
                )

                // Temperature in Celsius.
                Text (
                    text = avg_temp.toString(),
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

                // Precipitation measured in millimeters (mm).
                Text (
                    text = downfall.toString() + "mm",
                    textAlign = TextAlign.Right,
                    color = Color.White,
                    fontFamily = Rubik,
                    fontSize = 15.sp,
                )

                // Wind measured in meters per second (m/s).
                Text (
                    text = windText,
                    textAlign = TextAlign.Right,
                    color = Color.White,
                    fontFamily = Rubik,
                    fontSize = 15.sp,
                )
            }
        }
    }

}