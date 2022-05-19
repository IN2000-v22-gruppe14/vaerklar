package com.example.vaerklar.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.SettingsActivity
import com.example.vaerklar.data.TermsOfService
import com.example.vaerklar.ui.theme.Rubik


@Composable
fun ToSScreen() {
    val context = LocalContext.current
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = { context.startActivity(Intent(context, SettingsActivity::class.java)) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Tilbake",
                    modifier = Modifier.padding(20.dp) ,
                    color = Color(222, 254, 255),
                    fontFamily = Rubik
                )
            }
            Text(TermsOfService().terms,
                modifier = Modifier.fillMaxSize(),
                fontSize = 20.sp,
                color = Color(29, 58, 59),
                fontFamily = Rubik)
        }
    }
}