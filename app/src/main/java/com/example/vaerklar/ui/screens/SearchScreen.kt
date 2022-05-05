package com.example.vaerklar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.vaerklar.SearchActivityViewModel
import com.example.vaerklar.data.LocationMetaData
import com.example.vaerklar.ui.components.SearchResultItem

@Composable
fun SearchScreen() {
    var state = mutableStateOf("")
    val viewModel = SearchActivityViewModel()

    Scaffold {
        val focusRequester = remember { FocusRequester() }
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = White
                    )
            ) {
                Row {
                    Column {
                        TextField(
                            modifier = Modifier
                                .focusRequester(focusRequester)
                                .fillMaxWidth(),
                            value = state.value,
                            onValueChange = { state.value = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Search,
                            ),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    viewModel.fetchLocations(state.value)
                                }
                            ),
                        )
                    }
                }
            }
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
            Box {
                Row {
                    Column {
                        if (viewModel.getLocations().value != null) {
                            LocationsList(locations = viewModel.getLocations().value!!)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LocationsList(locations: List<LocationMetaData?>) {
    val yeet = remember { locations }
    LazyColumn {
        items(
            items = yeet,
            itemContent = {
                if (it != null) {
                    SearchResultItem(location = it)
                }
            }
        )
    }
}


