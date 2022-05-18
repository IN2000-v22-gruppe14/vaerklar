package com.example.vaerklar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vaerklar.data.DataSource
import com.example.vaerklar.data.LocationData
import com.example.vaerklar.data.WeatherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val ds = DataSource()

    private var weatherData: MutableLiveData<WeatherData?> = MutableLiveData()
    private var locationData: MutableLiveData<LocationData?> = MutableLiveData()

    fun getWeatherData(): MutableLiveData<WeatherData?> {
        return weatherData
    }

    fun getLocationData(): MutableLiveData<LocationData?> {
        return locationData
    }

    fun fetchWeatherData(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            ds.getWeatherData(latitude, longitude).also {
                weatherData.postValue(it)
            }
        }
    }

    fun fetchLocationData(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            ds.getLocationMetaData(latitude, longitude).also {
                locationData.postValue(it)
            }
        }
    }
}
