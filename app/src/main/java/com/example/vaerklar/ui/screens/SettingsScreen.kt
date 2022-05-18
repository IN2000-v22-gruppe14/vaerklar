package com.example.vaerklar.ui.screens

import android.app.Activity
import android.content.*
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
import com.example.vaerklar.TermsActivity
import com.example.vaerklar.data.TermsOfService
import com.example.vaerklar.ui.theme.*
import kotlin.math.roundToInt


private var tempSens = 0

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

fun getWarmth(): Int {
    return tempSens
}

fun invite(activity: Activity) {
    val context = activity.applicationContext
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Github repo link", "https://github.com/IN2000-v22-gruppe14/vaerklar")
    clipboard.setPrimaryClip(clip)
    Toast.makeText(context, "Kopierte til utklippstavla!", Toast.LENGTH_LONG).show()
}

@Preview
@Composable
fun SettingsMenu() {
    val activity = LocalContext.current as Activity
    val openWindow = remember { mutableStateOf(false) }
    //Base card to hold everything together
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = DayTile,
    ) {

        if (openWindow.value) {
            AlertDialog(
                modifier = Modifier.fillMaxSize(),
                onDismissRequest = { openWindow.value = false },
                title = {
                    Text(text = "Bruksvilkår")
                },
                text = {
                    Text(TermsOfService().terms)
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
                            Text("Lukk")
                        }
                    }
                }
            )
        }

        // Put all settings in a column to sort them downwards
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
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
                    val sharedPrefs : SharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
                    val sliderVal = sharedPrefs.getFloat("sliderVal", 0.5F)
                    var sliderPosition by remember { mutableStateOf(sliderVal) }

                    // Shows value in integer.
                    Text(
                        modifier = Modifier
                            .absolutePadding(0.dp, 5.dp, 0.dp, 0.dp),
                        text = "${(sliderPosition * 6).roundToInt() - 3}",
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
                            tempSens = (sliderPosition * 6).toInt()
                            sharedPrefs.edit().putFloat("sliderVal", sliderPosition).apply()
                        },
                        steps = 5
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
                Row(
                    modifier = Modifier
                        .absolutePadding(20.dp, 5.dp, 20.dp, 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Bruksvilkår",
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = Rubik,
                    )
                    Button(
                        onClick = { activity.startActivity(Intent(activity, TermsActivity::class.java)) },
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
        }
    }
}