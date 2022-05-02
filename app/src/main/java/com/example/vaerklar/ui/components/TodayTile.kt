package com.example.vaerklar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.R
import com.example.vaerklar.data.WeatherData
import com.example.vaerklar.ui.theme.DayTile1
import com.example.vaerklar.ui.theme.DayTile2
import com.example.vaerklar.ui.theme.Rubik
import java.time.LocalDateTime

private var globalTileCounter = 0
var timeSeriesIndex = 0
val showDialog = mutableStateOf(false)
var globalHourNumber = 0
val hourList = mutableListOf<Hour>()


@Composable
fun PopUpScreen(weatherData: WeatherData?, hourNumber: Int){
    AlertDialog(

        backgroundColor = Color(0XFF23323c),

        shape = RoundedCornerShape(15.dp),


        title = {
            Column(
                Modifier
                    .fillMaxSize()
                    .size(200.dp, 100.dp)
                    .background(Color(0XFF23323c))
            ){ Box(
                Modifier
                    .fillMaxSize()

            ) {
                val avatar = Avatar()
                val piss = avatar.avatarMain(weatherData, "", hourList[globalHourNumber].timeIndex)
                var theString = ""
                for(i in piss){
                    theString += i.capitalize() + "\n"
                }
                Box(
                    Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter){

                    Text(
                        text = theString,
                        fontSize = 22.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                                .absolutePadding(40.dp, 50.dp, 50.dp, 0.dp)
                    )}
                }

            }
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd){
                TextButton(

                    colors =
                    ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),

                    onClick = { showDialog.value = false }) {
                    Text(
                        text = "X",
                        fontSize = 22.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold)

                }
            }
        },
        onDismissRequest = {

        },

        buttons = {

        }
        )

}

fun setTimeSeriesIndex(weatherData: WeatherData?){
    val updatedAt = weatherData?.properties?.meta?.updated_at
    val updateHour = updatedAt?.substring(11,13)
    //val updateMinute = updatedAt?.substring(14,16)
    val uhourInt = updateHour?.toInt()
    var firstHour = 0
    if (uhourInt != 23){
        firstHour = uhourInt?.plus(1)!!
    }

    val nowTime = LocalDateTime.now()
    val nowString = nowTime.toString()
    val nowHour = nowString.substring(11,13)
    val nowHourInt = nowHour.toInt()
    timeSeriesIndex = (nowHourInt - firstHour) + 1

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
            ){

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
    println(weatherData)
    if(showDialog.value){
        PopUpScreen(weatherData, globalHourNumber)
    }
    setTimeSeriesIndex(weatherData)

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
        hourList.add(hour)
    }

    val day = remember {hourList}

    Card(
        modifier = Modifier
            .height(175.dp)
            .padding(10.dp),
        backgroundColor = DayTile1,
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
                        TodayTileItem(hour = it, DayTile2)
                    }

                    else {
                        TodayTileItem(hour = it, DayTile1)
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
    val icon: String?,
    val timeIndex : Int,
    val hourNumber : Int
)

// Translates symbol strings from the API to icons provided in the application for display.
var iconTranslation = hashMapOf<String?, Int>(

    // CLEAR
    "clearsky_day" to R.drawable.clear_day,
    "clearsky_night" to R.drawable.clear_night,

    // CLOUDS
    "fair_day" to R.drawable.fair_day,
    "fair_night" to R.drawable.fair_night,

    "cloudy" to R.drawable.cloudy,
    "partlycloudy_day" to R.drawable.partly_cloudy_day,
    "partlycloudy_night" to R.drawable.partly_cloudy_night,

    // FOG
    "fog" to R.drawable.foggy,

    // RAIN
    "lightrain" to R.drawable.light_rain,
    "lightrainshowers_day" to R.drawable.light_rain_day,
    "lightrainshowers_night" to R.drawable.light_rain_night,
    "rain" to R.drawable.rain,
    "rainshowers_day" to R.drawable.rain_day,
    "rainshowers_night" to R.drawable.rain_night,
    "heavyrain" to R.drawable.heavy_rain,
    "heavyshowers_day" to R.drawable.heavy_rain_day,
    "heavyshowers_night" to R.drawable.heavy_rain_night,

    // SLEET
    "lightsleet" to R.drawable.light_sleet,
    "lightsleetshowers_day" to R.drawable.light_sleet_day,
    "lightsleetshowers_night" to R.drawable.light_sleet_night,
    "sleet" to R.drawable.sleet,
    "sleetshowers_day" to R.drawable.sleet_day,
    "sleetshowers_night" to R.drawable.sleet_night,
    "heavysleet" to R.drawable.heavy_sleet,
    "heavysleetshowers_day" to R.drawable.heavy_sleet_day,
    "heavysleetshowers_night" to R.drawable.heavy_sleet_night,

    // SNOW
    "lightsnow" to R.drawable.light_snow,
    "lightsnowshowers_day" to R.drawable.light_snow_day,
    "lightsnowshowers_night" to R.drawable.light_snow_night,
    "snow" to R.drawable.snow,
    "snowshowers_day" to R.drawable.snow_day,
    "snowshowers_night" to R.drawable.snow_night,
    "heavysnow" to R.drawable.heavy_snow,
    "heavysnowshowers_day" to R.drawable.heavy_snow_day,
    "heavysnowshowers_day" to R.drawable.heavy_snow_night,

    // THUNDER (RAIN, NO SLEET, SNOW)
    "" to R.drawable.light_rain_thunder,
    "" to R.drawable.light_rain_thunder_day,
    "" to R.drawable.light_rain_thunder_night,

    "" to R.drawable.rain_thunder,
    "" to R.drawable.rain_thunder_day,
    "" to R.drawable.rain_thunder_night,

    "" to R.drawable.heavy_rain_thunder,
    "" to R.drawable.heavy_rain_thunder_day,
    "" to R.drawable.heavy_rain_thunder_night,

    "" to R.drawable.light_snow_thunder,
    "" to R.drawable.light_snow_thunder_day,
    "" to R.drawable.light_snow_thunder_night,

    "" to R.drawable.snow_thunder,
    "" to R.drawable.snow_thunder_day,
    "" to R.drawable.snow_thunder_night,

    "" to R.drawable.heavy_snow_thunder,
    "" to R.drawable.heavy_snow_thunder_day,
    "" to R.drawable.heavy_snow_thunder_night,

    // BRUH
    null to R.drawable.partly_cloudy_day
)