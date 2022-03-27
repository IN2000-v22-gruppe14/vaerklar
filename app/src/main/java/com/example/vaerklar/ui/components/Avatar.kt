package com.example.vaerklar.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.ui.theme.Rubik

@Preview
@Composable
// The avatar, alongside location.
fun Avatar() {

    // Row for arranging text and avatar.
    Column(
        modifier = Modifier
            .height(450.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // City.
        Text(
            text = "City",
            color = Color.White,
            fontFamily = Rubik,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier
                .absolutePadding(5.dp, 45.dp, 0.dp, 0.dp)
            // TODO: Place into row alongside pin icon with onClick. 
        )

        // Location within city.
        Text(
            text = "Location",
            color = Color.White,
            fontFamily = Rubik,
            fontWeight = FontWeight.Normal,
            fontSize = 25.sp,
        )
    }
}