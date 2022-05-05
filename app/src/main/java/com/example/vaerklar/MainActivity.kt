package com.example.vaerklar

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.vaerklar.data.WeatherData
import com.example.vaerklar.data.onboardPages
import com.example.vaerklar.databinding.ActivityMainBinding
import com.example.vaerklar.ui.screens.MainScreen
import com.example.vaerklar.ui.screens.OnBoardPages
import com.example.vaerklar.ui.theme.Rubik
import com.example.vaerklar.ui.theme.Theme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
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
    private val showDialog = mutableStateOf(false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        // Acquire location data.
        var locationName = "..."
        viewModel.getLocationData().observe(this) {
            locationName = if (it != null) {
                it.data?.get(0)?.name.toString()  // Nydelig. elsker kotlin
            } else {
                "Blindern"
            }
        }

        // Acquire weather data.
        var weatherData: WeatherData? = null
        viewModel.getWeatherData().observe(this) {
            weatherData = it
        }

        // Run splash screen
        // https://github.com/tgloureiro/animated_splashscreen_android/blob/step4_core_splash_jetpack/app/src/main/java/com/example/animatedsplashscreen/MainActivity.kt
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { weatherData == null } // Splash screen continues running until weatherData is set.

        // Splash screen exits when weatherData is set, running setContent.
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            splashScreenViewProvider.iconView
                .animate()
                .alpha(0f)
                .setStartDelay(500.toLong())
                .withEndAction {
                    splashScreenViewProvider.remove()

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
                            ) {

                                // Column responsible for the vertical stacking of all elements on the page.
                                Column {
                                    Box {
                                        OnBoardUi()
                                        if(showDialog.value == true){
                                            MainScreen(weatherData, locationName)
                                            NavigationBar(scaffoldState,scope )
                                        }
                                            //MainScreen(weatherData, locationName)
                                            //NavigationBar(scaffoldState,scope )

                                    }
                                }
                            }
                        }
                    }
                }
                .start()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), 0)
        }

        else {
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

        else {
            val toast = Toast.makeText(this, "Vennligst gi lokasjonstillatelse. Appen vil ellers ikke kjøre.", LENGTH_LONG)
            toast.show()
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


    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun OnBoardUi(){
        val pagerstate = rememberPagerState(pageCount = 4)
        
        Column(){
            Text(
                text = "Skip",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clickable {})

            HorizontalPager(
                state = pagerstate,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)) {

                    page ->
                OnBoardPages(page = onboardPages[page])
            }

            HorizontalPagerIndicator(
                pagerState = pagerstate,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                activeColor = Color.Blue
            )
            
            AnimatedVisibility(visible = pagerstate.currentPage == 3) {
                OutlinedButton(
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    onClick = {showDialog.value = true},
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = colorResource(
                            id = R.color.day_tile_1))) {

                    Text(
                        text = "La oss starte"
                    )
                    }

            }
            
        }
    }
}
