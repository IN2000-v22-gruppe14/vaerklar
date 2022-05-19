package com.example.vaerklar.data

import com.example.vaerklar.ui.screens.getWarmth
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToLong
import kotlin.math.sqrt


class ClothesAlgorithm {
    private var timeSeriesIndex = 0
    private var realtemperature = 0.0

    // TODO: make use of enums?
    private var head = hashMapOf(
        listOf(-100,0) to "lue",
        listOf(1,10) to "pannebånd",
        listOf(11,24) to "",
        listOf(25,50) to "solhatt"
    )

    private var body = hashMapOf(
        listOf(-100,-1) to "ullgenser",
        listOf(0,15) to "genser",
        // listOf(20,50) to "topp",
        listOf(16,50) to "tskjorte"
    )

    private var jacket = hashMapOf(
        listOf(-100,4) to "vinterjakke",
        listOf(5,10) to "jakke",
        listOf(11,100) to ""
    )

    private var lowerBody = hashMapOf(
        listOf(-100,-11) to "utebukse",
        listOf(-10,22) to "bukse",
        listOf(23,50) to "shorts"
    )

    private var shoe = hashMapOf(
        listOf(-100,4) to "vintersko",
        listOf(5,25) to "sneakers",
        listOf(26,50) to "sandaler"
    )

    private var special = hashMapOf(
        0 to "solbriller",
        1 to "votter & skjerf",
        2 to "paraply"
    )

    private var rainClothing = hashMapOf(
        0 to "regnhatt",
        1 to "_DETTE SKAL IKKE SKJE_",  // because we're too lazy to do this properly
        2 to "regnjakke",
        3 to "regnbukse",
        4 to "gummistøvler"
    )

    private var pieceIndexToPieceMap = listOf(
        head,
        body,
        jacket,
        lowerBody,
        shoe
    )

    // Returns the amount of degrees (fahrenheit) to adjust tempereature by
    // when taking the user's set temperature sensitivity into account
    private fun getTemperatureSensitivityDiff(adjuster: Int): Int {
        return when (adjuster) {
            0 -> 6
            1 -> 4
            2 -> 2
            3 -> 0
            4 -> -2
            5 -> -4
            6 -> -6
            else -> 0
        }
    }

    fun getWeatherScore(weatherData: WeatherData?, index: Int): MutableList<String> {
        timeSeriesIndex = index

        val airTemp =
            weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.air_temperature
        val fahrenheitTemp: Double? = if (airTemp != null) ((airTemp * 9) / 5) + 32 else null
        val windSpeed =
            weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.wind_speed
        val humidity =
            weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.relative_humidity

        var realTemp = fahrenheitTemp ?: -273.15  // Set a ridiculous fallback value. Not optimal but eh

        // Yoinked from
        // https://www.wpc.ncep.noaa.gov/html/heatindex_equation.shtml
        // https://hjelp.yr.no/hc/no/articles/360001695513-Effektiv-temperatur-og-f%C3%B8les-som-
        if (windSpeed != null && humidity != null && fahrenheitTemp != null) {
            if (fahrenheitTemp <= 50 && windSpeed >= 3.00) {
                realTemp =
                    35.74 + (0.6215 * fahrenheitTemp) - 35.75 * (windSpeed.pow(0.16)) + ((0.4275 * fahrenheitTemp) * (windSpeed.pow(
                        0.16
                    )))
            }

            if (fahrenheitTemp == realTemp && fahrenheitTemp >= 80) {
                realTemp =
                    0.5 * (fahrenheitTemp + 61.0 + ((fahrenheitTemp - 68.0) * 1.2) + (humidity * 0.094))
                if (realTemp >= 80) {
                    realTemp =
                        -42.379 + 2.04901523 * fahrenheitTemp + 10.14333127 * humidity - .22475541 * fahrenheitTemp * humidity - .00683783 * fahrenheitTemp * fahrenheitTemp - .05481717 * humidity * fahrenheitTemp + .00122874 * fahrenheitTemp * fahrenheitTemp * humidity + .00085282 * fahrenheitTemp * humidity * humidity - .00000199 * fahrenheitTemp * fahrenheitTemp * humidity * humidity

                    if (humidity < 13 && fahrenheitTemp >= 80 && fahrenheitTemp <= 112) {
                        realTemp -= ((13 - humidity) / 4) * sqrt((17 - abs(fahrenheitTemp - 95)) / 17)
                        if (humidity > 85 && fahrenheitTemp >= 80 && fahrenheitTemp <= 87) {
                            realTemp += ((humidity - 85) / 10) * ((87 - fahrenheitTemp) / 5)
                        }
                    }
                }
            }

            realTemp = (realTemp - 32) * 0.5556
            realtemperature = realTemp
        }

        // Adjust temperature based on the user's set temperature sensitivty level
        val tempSensitivitySetting = getWarmth()
        val diff = getTemperatureSensitivityDiff(tempSensitivitySetting)
        realTemp += (diff)

        return getOutfit(weatherData, realTemp.toFloat())
    }

