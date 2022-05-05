package com.example.vaerklar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vaerklar.data.DataSource
import com.example.vaerklar.data.LocationMetaData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchActivityViewModel : ViewModel() {
    private val ds = DataSource()
    private var locationData: MutableLiveData<List<LocationMetaData?>> = MutableLiveData()

    fun getLocations(): MutableLiveData<List<LocationMetaData?>> {
        return locationData
    }

    fun fetchLocations(locationName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            ds.getLocationMetaDataFromName(locationName).also {
                locationData.postValue(it)
            }
        }
    }
}
