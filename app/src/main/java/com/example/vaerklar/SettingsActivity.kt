package com.example.vaerklar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.example.vaerklar.ui.screens.SettingsScreen


class SettingsActivity : ComponentActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SettingsScreen()
        }
    }
}