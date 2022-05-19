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
import com.example.vaerklar.data.clothingItemNameToDrawable
import com.example.vaerklar.ui.theme.Rubik
import java.util.*


// The avatar + locationtext
// Should've been two separate components but we're lazy <3
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
                    .offset(15.dp, 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = text,
                )
                Icon(
                    painter = painterResource(R.drawable.dropdown),
                    contentDescription = "Åpne eller lukk dropdown",
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
        val sizeDP = 250.dp
        val clothesAlgorithm = ClothesAlgorithm()
        val clothingString = clothesAlgorithm.getWeatherScore(data, index)

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
                for (clothingItem in sole) {
                    if (clothingItem in clothingString){
                        clothingItemNameToDrawable[clothingItem + "2"]?.let { painterResource(it) }?.let {
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
                // Shoes
                Image(
                    painter = painterResource(R.drawable.body),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .width(sizeDP)
                        .align(Alignment.Center)
                )
                val shoes : List<String> = listOf("sko", "sneakers")
                var isShoes = false
                for (ord in shoes ) {
                    if (ord in clothingString && !isShoes){
                        isShoes = true
                        clothingItemNameToDrawable[ord]?.let { painterResource(it) }?.let {
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
                // Pants
                Box(
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(50.dp)
                        .background(Color.Transparent)
                )
                val pants : List<String> = listOf("bukse", "skj_rt", "regnbukse", "shorts")
                for (clothingItem in pants) {
                    if (clothingItem in clothingString){
                        clothingItemNameToDrawable[clothingItem]?.let { painterResource(it) }?.let {
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
                // Hat
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
                    painter = painterResource(R.drawable.hair),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .width(sizeDP)
                        .align(Alignment.Center)
                )
                val hat : List<String> = listOf("lue", "solhatt", "pannebånd")
                for (clothingItem in hat) {
                    if (clothingItem in clothingString){
                        clothingItemNameToDrawable[clothingItem]?.let { painterResource(it) }?.let {
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
                val top : List<String> = listOf("bobblejakke", "vinterjakke", "regnjakke", "jakke", "vinterjakke", "tskjorte", "langermet", "ullgenser", "kjole", "genser")
                var hasTop = false
                for (ord in top){
                    if (ord in clothingString && !hasTop){
                        hasTop = true
                        clothingItemNameToDrawable[ord]?.let { painterResource(it) }?.let {
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
                // Scarf/Mittens
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
                    painter = painterResource(R.drawable.face),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .width(sizeDP)
                        .align(Alignment.Center)
                )
                Image(
                    painter = painterResource(R.drawable.eyes),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .width(sizeDP)
                        .align(Alignment.Center)
                )
                val scarf: List<String> = listOf("votter & skjerf")
                for (clothingItem in scarf) {
                    if (clothingItem in clothingString){
                        clothingItemNameToDrawable[clothingItem]?.let { painterResource(it) }?.let {
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
                // Special shoes. Weather specific
                Box(
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
                )
                val specialShoe: List<String> = listOf("gummistøvler", "vintersko")
                for (ord in specialShoe){
                    if (ord in clothingString && !isShoes){
                        isShoes = true
                        clothingItemNameToDrawable[ord]?.let { painterResource(it) }?.let {
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
                // Sunglasses
                Box(
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
                )
                val sunglasses: List<String> = listOf("solbriller")
                for (clothingItem in sunglasses){
                    if (clothingItem in clothingString){
                        clothingItemNameToDrawable[clothingItem]?.let { painterResource(it) }?.let {
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
                // Misc items
                Box(
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
                )
                val handItems : List<String> = listOf("flagg","ol", "paraply")
                var isHolding = false
                for (ord in handItems){
                    if (ord in clothingString && !isHolding){
                        isHolding = true
                        clothingItemNameToDrawable[ord]?.let { painterResource(it) }?.let {
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
                //  Coat arm?
                Box(
                    Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxHeight()
                        .width(
                            50.dp
                        )
                        .background(Color.Transparent)
                )
                var hasArm = false
                for (ord in top){
                    if (ord in clothingString && !hasArm){
                        hasArm = true
                        clothingItemNameToDrawable[ord + "2"]?.let { painterResource(it) }?.let {
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

                // Dropdown text description of outfit recommendation
                if (flag == 1) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd))
                    {
                        DropDown(
                            text = "",
                            modifier = Modifier.absolutePadding(255.dp, 50.dp, 0.dp, 0.dp)
                        ) {
                            var outfitTextRepresentation = ""
                            for (item in clothingString) {
                                if (item != "") {
                                    outfitTextRepresentation += item.replaceFirstChar {
                                        if (it.isLowerCase()) it.titlecase(
                                            Locale.getDefault()
                                        ) else it.toString()
                                    } + "\n"
                                }
                            }
                            Text(
                                color = Color.White,
                                text = outfitTextRepresentation,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .offset(0.dp, 20.dp)
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
