package com.example.vaerklar.ui.screens

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.core.content.res.TypedArrayUtils.getText
import com.example.vaerklar.data.Notifications
import com.example.vaerklar.data.TermsOfService
import com.example.vaerklar.ui.theme.*
import kotlin.math.roundToInt
import com.example.vaerklar.ui.screens.notifications as notifications1


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

@Composable
fun notifications(notify: Boolean) {
    if (notify) Notifications(LocalContext.current).setNotification()
}

fun adjustWarmth(hotness: Float) {
    val hot: Int = hotness.roundToInt()
    TODO("Make this adjust the recommended clothes")
}

fun termsOfService(){
    //lol
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
    val openWindow = remember { mutableStateOf(false)}
    //Base card to hold everything together
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = DayTile,
    ) {

        if(openWindow.value) {
            AlertDialog(
                modifier = Modifier.fillMaxSize(),
                onDismissRequest = { openWindow.value = false },
                title = {
                    Text(text = "Bruksvilkår")
                },
                text = {
                    Text(TermsOfService().getText())
                },
                buttons = {
                    Row(
                        modifier = Modifier.padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { openWindow.value = false }
                        ) {
                            Text("Dismiss")
                        }
                    }
                }
            )
        }

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
                        onCheckedChange = { notifyState.value = it; },
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
                        onClick = { openWindow.value != openWindow.value },
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