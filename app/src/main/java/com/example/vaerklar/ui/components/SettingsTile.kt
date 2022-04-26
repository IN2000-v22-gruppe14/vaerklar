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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.ui.theme.DayTile1
import com.example.vaerklar.ui.theme.DayTile2
import com.example.vaerklar.ui.theme.Rubik
import kotlin.math.roundToInt

@Preview
@Composable
//has all the tiles in the settingsscreen
fun SettingsTile() {
    //Base card to hold everything together
    Card(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = DayTile1,
    ) {

        //put all settings in a column to sort them downwards
        Column(
            //horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            // Card containing information.
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                backgroundColor = DayTile2,
                elevation = 0.dp
            ) {

                //Row for button to toggle theme
                Row(
                    modifier = Modifier
                        .absolutePadding(20.dp, 10.dp, 20.dp, 0.dp),
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

            //Row for button to toggle notifications
            Row(
                modifier = Modifier
                    .absolutePadding(20.dp, 0.dp, 20.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val notifyState = remember {
                    mutableStateOf(false)
                }

                Text (
                    text = "Varsler",
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    fontSize = 25.sp,
                    fontFamily = Rubik,
                )

                Switch (
                    checked = notifyState.value,
                    onCheckedChange = {notifyState.value = it; notifications(it)},
                    colors = SwitchDefaults.colors(Color.Green)
                )
            }

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
                    fontSize = 25.sp,
                    fontFamily = Rubik,
                )

                Switch (
                    checked = performanceState.value,
                    onCheckedChange = {performanceState.value = it; performanceMode(it)},
                    colors = SwitchDefaults.colors(Color.Green)
                )
            }

            //Column to make adjustments to the warmth of person
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                var sliderPosition by remember { mutableStateOf(0.5F)}
                //Shows value in int
                Text(
                    text = "hotness: ${(sliderPosition * 5).roundToInt()}",
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                //Slider to adjust hotness
                Slider(
                    value = sliderPosition,
                    onValueChange = {
                        sliderPosition = it;
                        adjustWarmth(it)
                    }
                )

                Text (
                    text = "Adjust hotness",
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }

            //Row to clear data
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                Button(
                    onClick = { clearData() },
                    shape = RoundedCornerShape(10.dp)
                ) {

                    Text (
                        text = "clear",
                        textAlign = TextAlign.Center,
                        color = Color.Black)
                }

                Text(
                    text = "Clear Data from device",
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }

            //Button to watch the terms and Service
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { termsAndService() },
                    shape = RoundedCornerShape(10.dp)
                ) {}

                Text(
                    text = "Terms and Service",
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
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