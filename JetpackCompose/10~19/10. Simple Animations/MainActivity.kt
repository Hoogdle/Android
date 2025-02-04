// Simple Animations
// source : https://www.youtube.com/watch?v=trVmP1rw0uw&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=11

package com.example.jetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    @SuppressLint("RememberReturnType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            var sizeState by remember {mutableStateOf(200.dp)}
            val size by animateDpAsState(
                // we want to animate toward our sizeState
                // it will smoothly size up our button until reach value of sizeState
                targetValue = sizeState,

                // control property of 'time' in animations.
//                tween(
//                    // act during 3 second
//                    durationMillis = 3000,
//                    // delay of star time
//                    delayMillis = 300,
//                    easing = LinearOutSlowInEasing
//                )

                // animation like 'spring'!
//                spring(
//                    Spring.DampingRatioHighBouncy // bouncy animation
//                )

                // choose that at which time should our animation which value and which easing
//                keyframes {
//                    durationMillis = 5000
//                    sizeState at 0 with LinearEasing // 0~1 LinearEasing
//                    sizeState * 1.5f at 1000 with FastOutLinearInEasing // 1~5 FastOutLinearInEasing
//                    sizeState * 2f at 5000
//                }

                // for example 'color animating'
                tween(
                    durationMillis = 3000
                )
            )

            // animation which do infinitely
            val infiniteTransition = rememberInfiniteTransition()
            val color by infiniteTransition.animateColor(
                initialValue = Color.Red,
                targetValue = Color.Green,
                animationSpec = infiniteRepeatable(
                    tween(durationMillis = 2000),
                    repeatMode = RepeatMode.Reverse
                )
            )
            Box(modifier = Modifier
                //.size(sizeState) // before
                .size(size) // for animate(smoothly increase!)
                //.background(Color.Red), // before
                .background(color),
                contentAlignment = Alignment.Center){
                Button(onClick = {
                    // initial screen [Screen#1]
                    // after button clicked [Screen#2]
                    sizeState += 50.dp
                }){
                    Text("Increase Size")
                }
            }
        }
    }
}

