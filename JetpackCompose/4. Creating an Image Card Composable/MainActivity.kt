// #Creating an Image Card Composable
// source : https://www.youtube.com/watch?v=KPVoQjwmWX4&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=4

// review lecture till now and introduce new things.
// pooh.png is included in drawable folder.

package com.example.jetpackcompose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // #setContent : always entry point of our composable
        setContent{
            val painter = painterResource(id = R.drawable.bigpooh) // get the img source
            val description = "Hello pooh, who is sleep on my bed."
            val title = "World Cutie bear Pooh!"

            Box(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(16.dp,16.dp)){
                // we can reuse ImageCard for representing other card!
                ImageCard(
                    painter = painter,
                    contentDescription = description,
                    title = title)
            }
        }
    }
}

// naming convention for composable function is "start with capital letter"

// which data actually we need from the outside?
@Composable
fun ImageCard(
    painter: Painter, // #painter : allow us to use image from img resources.
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.fillMaxWidth(), //applied parameter's modifier to Card.
        shape = RoundedCornerShape(15.dp), // trim edge to round.
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp) //give little shadow
    ){
        // #Box
        // make features as stacked and give useful functions.
        // normal container we can put composable into this.
        // each item we appended will be 'stacked' each other.(of course we can align these item)
        Box(modifier = Modifier.height(200.dp)){
            // in Box, with default option, all the stuff will be stacked each other
            // first items will be most bottom of our stacks.
            // so before put the items, make ordering about sequence of items which be putted in box.

            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop /* center crop */
            )

            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 300f
                    )
                )) // #brush : apply gradient

            // because we want to align only text(not other items), we have to make another box which contains Text
            // in other words, by Box we can align Text(only this)
            Box(
                modifier = Modifier.fillMaxSize().padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ){
                Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp)) // sp: prefer to font size. it scales with user's font size preference
            }

        }
    }
}





















