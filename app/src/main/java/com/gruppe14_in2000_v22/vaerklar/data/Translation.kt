package com.gruppe14_in2000_v22.vaerklar.data

import com.gruppe14_in2000_v22.vaerklar.R


//  Tranlates the MET LocationForecast API's weather descriptions to human-reabable, norwegian ones.
//  You can find the API's list of descriptions here:
//  https://in2000-apiproxy.ifi.uio.no/weatherapi/weathericon/2.0/documentation
val weatherDescriptionTranslation: HashMap<String, String> = hashMapOf(
    "clearsky_day" to "Skyfritt",
    "clearsky_night" to "Skyfritt",
    "clearsky_dusk" to "Skyfritt",
    "cloudy" to "Overskyet",
    "fair" to "Lett skyet",
    "fair_day" to "Lett skyet",
    "fair_night" to "Lett skyet",
    "fair_dusk" to "Lett skyet",
    "fog" to "Tåkete",
    "heavyrain" to "Regn",
    "heavyrainandthunder" to "Storm med lyn",
    "heavyrainshowers_day" to "Tidvis regn",
    "heavyrainshowers_night" to "Tidvis regn",
    "heavyrainshowers_dusk" to "Tidvis regn",
    "heavyrainshowersandthunder_day" to "Tidvis strøm og lyn",
    "heavyrainshowersandthunder_night" to "Tidvis strøm og lyn",
    "heavyrainshowersandthunder_dusk" to "Tidvis strøm og lyn",
    "heavysleet" to "sludd",
    "heavysleetandthunder" to "Sludd og lyn",
    "heavysleetshowers_day" to "Tidvis sludd",
    "heavysleetshowers_night" to "Tidvis sludd",
    "heavysleetshowers_dusk" to "Tidvis sludd",
    "heavysleetshowersandthunder_day" to "Tidvis sludd og lyn",
    "heavysleetshowersandthunder_night" to "Tidvis sludd og lyn",
    "heavysleetshowersandthunder_dusk" to "Tidvis sludd og lyn",
    "heavysnow" to "Snø",
    "heavysnowandthunder" to "Snø og lyn",
    "heavysnowshowers_day" to "Tidvis mye snø",
    "heavysnowshowers_night" to "Tidvis mye snø",
    "heavysnowshowers_dusk" to "Tidvis mye snø",
    "lightrain" to "Lett nedbør",
    "lightrainandthunder" to "Lett regn og lyn",
    "lightrainshowers_day" to "Tidvis lett regn",
    "lightrainshowers_night" to "Tidvis lett regn",
    "lightrainshowers_dusk" to "Tidvis lett regn",
    "lightrainshowersandthunder_day" to "Tidvis lett regn og lyn",
    "lightrainshowersandthunder_night" to "Tidvis lett regn og lyn",
    "lightrainshowersandthunder_dusk" to "Tidvis lett regn og lyn",
    "lightsleet" to "Lett sludd",
    "lightsleetandthunder" to "Lett sludd og lyn",
    "lightsleetshowers_day" to "Tidvis lett sludd",
    "lightsleetshowers_night" to "Tidvis lett sludd",
    "lightsleetshowers_dusk" to "Tidvis lett sludd",
    "lightsnow" to "Lett snø",
    "lightsnowandthunder" to "Lett snø og lyn",
    "lightsnowshowers_day" to "Tidvis lett snø",
    "lightsnowshowers_night" to "Tidvis lett snø",
    "lightsnowshowers_dusk" to "Tidvis lett snø",
    "lightssleetshowersandthunder_day" to "Tidvis lett sludd og lyn",
    "lightssleetshowersandthunder_night" to "Tidvis lett sludd og lyn",
    "lightssleetshowersandthunder_dusk" to "Tidvis lett sludd og lyn",
    "lightssnowshowersandthunder_day" to "Tidvis lett snø og lyn",
    "lightssnowshowersandthunder_night" to "Tidvis lett snø og lyn",
    "lightssnowshowersandthunder_dusk" to "Tidvis lett snø og lyn",
    "partlycloudy_day" to "Lettere overskyet",
    "partlycloudy_night" to "Lettere overskyet",
    "partlycloudy_dusk" to "Lettere overskyet",
    "rain" to "Regn",
    "rainandthunder" to "Regn og lyn",
    "rainshowers_day" to "Tidvis regn",
    "rainshowers_night" to "Tidvis regn",
    "rainshowers_dusk" to "Tidvis regn",
    "rainshowersandthunder_day" to "Tidvis regn og lyn",
    "rainshowersandthunder_night" to "Tidvis regn og lyn",
    "rainshowersandthunder_dusk" to "Tidvis regn og lyn",
    "sleet" to "Sludd",
    "sleetandthunder" to "Sludd og lyn",
    "sleetshowers_day" to "Tidvis sludd",
    "sleetshowers_night" to "Tidvis sludd",
    "sleetshowers_dusk" to "Tidvis sludd",
    "sleetshowersandthunder_day" to "Tidvis sludd og lyn",
    "sleetshowersandthunder_night" to "Tidvis sludd og lyn",
    "sleetshowersandthunder_dusk" to "Tidvis sludd og lyn",
    "snow" to "Snø",
    "snowandthunder" to "Snø og lyn",
    "snowshowers_day" to "Tidvis snø",
    "snowshowers_night" to "Tidvis snø",
    "snowshowers_dusk" to "Tidvis snø",
    "snowshowersandthunder_day" to "Tidvis snø og lyn",
    "snowshowersandthunder_night" to "Tidvis snø og lyn",
    "snowshowersandthunder_dusk" to "Tidvis snø og lyn"
)

