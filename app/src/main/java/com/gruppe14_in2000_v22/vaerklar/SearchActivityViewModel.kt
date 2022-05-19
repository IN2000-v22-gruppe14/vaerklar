package com.gruppe14_in2000_v22.vaerklar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruppe14_in2000_v22.vaerklar.data.DataSource
import com.gruppe14_in2000_v22.vaerklar.data.MeiliLocationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchActivityViewModel : ViewModel() {
    private val dataSource = DataSource()
    private var locationData: MutableLiveData<MeiliLocationData> = MutableLiveData()

    fun getLocations(): MutableLiveData<MeiliLocationData> {
        return locationData
    }

    fun fetchLocations(locationName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.getLocationMetaDataFromName(locationName).also {
                locationData.postValue(it)
            }
        }
    }
}
