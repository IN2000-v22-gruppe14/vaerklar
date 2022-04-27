package com.example.vaerklar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.data.LocationData
import com.example.vaerklar.ui.theme.Rubik
import com.example.vaerklar.R
import com.example.vaerklar.data.ClothesAlgorithm
import com.example.vaerklar.data.WeatherData

@Preview
@Composable
// The avatar, alongside location.
fun Avatar(data: WeatherData?, locationName: String) {

    val converterClothing = hashMapOf<String?, Int>(
        // CLEAR
        "bukse" to R.drawable.bukse,
        "lue" to R.drawable.hatt,
        "genser" to R.drawable.genser,
        "genser2" to R.drawable.genser2,
        "sneakers" to R.drawable.sneaks,
        "bobblejakke" to R.drawable.bubble,
        "bobblejakke2" to R.drawable.bubble2,
        "chinos" to R.drawable.chinos,
        "sandaler" to R.drawable.sko,
        "sandaler2" to R.drawable.sko2,
        "regnbukse" to R.drawable.regnbukse,
        "" to R.drawable.chinos,
       "flagg" to R.drawable.flagg,
       "vinterjakke" to R.drawable.frakk,
       "vinterjakke2" to R.drawable.frakk2,
       "gummistøvler" to R.drawable.gummist_ler,
       "joggebukse" to R.drawable.jogge,
       "kjole" to R.drawable.kjole,
       "langermet2" to R.drawable.langermet2,
       "langermet" to R.drawable.langermet,
       "øl" to R.drawable.ol,
       "pannebånd" to R.drawable.panne,
       "regnjakke" to R.drawable.regn,
       "regnjakke2" to R.drawable.regn2,
       "skjort" to R.drawable.skjort,
       "sløyfe" to R.drawable.sl_yfe,
       "solbriller" to R.drawable.solbriller,
       "solhatt" to R.drawable.solhatt,
       "tskjorte" to R.drawable.tskjorte,
       "gummistøvler" to R.drawable.stovel,
       "ullgenser2" to R.drawable.ull2,
       "vindjakke" to R.drawable.vind,
       "votter & skjerf" to R.drawable.skjerf_og_vott,
       "happy" to R.drawable.glad,
       "paraply" to R.drawable.paraply,
       "shorts" to R.drawable.shorts,
       "exited" to R.drawable.spenstig,
       "sleepy" to R.drawable.trott,
       "ullgenser" to R.drawable.ull,
       "vindjakke2" to R.drawable.vind2,
       "vintersko" to R.drawable.vintersko,

        //BRUH
        null to R.drawable.solhatt
    )

    val sizeDP = 250.dp
    val clothesAlgorithm = ClothesAlgorithm()
    clothesAlgorithm.getWeatherScore(data)
    val clothingString = clothesAlgorithm.getOutfit(data, clothesAlgorithm.getRealTemp().toFloat())

    // Row for arranging text and avatar.
    Column(
        modifier = Modifier
            .height(450.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // City.
        Text(
            text = locationName,
            color = Color.White,
            fontFamily = Rubik,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier
                .absolutePadding(5.dp, 45.dp, 0.dp, 0.dp)
            // TODO: Place into row alongside pin icon with onClick. 
        )
        // Location within city.
        Text(
            text = "Location",
            color = Color.White,
            fontFamily = Rubik,
            fontWeight = FontWeight.Normal,
            fontSize = 25.sp,
        )

        Box(Modifier.fillMaxSize()) {
            Box(
                Modifier.align(Alignment.TopCenter).fillMaxHeight().width(
                    50.dp
                ).background(Color.Transparent)
            )
            val sole : List<String> = listOf("sko")
            for (ord in sole){
                if (ord in clothingString){
                    converterClothing.get(ord+"2")?.let { painterResource(it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Icon",
                            modifier = Modifier
                                .width(sizeDP)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
            // kunn skobunn
            Image(
                painter = painterResource(R.drawable.kropp),
                contentDescription = "Icon",
                modifier = Modifier
                    .width(sizeDP)
                    .align(Alignment.Center)
            )
            val shoes : List<String> = listOf("sko", "sneakers")
            for (ord in shoes){
                if (ord in clothingString){
                    converterClothing.get(ord)?.let { painterResource(it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Icon",
                            modifier = Modifier
                                .width(sizeDP)
                                .align(Alignment.Center)
                        )
                    }
                }
            }

            Box(
                Modifier.align(Alignment.TopCenter).fillMaxHeight().width(
                    50.dp
                ).background(Color.Transparent)
            )
            val pants : List<String> = listOf("bukse","skj_rt","regnbukse", "shorts")

            for (ord in pants){
                if (ord in clothingString){
                    converterClothing.get(ord)?.let { painterResource(it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Icon",
                            modifier = Modifier
                                .width(sizeDP)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
            /*

            Image(
                painter = painterResource(R.drawable.bubble),
                contentDescription = "Icon",
                modifier = Modifier
                    .width(sizeDP)
                    .align(Alignment.Center)
            )*/
            Box(
                Modifier.align(Alignment.TopCenter).fillMaxHeight().width(
                    50.dp
                ).background(Color.Transparent)
            )
            Image(
                painter = painterResource(R.drawable.h_r),
                contentDescription = "Icon",
                modifier = Modifier
                    .width(sizeDP)
                    .align(Alignment.Center)
            )
            val hatt : List<String> = listOf("lue", "solhatt", "pannebånd")
            for (ord in hatt){
                if (ord in clothingString){
                    converterClothing.get(ord)?.let { painterResource(it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Icon",
                            modifier = Modifier
                                .width(sizeDP)
                                .align(Alignment.Center)
                        )
                    }
                }
            }

            Box(
                Modifier.align(Alignment.TopCenter).fillMaxHeight().width(
                    50.dp
                ).background(Color.Transparent)
            )
            val top : List<String> = listOf("bobblejakke", "vinterjakke", "frakk", "tskjorte", "langermet", "ullgenser","kjole","genser"  )
            for (ord in top){
                if (ord in clothingString){
                    converterClothing.get(ord)?.let { painterResource(it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Icon",
                            modifier = Modifier
                                .width(sizeDP)
                                .align(Alignment.Center)
                        )
                    }
                }
            }

            Box(
                Modifier.align(Alignment.TopCenter).fillMaxHeight().width(
                    50.dp
                ).background(Color.Transparent)
            )
            Image(
                painter = painterResource(R.drawable.aniskt),
                contentDescription = "Icon",
                modifier = Modifier
                    .width(sizeDP)
                    .align(Alignment.Center)
            )
            Image(
                painter = painterResource(R.drawable.oyne),
                contentDescription = "Icon",
                modifier = Modifier
                    .width(sizeDP)
                    .align(Alignment.Center)
            )
            val scarf : List<String> = listOf("votter & skjerf","gummistøvler")
            for (ord in scarf){
                if (ord in clothingString){
                    converterClothing.get(ord)?.let { painterResource(it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Icon",
                            modifier = Modifier
                                .width(sizeDP)
                                .align(Alignment.Center)
                        )
                    }
                }
            }

            Box(
                Modifier.align(Alignment.TopCenter).fillMaxHeight().width(
                    50.dp
                ).background(Color.Transparent)
            )
            val sunglasses : List<String> = listOf("solbriller")
            for (ord in sunglasses){
                if (ord in clothingString){
                    converterClothing.get(ord)?.let { painterResource(it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Icon",
                            modifier = Modifier
                                .width(sizeDP)
                                .align(Alignment.Center)
                        )
                    }
                }
            }

            Box(
                    Modifier.align(Alignment.TopCenter).fillMaxHeight().width(
                        50.dp
                    ).background(Color.Transparent)
                    )
            val handThing : List<String> = listOf("flagg","gummist_ler","ol")
            for (ord in handThing){
                if (ord in clothingString){
                    converterClothing.get(ord)?.let { painterResource(it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Icon",
                            modifier = Modifier
                                .width(sizeDP)
                                .align(Alignment.Center)
                        )
                    }
                }
            }

            Image(
                painter = painterResource(R.drawable.arm),
                contentDescription = "Icon",
                modifier = Modifier
                    .width(sizeDP)
                    .align(Alignment.Center)
            )


            Box(
                Modifier.align(Alignment.TopCenter).fillMaxHeight().width(
                    50.dp
                ).background(Color.Transparent)
            )

            for (ord in top){
                if (ord in clothingString){
                    converterClothing.get(ord+"2")?.let { painterResource(it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Icon",
                            modifier = Modifier
                                .width(sizeDP)
                                .align(Alignment.Center)
                        )
                    }
                }
            }

            Box(
                Modifier.align(Alignment.TopCenter).fillMaxHeight().width(
                    50.dp
                ).background(Color.Transparent)
            )

            val mitten : List<String> = listOf("vott")

            for (ord in mitten){
                if (ord in clothingString){
                    converterClothing.get(ord)?.let { painterResource(it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Icon",
                            modifier = Modifier
                                .width(sizeDP)
                                .align(Alignment.Center)
                        )
                    }
                }
            }

        }

    }
}