// Mapping between english and norwegian weekday abbreviations
fun translateWeekdayAbbreviation(weekDayAbbreviation: String): String {
    return when (weekDayAbbreviation) {
        "MO" -> "MA"
        "TU" -> "TI"
        "WE" -> "ON"
        "TH" -> "TO"
        "FR" -> "FR"
        "SA" -> "LØ"
        "SU" -> "SØ"
        else -> "..."
    }
}

// Mapping between english weekday abbreviations to their norwegian full name equivalent
fun translateWeekDayWhole(weekDayAbbreviation: String): String {
    return when (weekDayAbbreviation) {
        "MO" -> "MANDAG"
        "TU" -> "TIRSDAG"
        "WE" -> "ONSDAG"
        "TH" -> "TORSDAG"
        "FR" -> "FREDAG"
        "SA" -> "LØRDAG"
        "SU" -> "SØNDAG"
        else -> "..."
    }
}

// Translates symbol strings from the MET LocationForecast API to icons provided in the application for display.
val iconTranslation = hashMapOf(
    // CLEAR
    "clearsky_day" to R.drawable.clear_day,
    "clearsky_night" to R.drawable.clear_night,

    // CLOUDS
    "fair_day" to R.drawable.fair_day,
    "fair_night" to R.drawable.fair_night,

    "cloudy" to R.drawable.cloudy,
    "partlycloudy_day" to R.drawable.partly_cloudy_day,
    "partlycloudy_night" to R.drawable.partly_cloudy_night,

    // FOG
    "fog" to R.drawable.foggy,

    // RAIN
    "lightrain" to R.drawable.light_rain,
    "lightrainshowers_day" to R.drawable.light_rain_day,
    "lightrainshowers_night" to R.drawable.light_rain_night,
    "rain" to R.drawable.rain,
    "rainshowers_day" to R.drawable.rain_day,
    "rainshowers_night" to R.drawable.rain_night,
    "heavyrain" to R.drawable.heavy_rain,
    "heavyshowers_day" to R.drawable.heavy_rain_day,
    "heavyshowers_night" to R.drawable.heavy_rain_night,

    // SLEET
    "lightsleet" to R.drawable.light_sleet,
    "lightsleetshowers_day" to R.drawable.light_sleet_day,
    "lightsleetshowers_night" to R.drawable.light_sleet_night,
    "sleet" to R.drawable.sleet,
    "sleetshowers_day" to R.drawable.sleet_day,
    "sleetshowers_night" to R.drawable.sleet_night,
    "heavysleet" to R.drawable.heavy_sleet,
    "heavysleetshowers_day" to R.drawable.heavy_sleet_day,
    "heavysleetshowers_night" to R.drawable.heavy_sleet_night,

    // SNOW
    "lightsnow" to R.drawable.light_snow,
    "lightsnowshowers_day" to R.drawable.light_snow_day,
    "lightsnowshowers_night" to R.drawable.light_snow_night,
    "snow" to R.drawable.snow,
    "snowshowers_day" to R.drawable.snow_day,
    "snowshowers_night" to R.drawable.snow_night,
    "heavysnow" to R.drawable.heavy_snow,
    "heavysnowshowers_day" to R.drawable.heavy_snow_day,
    "heavysnowshowers_day" to R.drawable.heavy_snow_night,

    // THUNDER (RAIN, NO SLEET, SNOW)
    "" to R.drawable.light_rain_thunder,
    "" to R.drawable.light_rain_thunder_day,
    "" to R.drawable.light_rain_thunder_night,

    "" to R.drawable.rain_thunder,
    "" to R.drawable.rain_thunder_day,
    "" to R.drawable.rain_thunder_night,

    "" to R.drawable.heavy_rain_thunder,
    "" to R.drawable.heavy_rain_thunder_day,
    "" to R.drawable.heavy_rain_thunder_night,

    "" to R.drawable.light_snow_thunder,
    "" to R.drawable.light_snow_thunder_day,
    "" to R.drawable.light_snow_thunder_night,

    "" to R.drawable.snow_thunder,
    "" to R.drawable.snow_thunder_day,
    "" to R.drawable.snow_thunder_night,

    "" to R.drawable.heavy_snow_thunder,
    "" to R.drawable.heavy_snow_thunder_day,
    "" to R.drawable.heavy_snow_thunder_night,

    // Fallback value
    null to R.drawable.partly_cloudy_day
)

