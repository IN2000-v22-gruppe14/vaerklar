package com.gruppe14_in2000_v22.vaerklar.data

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.coroutines.awaitString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

// This function gets the current starting index for fetching future weather data from the API
// NOTE: does not take time zones into account, meaning the app will display garbled data when
// viewing weather data for a location in a different time zone than your phone's
//
// A wise programmer once said: "don't touch datetime stuff", so we won't
// We haven't got the time for this shit anyway so deal with it!
fun getTimeSeriesIndex(weatherData: WeatherData?): Int {
    val updatedAt = weatherData?.properties?.meta?.updated_at
    val updateHour = LocalDateTime.parse(updatedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")).hour

    val firstHour = if (updateHour != 23) updateHour + 1 else 0

    val now = LocalDateTime.now()
    val nowHour = if (now.hour < firstHour) now.hour + 24 else now.hour

    return nowHour - firstHour + 1
}


// Draft for dealing with timezones, if we ever feel like attempting to fix the problem referenced above
/*
fun getTimeSeriesIndex(weatherData: WeatherData?): Int {
    val updatedAt = weatherData?.properties?.meta?.updated_at

    // Konverterer ISO string til dateteimeobjekt
    // Konverterer denne så til tidssone for Oslo
    println(TimeZone.getDefault().id)
    val updatedAtDateTime = LocalDateTime.parse(updatedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))
        .atZone(ZoneId.of("UTC"))
        .withZoneSameInstant(ZoneId.of(TimeZone.getDefault().id))

    val updateHour = updatedAtDateTime.hour
    val firstHour = if (updateHour != 23) updateHour + 1 else 0

    return updateHour - firstHour + 1
}
 */
