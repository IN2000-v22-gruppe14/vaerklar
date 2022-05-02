package com.example.vaerklar

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.vaerklar.data.ClothesAlgorithm
import com.example.vaerklar.data.WeatherData
import com.example.vaerklar.databinding.ActivityMainBinding
import com.example.vaerklar.ui.screens.MainScreen
import com.example.vaerklar.ui.screens.SplashScreen
import com.example.vaerklar.ui.theme.Rubik
import com.example.vaerklar.ui.theme.Theme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)


       // setContentView(binding.root)

        setContent {
            Theme {
                // The scaffold is responsible for revealing the drawer.

                Scaffold {
                    // Column responsible for the vertical stacking of all elements on the page.
                    Column() {
                        Box {
                            SplashScreen()
                        }
                    }
                }
            }
        }

        var locationName = "..."
        viewModel.getLocationData().observe(this) {
            if (it != null) {
                locationName = it.data?.get(0)?.name.toString()  // Nydelig. elsker kotlin
            }
        }

        val clothesAlgorithm = ClothesAlgorithm()
        var weatherData: WeatherData? = null
        viewModel.getWeatherData().observe(this) {
            val weatherScore = clothesAlgorithm.getWeatherScore(it)
            weatherData = it
        }

        println("ONCREATE (MainActivity): Appen starter rendering.")
        Handler(Looper.getMainLooper()).postDelayed({
            setContent {
                Theme {
                    // The scaffold is responsible for revealing the drawer.
                    val scaffoldState = rememberScaffoldState()
                    val scope = rememberCoroutineScope()
                    Scaffold (
                        scaffoldState = scaffoldState,
                        drawerBackgroundColor = Color(222, 254, 255),
                        drawerContent = {
                            IconButton(
                                onClick = {
                                }
                            ){
                                Text("     Hjem",
                                    modifier = Modifier.padding(20.dp),
                                    color = Color(29, 58, 59),
                                    fontFamily = Rubik
                                )
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Hjem",
                                    modifier = Modifier.padding(end = 48.dp),
                                    tint = Color(29, 58, 59)
                                )
                            }

                            Divider()
                            IconButton(
                                onClick = {
                                }
                            ){
                                Text("     Innstillinger",
                                    modifier = Modifier.padding(20.dp),
                                    color = Color(29, 58, 59),
                                    fontFamily = Rubik
                                )
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Innstillinger",
                                    modifier = Modifier.padding(end = 94.dp),
                                    tint = Color(29, 58, 59)
                                )
                            }

                            Divider()
                            // Drawer items
                        }
                        )
                        {
                        val state = rememberScaffoldState()

                        // Column responsible for the vertical stacking of all elements on the page.
                        Column() {
                            Box() {
                                MainScreen(weatherData, locationName)
                                NavigationBar(scaffoldState,scope )
                            }
                        }
                    }
                }
            }
        }, 1000)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), 0);
        } else {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, CancellationTokenSource().token)
                .addOnSuccessListener { location: Location? ->
                    println("ONREQUESTPERMISSIONSRESULT (MainActivity): Enhetens lokasjon er funnet.")

                    if (location != null) {
                        println("ONREQUESTPERMISSIONSRESULT (MainActivity): Enhetens lokasjon er ikke null.")
                        println("Enhetens gitte breddegrad/latitude er " + location.latitude)
                        println("Enhetens gitte lengdegrad/longitude er " + location.longitude)
                        viewModel.fetchLocationData(location.latitude, location.longitude)
                        viewModel.fetchWeatherData(location.latitude, location.longitude)
                    }
                }
        }
    }



    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, CancellationTokenSource().token)
                .addOnSuccessListener { location: Location? ->
                    println("ONREQUESTPERMISSIONSRESULT (MainActivity): Enhetens lokasjon er funnet.")

                    if (location != null) {
                        println("ONREQUESTPERMISSIONSRESULT (MainActivity): Enhetens lokasjon er ikke null.")
                        println("Enhetens gitte breddegrad/latitude er " + location.latitude)
                        println("Enhetens gitte lengdegrad/longitude er " + location.longitude)
                        viewModel.fetchLocationData(location.latitude, location.longitude)
                        viewModel.fetchWeatherData(location.latitude, location.longitude)
                    }
                }
        }
        // HAMBURGER GREIER VVVV
        //setContentView(R.layout.nav_activity_main)
    }

    @Composable
    // Navigation bar. Since it must be available on all screens, it is present in the MainActivity.
    fun NavigationBar(state: ScaffoldState, scope : CoroutineScope) {
        //scope = rememberCoroutineScope()

        TopAppBar(
            title = {
                Text(
                    text = "",
                    fontSize = 15.sp,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        scope.launch {
                            state.drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                    ) {

                    Icon(
                        Icons.Default.Menu,
                        "Hamburger",
                        tint = Color.White
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        scope.launch {
                        }
                    }) {

                    Icon(
                        Icons.Default.Search,
                        "Hamburger",
                        tint = Color.White
                    )
                }
            },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(0.dp, 10.dp, 0.dp, 0.dp)
        )

        // TODO: Add functional onClick modifiers.
    }


}