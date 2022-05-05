package com.example.vaerklar.ui.screens

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.data.Page



@Composable
fun OnBoardPages(page : Page){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            modifier = Modifier.height(250.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = page.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold)

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = page.description,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold)

        Spacer(
            modifier = Modifier.height(8.dp)
        )

    }
}

