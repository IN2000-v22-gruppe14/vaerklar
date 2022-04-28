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
import com.example.vaerklar.data.*
import com.example.vaerklar.ui.screens.altColor
import com.example.vaerklar.ui.screens.baseColor
import com.example.vaerklar.ui.screens.determineBase
import com.example.vaerklar.ui.theme.DayTile
import com.example.vaerklar.ui.theme.Rubik
import java.time.LocalDate
import java.time.Period

private var globalTileCounter = 0

@Composable
// The tertiary tile, responsible for displaying this week's weather. Requires WeekCycler.
fun WeekTileItem(day: Day, backgroundColor: Color) {
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
            text = day.airTemp.toString(),
            color = Color.White,
            fontFamily = Rubik,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .absolutePadding(5.dp, 0.dp, 0.dp, 0.dp)
        )

        Image(
            painter = painterResource(iconTranslation.getValue(day.icon)),
            contentDescription = "Icon",
            modifier = Modifier
                .width(51.dp)
        )

        // Time
        Text(
            text = day.dayName.toString(),
            color = Color.White.copy(alpha = 0.5f),
            fontFamily = Rubik,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
// The secondary tile, responsible for displaying today's weather across 4 timeslots. Requires TodayCycler.
fun WeekTile(weatherData: WeatherData?) {
    val dayList = mutableListOf<Day>()
    val currentDate = LocalDate.now()
    var counter = 0
    var dayDiff = 0

    // Find the index for the current provided hour.
    setTimeSeriesIndex(weatherData)

    // Determine the base color based on time.
    determineBase(weatherData)

    // While-loop responsible for finding weather information for the next six days. Runs as long as the difference in days from now to then is less than 7 days.
    while (dayDiff != 6) {
        var dayDate = "" // provided date
        val hour = weatherData?.properties?.timeseries?.get(counter)?.time?.substring(11,13) // provided hour of given date

        // If the found hour is 12:00:00 (which is applicable for all future dates), create a day object.
        if (hour == "12") {
            dayDate = weatherData.properties.timeseries[counter].time.substring(0, 10)
            val airTemp = weatherData.properties.timeseries[counter].data.instant.details?.air_temperature?.toInt().toString()
            val windSpeed = weatherData.properties.timeseries[counter].data.instant.details?.wind_speed
            val precipitation = weatherData.properties.timeseries[counter].data.next_1_hours?.details?.precipitation_amount_max
            val icon = weatherData.properties.timeseries[counter].data.next_6_hours?.summary?.symbol_code

            val dayIntermediary = LocalDate.parse(dayDate).dayOfWeek // acquire name of day in English
            val dayName = dayTranslation[dayIntermediary.name.substring(0,2)] // acquire two first letters of the name of the day

            val day = Day(dayName,  dayDate,"$airTemp°", windSpeed, precipitation, icon) // create day object
            dayList.add(day) // add day object to list of days
            dayDiff = Period.between(currentDate, LocalDate.parse(dayDate)).days // calculate new difference of days between today and the provided day
        }

        counter++
    }

    val day = remember {dayList}

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
                    when {
                        globalTileCounter == 1 -> {
                            WeekTileItem(day = it, baseColor)
                        }
                        globalTileCounter % 2 == 0 -> {
                            WeekTileItem(day = it, altColor)
                        }
                        else -> {
                            WeekTileItem(day = it, baseColor)
                        }
                    }

                    globalTileCounter++
                }
            )
        }
    }
}

class Day (
    val dayName: String?,
    val date: String?,
    val airTemp: String?,
    val windSpeed: Double?,
    val precipitation: Double?,
    val icon: String?
)
