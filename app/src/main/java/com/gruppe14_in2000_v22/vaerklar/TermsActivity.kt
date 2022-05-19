package com.gruppe14_in2000_v22.vaerklar

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.gruppe14_in2000_v22.vaerklar.ui.screens.ToSScreen

class TermsActivity : ComponentActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        requestWindowFeature(Window.FEATURE_ACTION_BAR)

        setContent {
            ToSScreen()
        }
    }
}
