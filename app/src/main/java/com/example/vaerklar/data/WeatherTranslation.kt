package com.example.vaerklar.data

/***
 *Oversettelse av de forskjellige vær man kan hente ut av APIet vi har brukt for å hente ut vær.
 * Oversikt over de forskjellige typene vær kan man finne her:
 * https://in2000-apiproxy.ifi.uio.no/weatherapi/weathericon/2.0/documentation
 ***/

class WeatherTranslation {
    companion object Translation {
        var WeatherMap: HashMap<String, String> = hashMapOf(
            "clearsky" to "skyfritt",
            "clody" to "overskyet",
            "fair" to "lett skyet",
            "fog" to "tåkete",
            "heavyrain" to "regn",
            "heavyrainandthunder" to "storm med lyn",
            "heavyrainshowers" to "tidvis regn",
            "heavyrainshowersandthunder" to "tidvis strom og lyn",
            "heavysleet" to "sludd",
            "heavysleetandthunder" to "sludd og lyn",
            "heavysleetshowers" to "tidvis sludd",
            "heavysleetshowersandthunder" to "tidvis sludd og lyn",
            "heavysnow" to "snø",
            "heavysnowandthunder" to "snø og lyn",
            "lightrain" to "lett nedbør",
            "lightrainandthunder" to "lett regn og lyn",
            "lightrainshowers" to "tidvis lett regn",
            "lightrainshowersandthunder" to "tidvis lett regn og lyn",
            "lightsleet" to "lett sludd",
            "lightsleetandthunder" to "lett sludd og lyn",
            "lightsleetshowers" to "tidvis lett sludd",
            "lightsnow" to "lett snø",
            "lightsnowandthunder" to "lett snø og lyn",
            "lightsnowshowers" to "tidvis lett snø",
            "lightssleetshowersandthunder" to "tidvis lett sludd og lyn",
            "lightssnowshowersandthunder" to "tidvis lett snø og lyn",
            "partlycloudy" to "lettere overskyet",
            "rain" to "regn",
            "rainandthunder" to "regn og lyn",
            "rainshowers" to "tidvis regn",
            "rainshowersandthunder" to "tidvis regn og lyn",
            "sleet" to "sludd",
            "sleetandthunder" to "sludd og lyn",
            "sleetshowers" to "tidvis sludd",
            "sleetshowersandthunder" to "tidvis sludd og lyn",
            "snow" to "snø",
            "snowandthunder" to "snø og lyn",
            "snowshowers" to "tidvis snø",
            "snowshowersandthunder" to "tidvis snø og lyn"
        )
        fun getTranslation(weather:String) : String? {
            return WeatherMap.get(weather)
        }
    }
}