    private fun getOutfit(weatherData: WeatherData?, realTemp: Float?): MutableList<String> {
        val realTempRound = (realTemp?.roundToLong()?.toFloat())
        val outfitList = mutableListOf<String>()
        val rain = rainAmount(weatherData)

        for (i in 0..4) {
            outfitList.add(getPiece(i, rain, realTempRound))
        }
        outfitList.add(getSpecial(realTempRound, weatherData))

        return outfitList
    }

    // Returns a string representation of the recommended piece of clothing based on temperature and rain
    private fun getPiece(pieceType: Int, rain: Int, realTemp: Float?): String {
        /*
            0 to headwear
            1 to sweater, t-shirt etc
            2 to jacket
            3 to pants
            4 to shoes
        */
        return if (pieceType != 1 && rain == 10) {  // If not a upper-body piece and lots of rain
            rainClothing.getValue(pieceType)
        } else if ((pieceType == 2 || pieceType == 4) && rain == 5) {  // If requesting pants or shoes and there's some rain
            rainClothing.getValue(pieceType)
        } else if (pieceType == 2 && rain == 3) {  // Light rain. Raincoat
            rainClothing.getValue(pieceType)
        } else {  // No weather-specific outfit needed
            getNormalClothing(pieceType, realTemp)
        }
    }

    // Returns a string representation of non weather specific clothing items based on temperature
    private fun getNormalClothing(pieceType: Int, realTemp: Float?): String {
        /*
            0 to headwear
            1 to sweater, t-shirt etc
            2 to jacket
            3 to pants
            4 to shoes
        */

        // I don't know what the FUCK is going on here, so I can't really refactor this
        val pieceMap = pieceIndexToPieceMap[pieceType]
        for (tempRange: List<Int> in pieceMap.keys) {
            if (realTemp != null) {
                if (realTemp >= tempRange[0] && realTemp <= tempRange[1]) {
                    val temperatureRange = listOf(tempRange[0], tempRange[1])
                    return pieceMap.getValue(temperatureRange)
                }
            }
        }

        return "Noe galt har skjedd"
    }

    // Returns a string representation of weather/date/condition specific clothing
    private fun getSpecial(realTemp: Float?, weatherData: WeatherData?): String {
        val rainAmount = rainAmount(weatherData)
        val windSpeed1Hour = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.wind_speed
        val windSpeed3Hour = weatherData?.properties?.timeseries?.get(timeSeriesIndex+2)?.data?.instant?.details?.wind_speed
        val windSpeed5Hour = weatherData?.properties?.timeseries?.get(timeSeriesIndex+4)?.data?.instant?.details?.wind_speed
        val avgWind = if (windSpeed1Hour != null && windSpeed3Hour != null && windSpeed5Hour != null) (windSpeed1Hour + windSpeed3Hour + windSpeed5Hour) / 3 else 0.0

        if (rainAmount > 3 && avgWind < 13) {
            return special.getValue(2)  // Return umbrella if more than light rain and wind speeds are low
        }
        if (sunCheck(weatherData)) {
            return special.getValue(0)  // Return sunglasses if sunny
        }
        if (realTemp != null) {
            if (realTemp < -5) {
                return special.getValue(1)  // Return mittens and scarf if cold
            }
        }

        // Date specific items (not implemented)
        /*
        val time = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.time
        val day = time?.substring(8,10)
        val month = time?.substring(5,7)
        if (day == "17" && month == "05") {
            //hent 17. mai flagg
        }
        */

        // Place specific items (not implemented)

        return ""  // No special clothing. Empty strings work as null in this case beause... I don't know
    }

    // Returns the amount of rain on a scale from 0-10
    private fun rainAmount(weatherData: WeatherData?): Int {
        val minTemp = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.next_6_hours?.details?.air_temperature_min
        val avgPrecipation = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.next_6_hours?.details?.precipitation_amount

        if (avgPrecipation != null && minTemp != null) {
            if (avgPrecipation > 0.5 && avgPrecipation < 1 && minTemp > 0) {
                return 3  // light rain
            } else if (avgPrecipation >= 1 && avgPrecipation < 5 && minTemp > 0) {
                return 5  // rain
            } else if (avgPrecipation >= 5 && minTemp > 0) {
                return 10  // heavy rain
            }
        }

        return 0
    }

    // Check whether or not it is consider sunny outside
    private fun sunCheck(weatherData: WeatherData?): Boolean {
        val symbol = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.next_1_hours?.summary?.symbol_code

        return (symbol == "fair_day" || symbol == "clearsky_day")
    }
}
