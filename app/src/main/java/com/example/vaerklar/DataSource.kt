package com.example.vaerklar

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitString
import kotlinx.serialization.json.*
import kotlinx.serialization.decodeFromString
import java.lang.Exception

class DataSource {
    private val apiBase = "https://in2000-apiproxy.ifi.uio.no"
    private val frostApiBase = "https://frost.met.no"

    private val weatherCompleteUrl = "$apiBase/weatherapi/locationforecast/2.0/complete"
    private val locationUrl = "$frostApiBase/locations/v0.jsonld"

    suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherData? {
        try {
            val response = Fuel.get("$weatherCompleteUrl?lat=$latitude&lon=$longitude").awaitString()
            return Json.decodeFromString<WeatherData>(response)
        } catch(exception: Exception) {
            Log.e("DataSource", "Weather data request and deserialization failed!")
            Log.e("DataSource", exception.toString())
        }
        return null
    }

    suspend fun getLocationMetaData(latitude: Double, longitude: Double): LocationData? {
        try {
            val response = Fuel.get("$locationUrl?geometry=nearest(POINT($latitude $longitude))").awaitString()
            return Json.decodeFromString<LocationData>(response)
        } catch(exception: Exception) {
            Log.e("DataSource", "Weather data request and deserialization failed!")
            Log.e("DataSource", exception.toString())
        }
        return null
    }
}
