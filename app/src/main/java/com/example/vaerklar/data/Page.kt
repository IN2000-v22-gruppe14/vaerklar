package com.example.vaerklar.data

import androidx.annotation.DrawableRes
import com.example.vaerklar.R

data class Page(val title : String, val description : String, @DrawableRes val image : Int)

val onboardPages = listOf(
    Page("Anbefalt for deg",
    "Klesplaggene du vil få anbefalt vil vises på denne avataren eller trykk på pilen for en skriftlig beskrivelse",
    R.drawable.avatar
    ),

    Page("Værforholdene akkurat nå",
    "Her kan du se temperaturen, været, nedbørsmengde og vindstyrke akkurat nå, hvor enn du er",
    R.drawable.rightnow
    ),

    Page("Dagens vær",
    "Her kan du se værmeldingen for dagen og trykke på tidspunktene for å få anbefalte klesplagg",
    R.drawable.todays),

    Page("Ukas vær",
    "Her kan se værmeldingen for uka og trykke på dagene for å få anbefalt klesplagg",
    R.drawable.week)

)
