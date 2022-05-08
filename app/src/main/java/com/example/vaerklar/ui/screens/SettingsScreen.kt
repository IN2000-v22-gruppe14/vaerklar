package com.example.vaerklar.ui.screens

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import com.example.vaerklar.ui.theme.*
import kotlin.math.roundToInt


@Composable
fun SettingsScreen() {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = ClearDay
                    )
                )
        ) {
            Column {
                SettingsMenu()
            }
        }
    }
}

fun toggleTheme(darkmode: Boolean) {
    TODO("Toggle between light and dark theme")
}

fun notifications(notify: Boolean) {
    TODO("Toggles notifications onn and off")
}

fun performanceMode(performance: Boolean) {
    TODO("Toggles performance mode, saving battery")
}

fun adjustWarmth(hotness: Float) {
    val hot: Int = hotness.roundToInt()
    TODO("Make this adjust the recommended clothes")
}

fun clearData() {
    TODO("Clears all saved data the app has on you")
}

fun termsOfService() {
    TODO("Shows a page with terms of Service")
}

fun invite(activity: Activity) {
    val context = activity.applicationContext
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Simple text", "https://github.com/IN2000-v22-gruppe14/vaerklar")
    clipboard.setPrimaryClip(clip)
    Toast.makeText(context, "copied to clipboard", Toast.LENGTH_LONG).show()
}

fun helpAndSupport() {
    TODO("Sends the user to a help page")
}

@Preview
@Composable
fun SettingsMenu() {
    val activity = LocalContext.current as Activity
    //Base card to hold everything together
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = DayTile,
    ) {

        //put all settings in a column to sort them downwards
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
            //horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                modifier = Modifier
                    .absolutePadding(10.dp, 20.dp, 5.dp, 10.dp),
                text = "Instillinger",
                color = Color.White,
                fontSize = 25.sp,
                fontFamily = Rubik,
                fontWeight = FontWeight.Bold
            )

            // Card containing information on dark mode.
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(10.dp, 0.dp, 10.dp, 0.dp),
                backgroundColor = DayTileAlt,
                shape = RoundedCornerShape(15.dp),
                elevation = 0.dp
            ) {

                // Row for button to toggle theme.
                Row(
                    modifier = Modifier
                        .absolutePadding(20.dp, 0.dp, 20.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    val themeState = remember { mutableStateOf(false) }

                    Text(
                        text = "Mørkemodus",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = Rubik,
                    )

                    Switch(
                        checked = themeState.value,
                        onCheckedChange = { themeState.value = it; toggleTheme(it) },
                        colors = SwitchDefaults.colors(Color.Green)
                    )
                }
            }

            // Card containing information on notifications.
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(10.dp, 0.dp, 10.dp, 0.dp),
                backgroundColor = DayTileAlt,
                shape = RoundedCornerShape(15.dp),
                elevation = 0.dp
            ) {

                // Row for button to toggle theme.
                Row(
                    modifier = Modifier
                        .absolutePadding(20.dp, 0.dp, 20.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    val notifyState = remember {
                        mutableStateOf(false)
                    }

                    Text(
                        text = "Varsler",
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = Rubik,
                    )

                    Switch(
                        checked = notifyState.value,
                        onCheckedChange = { notifyState.value = it; notifications(it) },
                        colors = SwitchDefaults.colors(Color.Green)
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(10.dp, 0.dp, 10.dp, 0.dp),
                backgroundColor = DayTileAlt,
                shape = RoundedCornerShape(15.dp),
                elevation = 0.dp
            ) {
                //Column to make adjustments to the warmth of person
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    var sliderPosition by remember { mutableStateOf(0.5F)}

                    // Shows value in integer.
                    Text(
                        modifier = Modifier
                            .absolutePadding(0.dp, 5.dp, 0.dp, 0.dp),
                        text = "${(sliderPosition * 5).roundToInt()}",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = Rubik,
                        fontWeight = FontWeight.Bold,
                    )

                    // Slider to adjust hotness.
                    Slider(
                        colors = SliderDefaults.colors(
                            thumbColor = Color.White,
                            activeTrackColor = InactiveWhite,
                            inactiveTrackColor = DayTile
                        )
                        ,
                        value = sliderPosition,
                        onValueChange = {
                            sliderPosition = it
                            adjustWarmth(it)
                        }
                    )

                    Text (
                        modifier = Modifier
                            .absolutePadding(0.dp, 0.dp, 0.dp, 5.dp),
                        text = "Varmeskala",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = Rubik,
                    )
                }
            }


            // Terms of service.
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(10.dp, 0.dp, 10.dp, 0.dp),
                backgroundColor = DayTileAlt,
                shape = RoundedCornerShape(15.dp),
                elevation = 0.dp,
            ) {

                // Row for button to toggle theme.
                Row(
                    modifier = Modifier
                        .absolutePadding(20.dp, 5.dp, 20.dp, 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Slett data",
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = Rubik,
                    )

                    Button(
                        onClick = { clearData() },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White)
                    ) {

                        Text (
                            text = "",
                            textAlign = TextAlign.Center,
                            color = DayTile,
                            fontSize = 15.sp,
                            fontFamily = Rubik,
                        )
                    }
                }
            }

            // Terms of service.
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(10.dp, 0.dp, 10.dp, 0.dp),
                backgroundColor = DayTileAlt,
                shape = RoundedCornerShape(15.dp),
                elevation = 0.dp,
            ) {

                // Row for button to toggle theme.
                Row(
                    modifier = Modifier
                        .absolutePadding(20.dp, 5.dp, 20.dp, 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    val notifyState = remember {
                        mutableStateOf(false)
                    }

                    Text(
                        text = "Bruksvilkår",
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = Rubik,
                    )

                    Button(
                        onClick = { termsOfService() },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White)
                    ) {

                        Text (
                            text = "",
                            textAlign = TextAlign.Center,
                            color = DayTile,
                            fontSize = 15.sp,
                            fontFamily = Rubik,
                        )
                    }
                }
            }

            // Invites.
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(10.dp, 0.dp, 10.dp, 0.dp),
                backgroundColor = DayTileAlt,
                shape = RoundedCornerShape(15.dp),
                elevation = 0.dp,
            ) {

                Row(
                    modifier = Modifier
                        .absolutePadding(20.dp, 5.dp, 20.dp, 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    val notifyState = remember {
                        mutableStateOf(false)
                    }

                    Text(
                        text = "Inviter",
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = Rubik,
                    )

                    Button(
                        onClick = { invite(activity) },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White)
                    ) {

                        Text (
                            text = "",
                            textAlign = TextAlign.Center,
                            color = DayTile,
                            fontSize = 15.sp,
                            fontFamily = Rubik,
                        )
                    }
                }
            }

            // Help and support.
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(10.dp, 0.dp, 10.dp, 0.dp),
                backgroundColor = DayTileAlt,
                shape = RoundedCornerShape(15.dp),
                elevation = 0.dp,
            ) {

                // Row for button to toggle theme.
                Row(
                    modifier = Modifier
                        .absolutePadding(20.dp, 5.dp, 20.dp, 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    val notifyState = remember {
                        mutableStateOf(false)
                    }

                    Text(
                        text = "Støtte",
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = Rubik,
                    )

                    Button(
                        onClick = { termsOfService() },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White)
                    ) {

                        Text (
                            text = "",
                            textAlign = TextAlign.Center,
                            color = DayTile,
                            fontSize = 15.sp,
                            fontFamily = Rubik,
                        )
                    }
                }
            }
        }
    }
}