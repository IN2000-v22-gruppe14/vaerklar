package com.example.vaerklar.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaerklar.R
import com.example.vaerklar.data.ClothesAlgorithm
import com.example.vaerklar.data.WeatherData
import com.example.vaerklar.ui.theme.Rubik
import java.util.*


// The avatar, alongside location.
class Avatar {

    @Composable
    fun DropDown(
        text: String,
        modifier: Modifier = Modifier,
        initiallyOpened: Boolean = false,
        content: @Composable () -> Unit
    ) {

        var isOpen by remember {
            mutableStateOf(initiallyOpened)
        }

        val alpha = animateFloatAsState(
            targetValue = if (isOpen) 1f else 0f,
            animationSpec = tween(
                durationMillis = 300
            )
        )

        val rotateX = animateFloatAsState(
            targetValue = if (isOpen) 0f else -90f,
            animationSpec = tween(
                durationMillis = 300
            )
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .offset(15.dp, 0.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = text,
                )
                Icon(
                    painter = painterResource(R.drawable.dropdown),
                    contentDescription = "Open or close the drop down",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable {
                            isOpen = !isOpen
                        }
                        .scale(1f, if (isOpen) -1f else 1f)
                        .size(15.dp)
                )
            }
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(400.dp)
                    .graphicsLayer {
                        transformOrigin = TransformOrigin(0.5f, 0f)
                        rotationX = rotateX.value
                    }
                    .alpha(alpha.value)
            ) {
                content()
            }
        }
    }

    @Composable
    fun avatarMain(data: WeatherData?, locationName: String, index : Int, flag: Int, date: String): MutableList<String>{
        val converterClothing = hashMapOf(
            // CLOTHES
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
            "vinterjakke2" to R.drawable.frakk,
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
        )

        val sizeDP = 250.dp
        val clothesAlgorithm = ClothesAlgorithm()
        val clothingString = clothesAlgorithm.getWeatherScore(data, index)
        //val clothingString = clothesAlgorithm.getOutfit(data, clothesAlgorithm.getRealTemp().toFloat())


        // Row for arranging text and avatar.
        Column(
            modifier = Modifier
                .height(450.dp),
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
            )

            // Used to show date of day clicked in popup
            Text(
                text = date,
                color = Color.White,
                fontFamily = Rubik,
                fontWeight = FontWeight.Normal,
                fontSize = 25.sp,
            )

            Box(Modifier.fillMaxSize()) {
                Box(
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
                )
                val sole : List<String> = listOf("sko")
                for (ord in sole){
                    if (ord in clothingString){
                        converterClothing[ord+"2"]?.let { painterResource(it) }?.let {
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
                var shoesTrue = 0
                for (ord in shoes ){
                    if (ord in clothingString && shoesTrue == 0){
                        shoesTrue = 1
                        converterClothing[ord]?.let { painterResource(it) }?.let {
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
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
                )
                val pants : List<String> = listOf("bukse","skj_rt","regnbukse", "shorts")

                for (ord in pants){
                    if (ord in clothingString){
                        converterClothing[ord]?.let { painterResource(it) }?.let {
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
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
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
                        converterClothing[ord]?.let { painterResource(it) }?.let {
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
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
                )
                val top : List<String> = listOf("bobblejakke","regnjakke", "vinterjakke", "frakk", "tskjorte", "langermet", "ullgenser","kjole","genser"  )
                var plagg = 0
                for (ord in top){
                    if (ord in clothingString  && plagg == 0){
                        plagg = plagg + 1
                        converterClothing[ord]?.let { painterResource(it) }?.let {
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
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
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
                val scarf : List<String> = listOf("votter & skjerf")
                for (ord in scarf){
                    if (ord in clothingString){
                        converterClothing[ord]?.let { painterResource(it) }?.let {
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
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
                )

                val specialShoe : List<String> = listOf("gummistøvler", "vintersko")
                for (ord in specialShoe){
                    if (ord in clothingString && shoesTrue == 0){
                        shoesTrue = 1
                        converterClothing[ord]?.let { painterResource(it) }?.let {
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
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
                )
                val sunglasses : List<String> = listOf("solbriller")
                for (ord in sunglasses){
                    if (ord in clothingString){
                        converterClothing[ord]?.let { painterResource(it) }?.let {
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
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
                )
                var handThingTrue = 0
                val handThing : List<String> = listOf("flagg","ol", "paraply")
                for (ord in handThing){
                    if (ord in clothingString && handThingTrue == 0){
                        handThingTrue = 1
                        converterClothing[ord]?.let { painterResource(it) }?.let {
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
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
                )
                var plagg2 = 0
                for (ord in top){
                    if (ord in clothingString && plagg2 == 0){
                        plagg2 = plagg2 + 1
                        converterClothing[ord+"2"]?.let { painterResource(it) }?.let {
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
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
                )

                if(flag == 1){
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd))
                    {
                        DropDown(

                            text = "",
                            modifier = Modifier.absolutePadding(255.dp, 50.dp, 0.dp, 0.dp)
                        ) {

                            var theString = ""
                            for(i in clothingString){
                                if(i != ""){
                                    theString += i.replaceFirstChar {
                                        if (it.isLowerCase()) it.titlecase(
                                            Locale.getDefault()
                                        ) else it.toString()
                                    } + "\n"
                                }
                            }
                            Text(
                                color = Color.White,
                                text = theString,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .absolutePadding(15.dp, 15.dp, 15.dp, 15.dp)
                                    .width(250.dp)
                                    .height(500.dp)
                                    .background(Color.Transparent)
                            )
                        }
                    }
                }

            }

        }
        return clothingString
    }
}
