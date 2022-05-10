package com.example.vaerklar

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vaerklar.ui.screens.SearchScreen


class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        requestWindowFeature(Window.FEATURE_ACTION_BAR)

        setContent {
            SearchScreen()
        }
    }
}
