package com.example.vaerklar.data

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


class ClothesAlgorithm {

    var head = hashMapOf<List<Int>, String>(
        listOf(-100,0) to "lue",
        listOf(1,10) to "pannebånd",
        listOf(25,50) to "solhatt"
    )

    var body = hashMapOf<List<Int>, String>(
        listOf(-100,0) to "ullgenser",
        listOf(1,15) to "genser",
        listOf(20,50) to "topp",
        listOf(15,50) to "tskjorte"
    )

    var jacket = hashMapOf<List<Int>, String>(
        listOf(-100,4) to "vinterjakke",
        listOf(5,10) to "jakke"
    )

    var underdel = hashMapOf<List<Int>, String>(
        listOf(-100,-10) to "utebukse",
        listOf(-9,19) to "bukse",
        listOf(-20,50) to "skjorts"
    )

    var shoe = hashMapOf<List<Int>, String>(
        listOf(-100,5) to "vintersko",
        listOf(6,25) to "joggesko",
        listOf(26,50) to "sandaler"
    )

    var special = hashMapOf<List<Int>, String>(
        listOf(0) to "solbriller",
        listOf(1) to "skjerf",
        listOf(3) to "votter"
    )

    var rainClothing = hashMapOf<List<Int>, String>(
        listOf(0) to "regnhatt",
        listOf(1) to "_DETTE SKAL IKKE SKJE_",
        listOf(2) to "regnjakke",
        listOf(3) to "regnbukse",
        listOf(4) to "gummistøvler"
    )

    var clothingReg = listOf<HashMap<List<Int>, String>>(
        head,
        body,
        jacket,
        underdel,
        shoe,
        special,
        rainClothing
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

    fun getOutfit(weatherData: WeatherData?, realTemp : Float): MutableList<String>{
        // tenker at klær skal ha score fra 0-4 der 4 er spesial (eks solbriller)
        //var outfit = ""
        var outfitList = mutableListOf<String>()
        var plaggNummer = 0
        val rain = rainCheck(weatherData)

        while (plaggNummer < 5) {
            outfitList.add(getPiece(plaggNummer,rain, realTemp))
            //outfit += getPiece(plaggNummer,rain, realTemp)
            plaggNummer++
        }
        outfitList.add(get_special(realTemp, weatherData))
        //outfit += get_special(realTemp, weatherData)

        return outfitList
    }

    fun getPiece(number : Int, rain: Int, realTemp : Float ) : String {
        /*
        0 to "regnhatt",
        1 to "_DETTE SKAL IKKE SKJE_", = genser, tskjorte etc
        2 to "regnjakke",
        3 to "regnbukse",
        4 to "gummistøvler"*/

        //sjekker om nummer = jakke og om regn er riktig, isåfall regnjakke
        if (number == 2 && 10 > rain && 5 < rain){
            //val piece = rainClothing.getValue(listOf(2))
            val drit = rainClothing.keys.elementAt(2)
            val piece = rainClothing.getValue(drit)
            return piece
        }else if (number != 1 && rain > 4){
            // genser hentes uforandrett så den skal ikke innom her
            //hent regn[r]
            // eks regnjakke, lue gummistøvler
            val drit = rainClothing.keys.elementAt(number)
            val piece = rainClothing.getValue(drit)
            return piece
        }else{
            //når man ikke trenger et spesielt plagg pga regn
            return getNormalClothing(number, realTemp)
        }
    }

    fun getNormalClothing(number : Int, realTemp: Float) : String{
        if(number == 0){
            for(tempRange:List<Int> in head.keys){
                if(realTemp > tempRange.get(0) && realTemp < tempRange.get(1)){
                    val list = listOf(tempRange.get(0), tempRange.get(1))
                    return head.getValue(list)
                }
            }
        }else if (number == 1){
            for(tempRange:List<Int> in body.keys){
                if(realTemp > tempRange.get(0) && realTemp < tempRange.get(1)){
                    val list = listOf(tempRange.get(0), tempRange.get(1))
                    return body.getValue(list)
                }
            }
        }else if (number == 2){
            for(tempRange:List<Int> in jacket.keys){
                if(realTemp > tempRange.get(0) && realTemp < tempRange.get(1)){
                    val list = listOf(tempRange.get(0), tempRange.get(1))
                    return jacket.getValue(list)
                }
            }
        }else if (number == 3){
            for(tempRange:List<Int> in underdel.keys){
                if(realTemp > tempRange.get(0) && realTemp < tempRange.get(1)){
                    val list = listOf(tempRange.get(0), tempRange.get(1))
                    return underdel.getValue(list)
                }
            }
        }else if (number == 4){
            for(tempRange:List<Int> in shoe.keys){
                if(realTemp > tempRange.get(0) && realTemp < tempRange.get(1)){
                    val list = listOf(tempRange.get(0), tempRange.get(1))
                    return shoe.getValue(list)
                }
            }
        }
        return "Noe galt har skjedd"
    }

    fun get_special(realTemp : Float, weatherData: WeatherData?) : String{
        val rain = weatherData?.properties?.timeseries?.get(0)?.data?.next_6_hours?.details?.precipitation_amount
        if (10 > rain!! && 3 < rain){
            // hent paraply
        }
        if (sunCheck(weatherData)){
            //hent solbriller
        }
        /*
        if (kaldt){

        }*/
        // her kan også legges inn dato greier eks 17 mai
        // eller kanskje til og med steder eks hemsedal (Snowboard briller)
        return ""
    }

    fun rainCheck (weatherData: WeatherData?) : Int{
        val symbol = weatherData?.properties?.timeseries?.get(0)?.data?.next_6_hours?.summary?.symbol_code
        if (symbol == "light_rain") {
            return 4
        }
        if (symbol == "rain"){
            return 5
        }
        if (symbol == "heavy_rain"){
            return 10
        }
        if (symbol == "storm"){
            return 15
        }
        return 0
    }

    fun sunCheck (weatherData: WeatherData?) : Boolean{
        val symbol = weatherData?.properties?.timeseries?.get(0)?.data?.next_6_hours?.summary?.symbol_code
        if (symbol == "sol") {
            return true
        }
        return false
    }
}