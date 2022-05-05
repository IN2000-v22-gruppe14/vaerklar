package com.example.vaerklar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.example.vaerklar.databinding.ActivityMainBinding
import com.example.vaerklar.ui.screens.SearchScreen


class SearchActivity : ComponentActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SearchScreen()
        }
    }
}
