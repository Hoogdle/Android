package com.example.jetpackcompose.ui.theme

import android.R.attr.name
import android.R.attr.text
import android.annotation.SuppressLint
import android.icu.text.AlphabeticIndex
import android.text.Highlights
import androidx.activity.SystemBarStyle.Companion.light
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.ui.standardQuadFromTo

@Composable
fun HomeScreen(){
    Box(modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()
    ){
        Column{
            // place all about content here.
            GreetingSection()
            ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeatureSection(
                features = listOf(
                    Feature(
                        title = "Sleep meditation",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Feature(
                        title = "Tips for sleeping",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Feature(
                        title = "Night island",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    Feature(
                        title = "Calming Sounds",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    )
                )
            )
        }

        // below above column we will put 'navigation view'
        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Meditate", R.drawable.ic_bubble),
            BottomMenuContent("Sleep", R.drawable.ic_moon),
            BottomMenuContent("Music", R.drawable.ic_music),
            BottomMenuContent("Profile", R.drawable.ic_profile),
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}

// Top, Greeting Section
@Composable
fun GreetingSection(
    name: String = "Kim Taeyeong"
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, // put each contents to typical side.
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Column(
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Good morning, $name",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                fontWeight = FontWeight.Light,
            )
        }
        Icon(painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipSection(
    chips: List<String>
){
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    // Scrollable Row
    // won't take any parameter
    LazyRow {
        items(chips.size /* size of item */){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                .clickable {
                    selectedChipIndex = it
                }
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if(selectedChipIndex == it) ButtonBlue
                    else DarkerButtonBlue
                )
                .padding(15.dp) // padding relate to text
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}


@Composable
fun CurrentMeditation(
    color: Color = LightRed
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp) // padding for text
            .fillMaxWidth()
    ){
        Column {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontSize = 24.sp
            )
            Text(
                text = "Meditation, 3-10 min",
                style = MaterialTheme.typography.bodyMedium,
                color = TextWhite
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ){
            Icon(painter = painterResource(id = R.drawable.ic_play),
                 contentDescription = "Play",
                 tint = Color.White,
                 modifier = Modifier.size(16.dp))
        }

    }
}


@Composable
fun FeatureSection(features: List<Feature>){
     Column(modifier = Modifier.fillMaxWidth()) {
         Text(
             text = "Features",
             style = MaterialTheme.typography.headlineLarge,
             fontWeight = FontWeight.Bold,
             modifier = Modifier.padding(15.dp),
             color = Color.White,
             fontSize = 30.sp
         )

         LazyVerticalGrid(
             columns = GridCells.Fixed(2),
             contentPadding = PaddingValues(start = 7.5.dp, end= 7.5.dp, bottom = 100.dp),
             modifier = Modifier.fillMaxHeight()
         ) {
             items(features.size){
                FeatureItem(feature = features[it])
             }
         }
     }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightsColor: Color = ButtonBlue, // button's highlight color
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0 // select first item by default
){
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround, // put same amount space to each items
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ){
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightsColor = activeHighlightsColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
                ) {

                // if we click the item, this block will opt.
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightsColor: Color = ButtonBlue, // button's highlight color
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable{
            onItemClick()
        }
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if(isSelected) activeHighlightsColor else Color.Transparent)
                .padding(10.dp)
        ){
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if(isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun FeatureItem(
    feature: Feature
){
    BoxWithConstraints (
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f) // means "we have square!", it will assign same value(height) to width.
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // #For first Curve

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f) // coordinate of first point
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f) // point2
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f) // point3
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f) // point4
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat()) // point5

        val mediumColoredPath = Path().apply{
            // start from point1's x and y.
            moveTo(mediumColoredPoint1.x,mediumColoredPoint1.y)

            // instead this, we use implemented function by own.
//            quadraticTo(
//                // define to get its going
//                mediumColoredPoint2.x,
//                mediumColoredPoint2.y,
//
//                // how the curve looks like
//                (mediumColoredPoint1.x + mediumColoredPoint2.x) /2f,
//                (mediumColoredPoint1.y + mediumColoredPoint2.y) /2f,
//            )

            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)

            // draw normal line(straight line)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f) // parameter refers to x, y
            lineTo(-100f, height.toFloat() + 100f)
            close()

            // we can also do above things by 'vector graphic'


        }

        // #For second Curve
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
        ){
            Text(
                text = feature.title,
                fontSize = 17.sp,
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                lineHeight = 26.sp, // space between lines
                modifier = Modifier.align(Alignment.TopStart), // set to the top-left corner
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart) // icon start from bottom
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable{
                        // Handle the click
                    }
                    .align(Alignment.BottomEnd) // put to Bottom-Right-Corner
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}