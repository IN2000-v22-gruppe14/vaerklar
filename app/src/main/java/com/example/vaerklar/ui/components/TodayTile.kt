package com.example.vaerklar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.example.vaerklar.R
import com.example.vaerklar.data.WeatherData
import com.example.vaerklar.data.iconTranslation
import com.example.vaerklar.ui.screens.altColor
import com.example.vaerklar.ui.screens.baseColor
import com.example.vaerklar.ui.theme.DayTile
import com.example.vaerklar.ui.theme.Rubik
import java.util.*

private var globalTileCounter = 0
private val showDialog = mutableStateOf(false)
private var globalHourNumber = 0
val hourList = mutableListOf<Hour>()

@Composable
private fun PopUpScreen(weatherData: WeatherData?) {
    AlertDialog(

        backgroundColor = baseColor,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.height(620.dp),

        title = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .size(200.dp, 100.dp)
                    .background(baseColor)
            ){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val avatar = Avatar()
                    var theTime = ""
                    if(hourList[globalHourNumber].time != null){
                        theTime = hourList[globalHourNumber].time.toString() + ":00"
                    }
                    val piss = avatar.avatarMain(weatherData, theTime, hourList[globalHourNumber].timeIndex, 0, "")
                    var theString = ""
                    for(i in piss){
                        if(i != ""){
                            theString += i.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + "\n"
                        }
                    }

                    Box(
                        Modifier
                        .fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter){
                            Text(
                                text = theString,
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
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.TopEnd
                ){
                    Text(
                        text = " "
                    )

                    Icon(
                        imageVector = Icons.Default.Close,
                        "Exit",
                        modifier = Modifier
                            .size(35.dp)
                            .absolutePadding(0.dp, 0.dp, 5.dp, 0.dp)
                            .clickable { showDialog.value = false }
                            .offset(0.dp, 10.dp),
                        tint = Color.White,
                    )
                }
            },
            onDismissRequest = {

            },

            buttons = {

            }
    )
}


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
            .clickable(onClick = { showDialog.value = true
                                    globalHourNumber = hour.hourNumber
                })
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

        Row() {
            // Time
            Text(
                text = hour.time.toString(),
                color = Color.White.copy(alpha = 0.5f),
                fontFamily = Rubik,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )

            // 00
            Text(
                text = ":00",
                color = Color.White.copy(alpha = 0.5f),
                fontFamily = Rubik,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
// The secondary tile, responsible for displaying today's weather across 4 timeslots. Requires TodayCycler.
fun TodayTile(weatherData: WeatherData?, timeSeriesIndex: Int) {
    if (showDialog.value) {
        PopUpScreen(weatherData)
    }

    // For-loop responsible for reading data from nearest next hour, then incrementing 2 hours five more times.
    var count = -1
    for (i in timeSeriesIndex..timeSeriesIndex+10 step 2) {
        count++
        val timeString = weatherData?.properties?.timeseries?.get(i)?.time
        val airTemp = weatherData?.properties?.timeseries?.get(i)?.data?.instant?.details?.air_temperature?.toInt().toString()
        val windSpeed = weatherData?.properties?.timeseries?.get(i)?.data?.instant?.details?.wind_speed
        val precipitation = weatherData?.properties?.timeseries?.get(i)?.data?.next_1_hours?.details?.precipitation_amount_max
        val icon = weatherData?.properties?.timeseries?.get(i)?.data?.next_1_hours?.summary?.symbol_code

        val time = timeString?.substring(11,13)

        val hour = Hour(time, "$airTemp°", windSpeed, precipitation, icon, i, count)
        if(hourList.size < 6){
            hourList.add(hour)
        }
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
class Hour(
    val time: String?,
    val airTemp: String?,
    val windSpeed: Double?,
    val precipitation: Double?,
    val icon: String?,
    val timeIndex : Int,
    val hourNumber : Int
)
