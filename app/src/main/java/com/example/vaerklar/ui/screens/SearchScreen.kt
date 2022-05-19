package com.example.vaerklar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.vaerklar.SearchActivityViewModel
import com.example.vaerklar.data.MeiliLocationMetaData
import com.example.vaerklar.ui.components.SearchResultItem
import com.example.vaerklar.ui.theme.CloudyDay
import com.example.vaerklar.ui.theme.DayTileAlt

@Composable
fun SearchScreen() {
    val searchFieldState = mutableStateOf("")
    val viewModel = SearchActivityViewModel()

    val locations = viewModel.getLocations().observeAsState()

    Scaffold {
        val focusRequester = remember { FocusRequester() }
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = CloudyDay
                    )
                )
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = DayTileAlt
                    )
            ) {
                TextField(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth(),
                    value = searchFieldState.value,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.White
                    ),
                    onValueChange = {
                        searchFieldState.value = it
                        viewModel.fetchLocations(searchFieldState.value)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search,
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                        }
                    ),
                )
            }
            Box {
                Column {
                    locations.value?.let { locations -> LocationsList(locations.hits) }
                }
            }
        }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}

@Composable
fun LocationsList(locations: List<MeiliLocationMetaData>) {
    val forcedLocationsUpdate = locations.map { it }  // This is wildly horrible but in order to force a rerender we need to do this apparently
    // val fuck = remember { locations }

    LazyColumn {
        items(
            items = forcedLocationsUpdate,
            itemContent = {
                SearchResultItem(it)
            }
        )
    }
}
