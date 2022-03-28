package com.example.vaerklar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.data.ClothesAlgorithm
import com.example.vaerklar.databinding.ActivityMainBinding
import com.example.vaerklar.ui.screens.MainScreen
import com.example.vaerklar.ui.theme.VærklarTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchWeatherData()
        viewModel.fetchLocationData()

        val clothesAlgorithm = ClothesAlgorithm()

        viewModel.getWeatherData().observe(this) {
            val weatherScore = clothesAlgorithm.getWeatherScore(it)
            println(it)
        }

        viewModel.getLocationData().observe(this) {
            println(it)
        }
        //

        setContent {
            VærklarTheme {
                // The scaffold is responsible for revealing the drawer.
                Scaffold() {
                    val state = rememberScaffoldState()

                    // Column responsible for the vertical stacking of all elements on the page.
                    Column() {
                        Box() {
                            MainScreen()
                            NavigationBar(state)
                        }
                    }

                    // TODO: Allow the scaffold to update values based on state.
                }
            }
        }
    }

    @Composable
    // Navigation bar. Since it must be available on all screens, it is present in the MainActivity.
    fun NavigationBar(state: ScaffoldState) {
        val scope = rememberCoroutineScope()

        TopAppBar(
            title = {
                Text(
                    text = "",
                    fontSize = 15.sp,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                ) },
            navigationIcon = {
                IconButton(
                    onClick = {
                        scope.launch {
                            state.drawerState.open()
                        }
                    }) {

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
                .absolutePadding(0.dp,10.dp,0.dp,0.dp)
        )

        // TODO: Add functional onClick modifiers.
    }

    // The drawer, including application settings and further navigation to other pages.
    @Composable
    fun Drawer(state: ScaffoldState) {
        val scope = rememberCoroutineScope()
        // TODO: Create a drawer.
    }
}