val clothingItemNameToDrawable = hashMapOf(
    "bukse" to R.drawable.pants,
    "lue" to R.drawable.hat,
    "genser" to R.drawable.sweater,
    "genserArm" to R.drawable.sweaterarm,
    "sneakers" to R.drawable.sneaks,
    "bobblejakke" to R.drawable.bubble,
    "bobblejakkeArm" to R.drawable.bubblearm,
    "chinos" to R.drawable.chinos,
    "sandaler" to R.drawable.shoes,
    "sandalerBunn" to R.drawable.shoesoles,
    "regnbukse" to R.drawable.rainpants,
    "" to R.drawable.chinos,
    "flagg" to R.drawable.flag,
    "vinterjakke" to R.drawable.coat,
    "vinterjakkeArm" to R.drawable.coatarm,
    "jakke" to R.drawable.windjacket,
    "jakkeArm" to R.drawable.windjacketarm,
    "joggebukse" to R.drawable.sweatpants,
    "kjole" to R.drawable.dress,
    "langermetArm" to R.drawable.longsleevearm,
    "langermet" to R.drawable.longsleeve,
    "øl" to R.drawable.beer,
    "pannebånd" to R.drawable.sweatband,
    "regnjakke" to R.drawable.raincoat,
    "regnjakkeArm" to R.drawable.raincoatarm,
    "skjort" to R.drawable.skirt,
    "sløyfe" to R.drawable.bowtie,
    "solbriller" to R.drawable.sunglasses,
    "solhatt" to R.drawable.buckethat,
    "tskjorte" to R.drawable.tshirt,
    "gummistøvler" to R.drawable.rainboots,
    "ullgenserArm" to R.drawable.woolarm,
    "vindjakke" to R.drawable.windjacket,
    "votter & skjerf" to R.drawable.scarf_mitten,
    "happy" to R.drawable.happy,
    "paraply" to R.drawable.umbrella,
    "shorts" to R.drawable.shorts,
    "exited" to R.drawable.excited,
    "sleepy" to R.drawable.tired,
    "ullgenser" to R.drawable.wool,
    "vindjakkeArm" to R.drawable.windjacketarm,
    "vintersko" to R.drawable.winterboots,
)
