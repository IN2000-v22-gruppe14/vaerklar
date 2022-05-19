package com.gruppe14_in2000_v22.vaerklar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruppe14_in2000_v22.vaerklar.data.DataSource
import com.gruppe14_in2000_v22.vaerklar.data.LocationData
import com.gruppe14_in2000_v22.vaerklar.data.WeatherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val dataSource = DataSource()

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
            dataSource.getWeatherData(latitude, longitude).also {
                weatherData.postValue(it)
            }
        }
    }

    fun fetchLocationData(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.getLocationMetaData(latitude, longitude).also {
                locationData.postValue(it)
            }
        }
    }
}
