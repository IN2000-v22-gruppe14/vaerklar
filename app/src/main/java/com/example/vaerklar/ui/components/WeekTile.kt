package com.example.vaerklar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.data.WeatherData
import com.example.vaerklar.data.iconTranslation
import com.example.vaerklar.data.translateWeekdayAbbreviation
import com.example.vaerklar.data.translateWeekDayWhole
import com.example.vaerklar.ui.screens.altColor
import com.example.vaerklar.ui.screens.baseColor
import com.example.vaerklar.ui.theme.DayTile
import com.example.vaerklar.ui.theme.Rubik
import java.time.LocalDate
import java.time.Period

private var globalTileCounter = 0
private val showDialog = mutableStateOf(false)
private var globalDayNumber = 0
val dayList = mutableListOf<Day>()

@Composable
private fun PopUpScreen(weatherData: WeatherData?) {
    AlertDialog(
        backgroundColor = baseColor,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.height(620.dp),
        title = {
            Column(
                Modifier
                    .fillMaxSize()
                    .size(200.dp, 100.dp)
                    .background(baseColor)
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                ) {
                    val avatar = Avatar()
                    val dayName = if (dayList[globalDayNumber].dayNameWhole != null) dayList[globalDayNumber].dayNameWhole.toString() else ""

                    var date = ""
                    if (dayList[globalDayNumber].date != null) {
                        val day = dayList[globalDayNumber].date.toString().substring(8,10)
                        val month = dayList[globalDayNumber].date.toString().substring(5,7)
                        date = "$day.$month"
                    }

                    val outfit = avatar.avatarMain(weatherData, dayName, dayList[globalDayNumber].timeIndex, 0, date)
                    var outfitTextRepresentation = ""
                    for (clothingItem in outfit) {
                        if (clothingItem != "") {
                            outfitTextRepresentation += "${clothingItem.capitalize()}\n"
                        }
                    }
                    Box(
                        Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter) {
                        Text(
                            text = outfitTextRepresentation,
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .absolutePadding(40.dp, 50.dp, 50.dp, 0.dp)
                        )
                    }
                }
            }
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd) {
                Text(
                    text = " "
                )
                Icon(
                    imageVector = Icons.Default.Close,
                    "Lukk",
                    modifier = Modifier
                        .size(35.dp)
                        .absolutePadding(0.dp, 0.dp, 5.dp, 0.dp)
                        .clickable { showDialog.value = false }
                        .offset(0.dp, 10.dp),
                    tint = Color.White,
                )
            }
        },
        onDismissRequest = {},
        buttons = {}
    )
}

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
            .clickable(
                onClick = {
                    showDialog.value = true
                    globalDayNumber = day.dayNumber
                }
            )
    ) {
        // Temperature
        Text(
            text = day.airTemp.toString(),
            color = Color.White,
            fontFamily = Rubik,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .absolutePadding(5.dp, 0.dp, 0.dp, 0.dp)
        )
        // Weather icon
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
    val currentDate = LocalDate.now()
    var counter = 0
    var dayDiff = 0
    if (showDialog.value) {
        PopUpScreen(weatherData)
    }

    // While-loop responsible for finding weather information for the next six days. Runs as long as the difference in days from now to then is less than 7 days.
    var index = -1
    while (dayDiff != 6) {
        val hour = weatherData?.properties?.timeseries?.get(counter)?.time?.substring(11,13) // provided hour of given date

        // If the found hour is 12:00:00 (which is applicable for all future dates), create a day object.
        if (hour == "12") {
            index++
            val dayDate = weatherData.properties.timeseries[counter].time.substring(0, 10)
            val airTemp = weatherData.properties.timeseries[counter].data.instant.details?.air_temperature?.toInt().toString()
            val windSpeed = weatherData.properties.timeseries[counter].data.instant.details?.wind_speed
            val precipitation = weatherData.properties.timeseries[counter].data.next_1_hours?.details?.precipitation_amount_max
            val icon = weatherData.properties.timeseries[counter].data.next_6_hours?.summary?.symbol_code

            val dayIntermediary = LocalDate.parse(dayDate).dayOfWeek // acquire name of day in English
            val dayName = translateWeekdayAbbreviation(dayIntermediary.name.substring(0,2)) // acquire two first letters of the name of the day
            val dayNameWhole  = translateWeekDayWhole(dayIntermediary.name.substring(0,2)) //aquire the whole name

            val day = Day(dayName, dayNameWhole,  dayDate,"$airTemp°", windSpeed, precipitation, icon, counter, index) // create day object
            if (dayList.size < 6) {
                dayList.add(day) // add day object to list of days
            }
            dayDiff = Period.between(currentDate, LocalDate.parse(dayDate)).days // calculate new difference of days between today and the provided day
        }

        counter++
    }

    val day = remember { dayList }

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
    val dayNameWhole: String?,
    val date: String?,
    val airTemp: String?,
    val windSpeed: Double?,
    val precipitation: Double?,
    val icon: String?,
    val timeIndex: Int,
    val dayNumber: Int
)
