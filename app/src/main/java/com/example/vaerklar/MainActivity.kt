package com.example.vaerklar

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.Window
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.vaerklar.data.WeatherData
import com.example.vaerklar.data.onboardPages
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
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModel: MainActivityViewModel by viewModels()
    private var locationName = "..."  // This is kind of redundant but eh fuck it
    var weatherData: WeatherData? = null
    private var lat: Double? = null
    private var lon: Double? = null
    private val showDialog = mutableStateOf(false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        requestWindowFeature(Window.FEATURE_ACTION_BAR)

        loadOnBoarding()

        // Make location data API request.
        viewModel.getLocationData().observe(this) {
            locationName = if (it != null) {
                it.data?.get(0)?.name.toString()  // Nydelig. elsker kotlin
            } else {
                "Blindern"  // Just in case :)
            }
        }

        viewModel.getWeatherData().observe(this) {
            weatherData = it
        }
    }

    override fun onStart() {
        // Runs when app is minimized and relaunches.
        // Is responsible for rendering UI as well as obtaining location permissions
        super.onStart()

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
                        val context = LocalContext.current
                        Theme {
                            // The scaffold is responsible for revealing the drawer.
                            val scaffoldState = rememberScaffoldState()
                            val scope = rememberCoroutineScope()
                            Scaffold (
                                scaffoldState = scaffoldState,
                                drawerBackgroundColor = Color(222, 254, 255),
                                drawerContent = {
                                    IconButton(
                                        onClick = {}
                                    ) {
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
                                            context.startActivity(Intent(context, SettingsActivity::class.java))
                                        }
                                    ) {
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
                                }
                            ) {
                                // Column responsible for the vertical stacking of all elements on the page.
                                Column {
                                    Box {
                                        OnBoardUi()
                                        if (showDialog.value) {
                                            saveOnboardingState()
                                            MainScreen(weatherData, locationName)
                                            NavigationBar(scaffoldState,scope )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                .start()
        }

        // Check if location has been passed from search. If so, set values
        val extras = intent.extras
        if (extras != null) {
            lat = extras.get("latitude") as Double?
            lon = extras.get("longitude") as Double?
        }

        // If gore but fuck it
        if (lat != null && lon != null) {
            // Why do I have to do this? I don't fucking know
            viewModel.fetchLocationData(lat!!, lon!!)
            viewModel.fetchWeatherData(lat!!, lon!!)
            return  // Return early to prevent checking permissions. Theoretically making it possible to use the app without permissions
        }

        // Ask For location permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), 0)
        } else {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, CancellationTokenSource().token)
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        viewModel.fetchLocationData(location.latitude, location.longitude)
                        viewModel.fetchWeatherData(location.latitude, location.longitude)
                    }
                }
        }
    }

    @SuppressLint("MissingPermission")  // Deprecated but it works :)
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
                    if (location != null) {
                        viewModel.fetchLocationData(location.latitude, location.longitude)
                        viewModel.fetchWeatherData(location.latitude, location.longitude)
                    }
                }
        } else {
            val toast = Toast.makeText(this, "Vennligst gi lokasjonstillatelse. Appen vil ellers ikke kjøre.", LENGTH_LONG)
            toast.show()
        }
    }

    @Composable
    // Navigation bar. Since it must be available on all screens, it is present in the MainActivity.
    fun NavigationBar(state: ScaffoldState, scope : CoroutineScope) {
        val context = LocalContext.current

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
                        "Meny",
                        tint = Color.White
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        context.startActivity(Intent(context, SearchActivity::class.java))
                    }) {

                    Icon(
                        Icons.Default.Search,
                        "Søk",
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
    }


    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun OnBoardUi(){
        val pagerstate = rememberPagerState(pageCount = 4)
        
        Column {
            Text(
                text = "Skip",
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(10.dp, 10.dp, 0.dp, 0.dp)
                    .clickable {showDialog.value = true})
            HorizontalPager(
                state = pagerstate,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)) {
                    page -> OnBoardPages(page = onboardPages[page])
            }
            HorizontalPagerIndicator(
                pagerState = pagerstate,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                activeColor = Color.White,
                inactiveColor = Color.Gray
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
                        text = "Kom i gang!"
                    )
                }
            }
            
        }
    }

    private fun saveOnboardingState() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val changer = sharedPreferences.edit()
        changer.apply{
            putBoolean("BOOLEAN_KEY", showDialog.value)
        }.apply()
    }

    private fun loadOnBoarding() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedBoolean = sharedPreferences.getBoolean("BOOLEAN_KEY", false)

        showDialog.value = savedBoolean
    }
}


