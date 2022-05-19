package com.gruppe14_in2000_v22.vaerklar.ui.components

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
import com.gruppe14_in2000_v22.vaerklar.MainActivity
import com.gruppe14_in2000_v22.vaerklar.data.MeiliLocationMetaData
import com.gruppe14_in2000_v22.vaerklar.ui.theme.DayTile
import com.gruppe14_in2000_v22.vaerklar.ui.theme.Rubik


@OptIn(ExperimentalMaterialApi::class)  // Because everything, and I mean EVERYTHING is deprecated in the Android ecosystem
@Composable
fun SearchResultItem(location: MeiliLocationMetaData) {
    val currentActivity = LocalContext.current as Activity

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = DayTile,
        onClick = {
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
