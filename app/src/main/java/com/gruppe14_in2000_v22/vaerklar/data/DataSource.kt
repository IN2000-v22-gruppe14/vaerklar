package com.gruppe14_in2000_v22.vaerklar.data

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.coroutines.awaitString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.time.LocalDateTime

class DataSource {

    // TODO: Specify in local.properties

    // This is shit practice but does it look like I care?
    private val apiClient = "2b35fe20-c789-4ce5-ab4c-34de69f50d22"
    private val apiSecret = "708495ee-4757-4894-9316-b7b730576b6b"

    private val apiBase = "https://in2000-apiproxy.ifi.uio.no"
    private val frostApiBase = "https://frost.met.no"
    private val meiliBase = "https://meili.lblend.moe"

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
            val response = Fuel.get("$locationUrl?geometry=nearest(POINT($longitude $latitude))")
                .authentication()
                .basic(apiClient, apiSecret)
                .awaitString()
            return Json.decodeFromString<LocationData>(response)
        } catch(exception: Exception) {
            Log.e("DataSource", "Weather data request and deserialization failed!")
            Log.e("DataSource", exception.toString())
        }
        return null
    }

    suspend fun getLocationMetaDataFromName(locationName: String): MeiliLocationData? {
        try {
            val response =
                Fuel.get("$meiliBase/indexes/locations/search", listOf("q" to locationName, "limit" to 10))
                    .awaitString()
            return Json.decodeFromString<MeiliLocationData>(response)
        } catch (exception: Exception) {
            Log.e("DataSource", "MeiliSearch request failed!")
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
    if (uhourInt != 23) {
        firstHour = uhourInt?.plus(1)!!
    }

    val nowTime = LocalDateTime.now()
    val nowString = nowTime.toString()
    val nowHour = nowString.substring(11,13)
    var nowHourInt = nowHour.toInt()

    if (nowHourInt < firstHour) {
        val oldHour = nowHourInt
        nowHourInt = (24 + oldHour)
    }
    return nowHourInt - firstHour + 1
}
