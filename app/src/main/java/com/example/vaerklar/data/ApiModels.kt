package com.example.vaerklar.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val geometry: PointGeometry,
    val properties: Forecast,
    val type: String
)

@Serializable
data class PointGeometry(
    val coordinates: List<Double>,
    val type: String
)

@Serializable
data class Forecast(
    val meta: Meta,
    val timeseries: List<ForecastTimeStep>
)

@Serializable
data class Meta(
    val units: Units,
    val updated_at: String
)

@Serializable
data class ForecastTimeStep(
    val data: ForecastData,
    val time: String
)

@Serializable
data class Units(
    val air_pressure_at_sea_level: String? = null,
    val air_temperature: String? = null,
    val air_temperature_max: String? = null,
    val air_temperature_min: String? = null,
    val air_temperature_percentile_10: String? = null,
    val air_temperature_percentile_90: String? = null,
    val cloud_area_fraction: String? = null,
    val cloud_area_fraction_high: String? = null,
    val cloud_area_fraction_low: String? = null,
    val cloud_area_fraction_medium: String? = null,
    val dew_point_temperature: String? = null,
    val fog_area_fraction: String? = null,
    val precipitation_amount: String? = null,
    val precipitation_amount_max: String? = null,
    val precipitation_amount_min: String? = null,
    val probability_of_precipitation: String? = null,
    val probability_of_thunder: String? = null,
    val relative_humidity: String? = null,
    val ultraviolet_index_clear_sky: String? = null,
    val wind_from_direction: String? = null,
    val wind_speed: String? = null,
    val wind_speed_of_gust: String? = null,
    val wind_speed_percentile_10: String? = null,
    val wind_speed_percentile_90: String? = null
)

@Serializable
data class ForecastData(
    val instant: Instant,
    val next_12_hours: Next12Hours? = null,
    val next_1_hours: Next1Hour? = null,
    val next_6_hours: Next6Hours? = null
)

@Serializable
data class Instant(
    val details: ForecastTimeInstant? = null
)

@Serializable
data class Next1Hour(
    val details: ForecastTimePeriod1Hour? = null,
    val summary: ForecastSummary
)

@Serializable
data class Next6Hours(
    val details: ForecastTimePeriod6Hours? = null,
    val summary: ForecastSummary
)

@Serializable
data class Next12Hours(
    val details: ForecastTimePeriod12Hours? = null,
    val summary: ForecastSummary12Hours
)

@Serializable
data class ForecastTimeInstant(
    val air_pressure_at_sea_level: Double? = null,
    val air_temperature: Double? = null,
    val air_temperature_percentile_10: Double? = null,
    val air_temperature_percentile_90: Double? = null,
    val cloud_area_fraction: Double? = null,
    val cloud_area_fraction_high: Double? = null,
    val cloud_area_fraction_low: Double? = null,
    val cloud_area_fraction_medium: Double? = null,
    val dew_point_temperature: Double? = null,
    val fog_area_fraction: Double? = null,
    val relative_humidity: Double? = null,
    val ultraviolet_index_clear_sky: Double? = null,
    val wind_from_direction: Double? = null,
    val wind_speed: Double? = null,
    val wind_speed_of_gust: Double? = null,
    val wind_speed_percentile_10: Double? = null,
    val wind_speed_percentile_90: Double? = null
)

@Serializable
data class ForecastTimePeriod1Hour(
    val precipitation_amount: Double? = null,
    val precipitation_amount_max: Double? = null,
    val precipitation_amount_min: Double? = null,
    val probability_of_precipitation: Double? = null,
    val probability_of_thunder: Double? = null
)

@Serializable
data class ForecastTimePeriod6Hours(
    val air_temperature_max: Double? = null,
    val air_temperature_min: Double? = null,
    val precipitation_amount: Double? = null,
    val precipitation_amount_max: Double? = null,
    val precipitation_amount_min: Double? = null,
    val probability_of_precipitation: Double? = null,
)

@Serializable
data class ForecastTimePeriod12Hours(
    val probability_of_precipitation: Double? = null
)

@Serializable
data class ForecastSummary(
    val symbol_code: String,
)

@Serializable
data class ForecastSummary12Hours(
    val symbol_code: String,
    val symbol_confidence: String? = null
)

@Serializable
data class LocationData(
    @SerialName("@context") val context: String? = null,
    @SerialName("@type") val type: String? = null,
    val apiVersion: String? = null,
    val license: String? = null,
    val createdAt: String? = null,
    val queryTime: Double? = null,
    val currentItemCount: Int? = null,
    val itemsPerPage: Int? = null,
    val offset: Int? = null,
    val totalItemCount: Int? = null,
    val nextLink: String? = null,
    val previousLink: String? = null,
    val currentLink: String? = null,
    val data: List<LocationMetaData?>? = null,
)

@Serializable
data class LocationMetaData(
    val name: String? = null,
    val feature: String? = null,
    val geometry: Geometry? = null
)

@Serializable
data class Geometry(
    @SerialName("@type") val type: String? = null,
    val coordinates: List<Double>? = null,
    val nearest: Boolean? = null,
)
