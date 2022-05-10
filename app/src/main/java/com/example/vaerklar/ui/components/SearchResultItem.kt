package com.example.vaerklar.ui.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.vaerklar.MainActivity
import com.example.vaerklar.data.MeiliLocationMetaData
import com.example.vaerklar.ui.theme.DayTile
import com.example.vaerklar.ui.theme.Rubik


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchResultItem(location: MeiliLocationMetaData) {
    val currentActivity = LocalContext.current as Activity

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = DayTile,
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
                color = Color.White,
                fontFamily = Rubik,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .absolutePadding(16.dp, 16.dp, 16.dp, 2.dp)
            )
            Text(
                text = location.geometry?.coordinates?.get(0)
                    .toString() + ", " + location.geometry?.coordinates?.get(1).toString(),
                color = Color.LightGray,
                fontFamily = Rubik,
                fontSize = 12.sp,
                modifier = Modifier
                    .absolutePadding(16.dp, 2.dp, 16.dp, 16.dp)
            )
        }
    }
}