package com.example.vaerklar.data

import androidx.annotation.DrawableRes
import com.example.vaerklar.R

data class Page(val title : String, val description : String, @DrawableRes val image : Int)

val onboardPages = listOf(
    Page("Anbefalt for deg",
        "På avataren får du anbefalt klesplagg til været akkurat nå. \n\n Du kan også få en tesktlig beskrivelse ved å trykke på pilen.",
        R.drawable.bilde1
    ),
    Page("Været nå",
        "Her kan du se temperaturen, været, nedbørsmengde og vindstyrke akkurat nå. \n\n ",
        R.drawable.bilde3
    ),
    Page("Dagens vær",
        "Her kan du se værmeldingen for dagen og trykke på tidspunktene for å få anbefalte klesplagg. \n\n",
        R.drawable.bilde4
    ),
    Page("Ukens vær",
        "Her kan du se værmeldingen for uka og trykke på dagene for å få anbefalt klesplagg. \n\n",
        R.drawable.bilde5
    )
)
