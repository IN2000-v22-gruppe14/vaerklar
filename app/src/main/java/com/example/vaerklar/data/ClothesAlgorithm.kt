package com.example.vaerklar.data

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


class ClothesAlgorithm {

    var head = hashMapOf<Int, String>(
        -10 to "pannebånd",
        10 to "pannebånd",
        30 to "solhatt"
    )

    var body = hashMapOf<Int, String>(
        -10 to "ullgenser",
        10 to "genser",
        30 to "tskjorte"
    )

    var jacket = hashMapOf<Int, String>(
        -10 to "boblejakke",
        10 to "jakke",
        30 to "tskjorte"
    )

    var underdel = hashMapOf<Int, String>(
        -10 to "utebukse",
        10 to "bukse",
        30 to "skjorts"
    )

    var shoe = hashMapOf<Int, String>(
        -10 to "vintersko",
        10 to "joggesko",
        30 to "sandaler"
    )

    var special = hashMapOf<Int, String>(
        0 to "solbriller",
        1 to "skjerf",
        3 to "votter"
    )

    var rain = hashMapOf<Int, String>(
        0 to "regnhatt",
        1 to "_DETTE SKAL IKKE SKJE_",
        2 to "regnjakke",
        3 to "regnbukse",
        4 to "gummistøvler"
    )

    var clothingReg = listOf<HashMap<Int, String>>(
        head,
        body,
        jacket,
        underdel,
        shoe,
        special,
        rain
    )

    fun getWeatherScore(weatherData: WeatherData?){
        var airTemp = weatherData?.properties?.timeseries?.get(0)?.data?.instant?.details?.air_temperature
        var windSpeed = weatherData?.properties?.timeseries?.get(0)?.data?.instant?.details?.wind_speed
        var humidity = weatherData?.properties?.timeseries?.get(0)?.data?.instant?.details?.relative_humidity


        var fhTemp = ((airTemp?.times(9))?.div(5))?.plus(32)
        var realTemp = fhTemp



        if (airTemp != null && windSpeed != null && humidity != null && fhTemp != null) {
            if (fhTemp <= 50 && windSpeed >= 3.00){
                realTemp = 35.74 + (0.6215*fhTemp) - 35.75*(windSpeed.pow(0.16)) + ((0.4275*fhTemp)*(windSpeed.pow(0.16)))
            }

            if(realTemp == fhTemp && fhTemp >= 80){
                realTemp = 0.5 * (fhTemp + 61.0 + ((fhTemp-68.0)*1.2) + (humidity*0.094))
                if (realTemp >= 80){
                    realTemp = -42.379 + 2.04901523*fhTemp + 10.14333127*humidity - .22475541*fhTemp*humidity - .00683783*fhTemp*fhTemp - .05481717*humidity*fhTemp + .00122874*fhTemp*fhTemp*humidity + .00085282*fhTemp*humidity*humidity - .00000199*fhTemp*fhTemp*humidity*humidity

                    if (humidity < 13 && fhTemp >= 80 && fhTemp <= 112){
                        realTemp -= ((13 - humidity) / 4) * sqrt((17 - abs(fhTemp - 95)) / 17)
                        if (humidity > 85 && fhTemp >= 80 && fhTemp <= 87){
                            realTemp += ((humidity - 85) / 10) * ((87 - fhTemp) / 5)
                        }
                    }
                }
            }
            if (realTemp != null) {
                realTemp = (realTemp-32)*0.5556
            }
        }


        println("Real temperature: $realTemp")
        //println("Lufttemperatur: $airTemp")
    }

    fun getOutfit(weatherData: WeatherData?,realTemp : Float){
        // tenker at klær skal ha score fra 0-4 der 4 er spesial (eks solbriller
        var outfit = ""
        val r = 0
        val rain = rainCheck(WeatherData?)

        while (r < 5) {
            outfit += getPiece(r,rain, realTemp)
            r++
        }
        outfit += get_special(weatherData,realTemp)

        return outfit
    }

    fun getPiece(number : Int,rain: Int ,realTemp : Float ) {
        if (number == 2 && 10>rain > 5 ){
            regnjakke
        }
        if (number != 1 && rain > 10){
            // genser hentes uforandrett så den skal ikke innom her
            //hent regn[r]
            // eks regnjakke, lue gummistøvler
        }
        else {
            //hent plagg[r] for realtemp
        }
    }

    fun get_special(rain : Int,realTemp : Float ) {
        if (10>rain > 3){
            // hent paraply
        }
        if (sunCheck(WeatherData?)){
            //hent solbriller
        }
        if (kaldt){

        }
        // her kan også legges inn dato greier eks 17 mai
        // eller kanskje til og med steder eks hemsedal (Snowboard briller)
    }

    fun rainCheck (weatherData: WeatherData?){
        val symbol = weatherData?.properties?.timeseries?.get(0)?.data?.next_6_hours?.summary?.symbol_code
        if (symbol == light_rain) {
            return 4
        }
        if (symbol == rain){
            return 5
        }
        if (symbol == heavy_rain){
            return 10
        }
        if (symbol == storm){
            return 15
        }
        return 0
    }

    fun sunCheck (weatherData: WeatherData?){
        val symbol = weatherData?.properties?.timeseries?.get(0)?.data?.next_6_hours?.summary?.symbol_code
        if (symbol == sol) {
            return true
        }
        return false
    }
}