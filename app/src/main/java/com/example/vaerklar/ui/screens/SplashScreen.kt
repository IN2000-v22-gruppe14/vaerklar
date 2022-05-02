package com.example.vaerklar.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vaerklar.R
import com.example.vaerklar.ui.theme.DayTile

@Preview
@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .background(color = DayTile)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp),
            painter = painterResource(id = R.drawable.mark),
            contentDescription = "Splash icon.",
            tint = Color.White
        )
    }
}

@Composable
fun Splash() {

}
