package com.example.vaerklar.data

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.coroutines.awaitString
import kotlinx.serialization.json.*
import kotlinx.serialization.decodeFromString
import java.lang.Exception
import java.time.LocalDateTime

class DataSource {

    // Dette er en uting, men jeg gir så faen. yeet yeet
    private val apiClient = "2b35fe20-c789-4ce5-ab4c-34de69f50d22"
    private val apiSecret = "708495ee-4757-4894-9316-b7b730576b6b"

    private val apiBase = "https://in2000-apiproxy.ifi.uio.no"
    private val frostApiBase = "https://frost.met.no"

    private val weatherCompleteUrl = "$apiBase/weatherapi/locationforecast/2.0/complete"
    private val locationUrl = "$frostApiBase/locations/v0.jsonld"

    suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherData? {
        try {
            val response = Fuel.get("$weatherCompleteUrl?lat=$latitude&lon=$longitude").awaitString()
            println(response)
            return Json.decodeFromString<WeatherData>(response)
        } catch(exception: Exception) {
            Log.e("DataSource", "Weather data request and deserialization failed!")
            Log.e("DataSource", exception.toString())
        }
        return null
    }

    suspend fun getLocationMetaData(latitude: Double, longitude: Double): LocationData? {
        try {
            //apikallet her er på formatet long, lat. for ryddighetens skyld har jeg reversert det i kallet men funksjonen tar imot på "riktig" format
            val response = Fuel.get("$locationUrl?geometry=nearest(POINT($longitude $latitude))")
                .authentication()
                .basic(apiClient, apiSecret)
                .awaitString()
            println(response)
            return Json.decodeFromString<LocationData>(response)
        } catch(exception: Exception) {
            Log.e("DataSource", "Weather data request and deserialization failed!")
            Log.e("DataSource", exception.toString())
        }
        return null
    }
}

fun getTimeSeriesIndex(weatherData: WeatherData?): Int {
    val updatedAt = weatherData?.properties?.meta?.updated_at
    val updateHour = updatedAt?.substring(11,13)

    val uhourInt = updateHour?.toInt()
    var firstHour = 0
    if(uhourInt != 23){
        firstHour = uhourInt?.plus(1)!!
    }

    val nowTime = LocalDateTime.now()
    val nowString = nowTime.toString()
    val nowHour = nowString.substring(11,13)
    val nowHourInt = nowHour.toInt()
    return nowHourInt - firstHour + 1
}
