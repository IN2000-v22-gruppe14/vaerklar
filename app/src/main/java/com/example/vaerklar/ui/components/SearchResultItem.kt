package com.example.vaerklar.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.data.LocationMetaData
import com.example.vaerklar.ui.theme.Rubik


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchResultItem(location: LocationMetaData) {
    if (location.name != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 4.dp, color = Color.Black),
            backgroundColor = Color.Gray,
            onClick = {
                println(location.geometry?.coordinates?.get(0))
                println(location.geometry?.coordinates?.get(1))
            }
        ) {
            Column {
                Text(
                    text = location.name,
                    Modifier.padding(16.dp),
                    color = Color.Black,
                    fontFamily = Rubik,
                    fontSize = 24.sp,
                )
            }
            Column {
                Text(
                    text = location.geometry?.coordinates?.get(0).toString() + ", " + location.geometry?.coordinates?.get(1).toString(),
                    Modifier.padding(16.dp),
                    color = Color.Black,
                    fontFamily = Rubik,
                    fontSize = 12.sp,
                )
            }
        }
    }
}