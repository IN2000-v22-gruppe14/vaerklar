package com.example.vaerklar.data

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


class ClothesAlgorithm {

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

    fun getOutfit(){

    }
}