package com.example.vaerklar.ui.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.vaerklar.MainActivity
import com.example.vaerklar.data.LocationMetaData
import com.example.vaerklar.ui.theme.Rubik


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchResultItem(location: LocationMetaData) {
    val currentActivity = LocalContext.current as Activity

    if (location.name != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 4.dp, color = Color.Black),
            backgroundColor = Color.Gray,
            onClick = {
                println(location.geometry?.coordinates?.get(0))
                println(location.geometry?.coordinates?.get(1))
                val intent = Intent(currentActivity, MainActivity::class.java)
                intent.putExtra("longitude", location.geometry?.coordinates?.get(0))
                intent.putExtra("latitude", location.geometry?.coordinates?.get(1))
                // currentActivity.finish()
                startActivity(currentActivity, intent, null)

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