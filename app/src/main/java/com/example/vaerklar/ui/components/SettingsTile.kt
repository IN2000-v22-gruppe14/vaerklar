package com.example.vaerklar.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.ui.theme.DayTile
import com.example.vaerklar.ui.theme.DayTileAlt
import com.example.vaerklar.ui.theme.InactiveWhite
import com.example.vaerklar.ui.theme.Rubik
import kotlin.math.roundToInt

@Preview
@Composable
//has all the tiles in the settingsscreen
fun SettingsTile() {
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

            // Card containing information on performance.
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(10.dp, 0.dp, 10.dp, 0.dp),
                backgroundColor = DayTileAlt,
                shape = RoundedCornerShape(15.dp),
                elevation = 0.dp
            ) {

                //Row for button to toggle performance mode
                Row(
                    modifier = Modifier
                        .absolutePadding(20.dp, 0.dp, 20.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val performanceState = remember {
                        mutableStateOf(false)
                    }

                    Text (
                        text = "Ytelsesmodus",
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = Rubik,
                    )

                    Switch (
                        checked = performanceState.value,
                        onCheckedChange = {performanceState.value = it; performanceMode(it)},
                        colors = SwitchDefaults.colors(
                            uncheckedThumbColor = Color.White,
                            checkedThumbColor = Color.White,
                            uncheckedTrackColor = DayTile,
                            checkedTrackColor = InactiveWhite
                        )
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
                        text = "Slett data",
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = Rubik,
                    )

                    Button(
                        onClick = { clearData() },
                        shape = RoundedCornerShape(10.dp)
                    ) {

                        Text (
                            text = "Slett",
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
                            text = "Les",
                            textAlign = TextAlign.Center,
                            color = DayTile,
                            fontSize = 15.sp,
                            fontFamily = Rubik,
                        )
                    }
                }
            }

            //Button to invite people to download the app
            Button(
                onClick = { invite() },
                shape = RoundedCornerShape(10.dp)
            ) {

                Text(
                    text = "INVITE FRIENDS",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                )
            }

            //Button for help and support
            ClickableText(
                text = AnnotatedString("Help and Support"),
                onClick = { helpAndSupport() }
            )
        }
    }
}