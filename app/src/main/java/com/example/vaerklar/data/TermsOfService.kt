package com.example.vaerklar.data

class TermsOfService {
    val terms = "Denne appen var laget som et prosjekt i emnet in2000 ved UiO\n" +
                "De fulle rettighetene til appen ligger hos: \n" +
                "Leander West Furumo\n" +
                "Petter Sæterøy\n" +
                "Mathias Morsund\n" +
                "Souhail Ziani\n" +
                "Henrik Shi Yu Henriksen\n" +
                "Mads Severin Murvold\n\n" +
                "Appen er åpen for alle å bruke og ta inspirasjon fra, men direkte tyveri av " +
                "koden vil ansees som tyveri av intelektuel eiendom.\n\n" +
                "Ønskes det å benytte noe fra prosjektet til undervisning kan dette fritt" +
                "gjøres, men det ønskelig at skaperne blir opplyst om dette. "

    fun getText():String{
        return terms
    }
}