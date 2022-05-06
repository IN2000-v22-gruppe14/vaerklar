package com.example.vaerklar.data

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToLong
import kotlin.math.sqrt


class ClothesAlgorithm {
    private var timeSeriesIndex = 0

    private var head = hashMapOf(
        listOf(-100,0) to "lue",
        listOf(1,10) to "pannebånd",
        listOf(11,24) to "",
        listOf(25,50) to "solhatt"
    )

    private var body = hashMapOf(
        listOf(-100,-1) to "ullgenser",
        listOf(0,15) to "genser",
      //  listOf(20,50) to "topp",
        listOf(16,50) to "tskjorte"
    )

    private var jacket = hashMapOf(
        listOf(-100,4) to "vinterjakke",
        listOf(5,10) to "jakke",
        listOf(11,100) to ""
    )

    private var underdel = hashMapOf(
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
        1 to "_DETTE SKAL IKKE SKJE_",
        2 to "regnjakke",
        3 to "regnbukse",
        4 to "gummistøvler"
    )

    private var clothingReg = listOf(
        head,
        body,
        jacket,
        underdel,
        shoe
    )

    private var realtemperature = 0.0


    fun getWeatherScore(weatherData: WeatherData?, index : Int) : MutableList<String>{

        timeSeriesIndex = index
        //getTimeSeriesIndex(weatherData)
        val airTemp = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.air_temperature
        val windSpeed = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.wind_speed
        val humidity = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.relative_humidity

        val fhTemp = ((airTemp?.times(9))?.div(5))?.plus(32)
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
                realtemperature = realTemp
            }
        }


        println("Real temperature: $realTemp")
        val clothString = getOutfit(weatherData, realTemp?.toFloat())
        println("Klær: $clothString")

        return clothString
    }


    fun getRealTemp() : Double {
        return realtemperature
    }

    private fun getOutfit(weatherData: WeatherData?, realTemp : Float?): MutableList<String>{
        val realTempRound = (realTemp?.roundToLong())?.toFloat()
        val outfitList = mutableListOf<String>()
        var plaggNummer = 0
        val rain = rainCheck(weatherData)

        while (plaggNummer < 5) {
            outfitList.add(getPiece(plaggNummer,rain, realTempRound))
            plaggNummer++
        }
        outfitList.add(getSpecial(realTempRound, weatherData))

        return outfitList
    }

    private fun getPiece(number : Int, rain: Int, realTemp : Float?) : String {
        /*
        0 to hodeplagg
        1 to genser, tskjorte etc
        2 to jakke
        3 to bukse
        4 to sko*/

        //sjekker om nummer = jakke og om regn er riktig, isåfall regnjakke
        return if (number != 1 && rain == 10) {
            //regner masse, fullt utstyr på
            rainClothing.getValue(number)
        }else if (number != 1 && number != 0 && number != 3 && rain == 5){
            //regner endel, hent alt unntatt regnbukse og hatt
            rainClothing.getValue(number)
        }else if (number == 2 && rain == 3){
            //lett regn, bare regnjakke
            rainClothing.getValue(number)
        }else{
            //når man ikke trenger et spesielt plagg pga regn
            getNormalClothing(number, realTemp)
        }
    }

    private fun getNormalClothing(number : Int, realTemp: Float?) : String{
        val pieceMap = clothingReg[number]
        for(tempRange:List<Int> in pieceMap.keys){
            if (realTemp != null) {
                if(realTemp >= tempRange[0] && realTemp <= tempRange[1]){
                    val list = listOf(tempRange[0], tempRange[1])
                    return pieceMap.getValue(list)
                }
            }
        }
        return "Noe galt har skjedd"
    }

    private fun getSpecial(realTemp : Float?, weatherData: WeatherData?) : String{
        val rain = rainCheck(weatherData)
        val windSpeed1time = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.instant?.details?.wind_speed
        val windSpeed3time = weatherData?.properties?.timeseries?.get(timeSeriesIndex+2)?.data?.instant?.details?.wind_speed
        val windSpeed5time = weatherData?.properties?.timeseries?.get(timeSeriesIndex+4)?.data?.instant?.details?.wind_speed
        var avgWind = 0.0

        if (windSpeed1time != null && windSpeed3time != null && windSpeed5time != null){
            val totWind = windSpeed1time + windSpeed3time + windSpeed5time
            avgWind = totWind/3
        }

        //henter specials
        if (rain > 3 && avgWind < 13){
            // hent paraply hvis det regner mer en light men ikke hvis det blåser jævlig
            return special.getValue(2)
        }
        if (sunCheck(weatherData)){
            //hent solbriller
            return special.getValue(0)
        }

        if (realTemp != null) {
            if (realTemp < -5){
                //hent votter og skjerf (de må sammen pga tegning)
                return special.getValue(1)
            }
        }
        val time = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.time
        val day = time?.substring(8,10)
        val month = time?.substring(5,7)
        if (day == "17" && month == "05") {
            //hent 17. mai flagg
        }
        // eller kanskje til og med steder eks hemsedal (Snowboard briller)
        return ""
    }

    private fun rainCheck (weatherData: WeatherData?) : Int{
        //endrer denne fra å bruke symbol (det er alt for mange ulike, eks: lightsleetshowers_day) til å bruke precipitation og temp
        val minTemp = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.next_6_hours?.details?.air_temperature_min
        val avgPrecip = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.next_6_hours?.details?.precipitation_amount

        if (avgPrecip != null && minTemp != null) {
            if(avgPrecip > 0.5 && avgPrecip < 1 && minTemp > 0){
                //lightrain
                return 3
            }else if(avgPrecip >= 1 && avgPrecip < 5 && minTemp > 0){
                //rain
                return 5
            }else if(avgPrecip >= 5 && minTemp > 0){
                //heavy rain
                return 10
            }
        }
        return 0
    }


    private fun sunCheck (weatherData: WeatherData?) : Boolean{
        val symbol = weatherData?.properties?.timeseries?.get(timeSeriesIndex)?.data?.next_1_hours?.summary?.symbol_code
        if (symbol == "fair_day" || symbol == "clearsky_day") {
            return true
        }
        return false
    }
}
