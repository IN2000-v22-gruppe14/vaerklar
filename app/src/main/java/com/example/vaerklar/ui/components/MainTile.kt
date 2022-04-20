package com.example.vaerklar.ui.components

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
import com.example.vaerklar.data.WeatherTranslation

@Preview
@Composable
// The primary tile, responsible for displaying weather information beneath the avatar.
fun MainTile(viewmodel:MainActivityViewModel) {
    val data = viewmodel.getWeatherData()
    val min_temp = data.value?.properties?.timeseries?.get(0)?.data?.next_6_hours?.details?.air_temperature_min
    val max_temp = data.value?.properties?.timeseries?.get(0)?.data?.next_6_hours?.details?.air_temperature_max
    val avg_temp = (min_temp?.plus(max_temp!!))?.div(2)
    val prob_parcipation = data.value?.properties?.timeseries?.get(0)?.data?.next_6_hours?.details?.probability_of_precipitation
    var downfall = "sol"
    if (prob_parcipation != null) {
        if(prob_parcipation >= 50){downfall="nedbør"}
    }
    val weather = data.value?.properties?.timeseries?.get(0)?.data?.next_6_hours?.summary?.symbol_code
    var translatedWeather = weather?.let { WeatherTranslation.getTranslation(it) }
    if (translatedWeather == null) translatedWeather = "Fant ikke vær"

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
            Icon(
                painter = painterResource(R.drawable.ic_sunny),
                "Icon",
                modifier = Modifier
                    .padding(20.dp)
            )

            // Column element for placing text on top of each other.
            Column() {

                // Weather description.
                if (translatedWeather != null) {
                    Text (
                        text = translatedWeather,
                        color = Color.White,
                        fontFamily = Rubik,
                        fontWeight = FontWeight.Normal
                    )
                }

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
                    text = downfall,
                    textAlign = TextAlign.Right,
                    color = Color.White,
                    fontFamily = Rubik,
                    fontSize = 15.sp,
                )

                // Wind measured in meters per second (m/s).
                Text (
                    text = "Wind",
                    textAlign = TextAlign.Right,
                    color = Color.White,
                    fontFamily = Rubik,
                    fontSize = 15.sp,
                )
            }
        }
    }

}