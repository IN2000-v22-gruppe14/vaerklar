package com.example.vaerklar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.ui.theme.DayTile
import com.example.vaerklar.ui.theme.DayTileAlt
import com.example.vaerklar.ui.theme.Rubik
import com.example.vaerklar.R
import com.example.vaerklar.data.*
import com.example.vaerklar.ui.screens.altColor
import com.example.vaerklar.ui.screens.baseColor
import com.example.vaerklar.ui.screens.determineBase
import java.time.LocalDateTime

private var globalTileCounter = 0
var timeSeriesIndex = 0

//Component to be recycled. The icon (51 dp) provides the correct width for the entire row displayed.  
@Composable
fun TodayTileItem(hour: Hour, backgroundColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(backgroundColor)
            .absolutePadding(7.dp, 0.dp, 7.dp, 0.dp)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {

        Text(
            text = hour.airTemp.toString(),
            color = Color.White,
            fontFamily = Rubik,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .absolutePadding(5.dp, 0.dp, 0.dp, 0.dp)
        )

        Image(
            painter = painterResource(iconTranslation.getValue(hour.icon)),
            contentDescription = "Icon",
            modifier = Modifier
                .width(51.dp)
        )

        // Time
        Text(
            text = hour.time.toString(),
            color = Color.White.copy(alpha = 0.5f),
            fontFamily = Rubik,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
// The secondary tile, responsible for displaying today's weather across 4 timeslots. Requires TodayCycler.
fun TodayTile(weatherData: WeatherData?) {
    determineBase(weatherData)

    val hourList = mutableListOf<Hour>()
    println(weatherData)

    timeSeriesIndex = getTimeSeriesIndex(weatherData)

    // For-loop responsible for reading data from nearest next hour, then incrementing 2 hours five more times.
    for (i in timeSeriesIndex..timeSeriesIndex+10 step 2) {
        val timeString = weatherData?.properties?.timeseries?.get(i)?.time
        val airTemp = weatherData?.properties?.timeseries?.get(i)?.data?.instant?.details?.air_temperature?.toInt().toString()
        val windSpeed = weatherData?.properties?.timeseries?.get(i)?.data?.instant?.details?.wind_speed
        val precipitation = weatherData?.properties?.timeseries?.get(i)?.data?.next_1_hours?.details?.precipitation_amount_max
        val icon = weatherData?.properties?.timeseries?.get(i)?.data?.next_1_hours?.summary?.symbol_code

        val time = timeString?.substring(11,13)

        val hour = Hour(time, "$airTemp°", windSpeed, precipitation, icon)
        hourList.add(hour)
    }

    val day = remember {hourList}

    Card(
        modifier = Modifier
            .height(175.dp)
            .padding(10.dp),
        backgroundColor = DayTile,
        shape = RoundedCornerShape(15.dp),
        elevation = 0.dp
    ) {

        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(
                items = day,

                itemContent = {
                    if (globalTileCounter % 2 == 0) {
                        TodayTileItem(hour = it, altColor)
                    }

                    else {
                        TodayTileItem(hour = it, baseColor)
                    }

                    globalTileCounter++
                }
            )
        }
    }
}

// Hour class storing information about a particular hour. Windspeed and precipitation currently not in use.
public class Hour(
    val time: String?,
    val airTemp: String?,
    val windSpeed: Double?,
    val precipitation: Double?,
    val icon: String?
)