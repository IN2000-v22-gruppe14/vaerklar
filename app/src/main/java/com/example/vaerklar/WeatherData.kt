package com.example.vaerklar

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
    val data: Data,
    val time: String
)

@Serializable
data class Units(
    val air_pressure_at_sea_level: String?,
    val air_temperature: String?,
    val air_temperature_max: String?,
    val air_temperature_min: String?,
    val cloud_area_fraction: String?,
    val cloud_area_fraction_high: String?,
    val cloud_area_fraction_low: String?,
    val cloud_area_fraction_medium: String?,
    val dew_point_temperature: String?,
    val fog_area_fraction: String?,
    val precipitation_amount: String?,
    val precipitation_amount_max: String?,
    val precipitation_amount_min: String?,
    val probability_of_precipitation: String?,
    val probability_of_thunder: String?,
    val relative_humidity: String?,
    val ultraviolet_index_clear_sky_max: String?,
    val wind_from_direction: String?,
    val wind_speed: String?,
    val wind_speed_of_gust: String?
)

@Serializable
data class Data(
    val instant: Instant,
    val next_12_hours: NextHours?,
    val next_1_hours: NextHours?,
    val next_6_hours: NextHours?
)

@Serializable
data class Instant(
    val details: ForecastTimeInstant?
)

@Serializable
data class NextHours(
    val details: ForecastTimePeriod?,
    val summary: ForecastSummary
)

@Serializable
data class ForecastTimeInstant(
    val air_pressure_at_sea_level: Double?,
    val air_temperature: Double?,
    val cloud_area_fraction: Double?,
    val cloud_area_fraction_high: Double?,
    val cloud_area_fraction_low: Double?,
    val cloud_area_fraction_medium: Double?,
    val dew_point_temperature: Double?,
    val fog_area_fraction: Double?,
    val relative_humidity: Double?,
    val wind_from_direction: Double?,
    val wind_speed: Double?,
    val wind_speed_of_gust: Double?
)

@Serializable
data class ForecastTimePeriod(
    val air_temperature_max: Double?,
    val air_temperature_min: Double?,
    val precipitation_amount: Double?,
    val precipitation_amount_max: Double?,
    val precipitation_amount_min: Double?,
    val probability_of_precipitation: Double?,
    val probability_of_thunder: Double?,
    val ultraviolet_index_clear_sky_max: Double?
)

@Serializable
data class ForecastSummary(
    val symbol_code: String
)
