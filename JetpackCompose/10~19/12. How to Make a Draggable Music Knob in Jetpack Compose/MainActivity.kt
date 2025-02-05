// How to Make a Draggable Music Knob in Jetpack Compose
// source : https://www.youtube.com/watch?v=TOflUdgx4pw&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=13

package com.example.jetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
                    .background(Color.Black)
            ){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                        .padding(30.dp)
                ){
                    var volume by remember {
                        mutableStateOf(0f)
                    }

                    var barCount = 20
                    MusicKnob(
                        modifier = Modifier.size(100.dp)
                    ){
                        volume = it
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    VolumeBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp),
                        activeBars = (barCount * volume).roundToInt(),
                        barCount = barCount
                    )
                }
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBars: Int = 0,
    barCount: Int = 10
){
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWidth = remember {
            constraints.maxWidth / (2f * barCount)
        }
        Canvas(modifier = modifier){
            for (i in 0 until barCount){
                drawRoundRect(
                    color = if (i in 0..activeBars) Color.Green else Color.DarkGray,
                    topLeft = Offset(i * barWidth * 2f + barWidth/2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitimgAngle: Float = 25f, // circle angle start from y-axis, it means 25 degree clock-wise
    onValueChange: (Float) -> Unit
){
    var rotation by remember {
        mutableStateOf(limitimgAngle)
    }

    var touchX by remember {
        mutableStateOf(0f)
    }

    var touchY by remember {
        mutableStateOf(0f)
    }

    var centerX by remember {
        mutableStateOf(0f)
    }

    var centerY by remember {
        mutableStateOf(0f)
    }

    Image(
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "Music knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned{
                // boundsInWindow : boundaries and the position of our image relative to our whole screen.
                val windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width / 2f
                centerY = windowBounds.size.height / 2f
            }
            .pointerInteropFilter{event ->
                // relate to touch function
                touchX = event.x
                touchY = event.y
                // with center's coordinate, touch's coordinate, we can calculate corresponding angle.
                // by trigonometry(sin, cos ,tan)
                // more detail, we use 'tan' (inverse of tan to get angle), because we have data of x and y
                val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()// arc tan,
                // actually it use radian, not degree, so we have to change it match format.

                // check touch event is correct one.
                when(event.action){
                    // only rotate when it have 'down' or 'up' action in event.
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_UP -> {
                        // excpet for limited space
                        if(angle !in -limitimgAngle..limitimgAngle){
                            // angle degree : 0~180 -> -180~0, so we have to change it.
                            val fixedAngle = if(angle in -180f..-limitimgAngle){
                                360f + angle
                            } else {
                                angle
                            }
                            rotation = fixedAngle

                            val percent = (fixedAngle - limitimgAngle) / (360f - 2 * limitimgAngle)
                            onValueChange(percent)
                            true
                        } else false // we don't handle it if it be in limited degree
                    }
                    else -> false
                }
            }.rotate(rotation)
    )
}


// there is a little problem, so plz see original code.
//@Composable
//fun MusicKnob(
//    modifier: Modifier = Modifier,
//    limitingAngle: Float = 25f,
//    onValueChange: (Float) -> Unit
//) {
//    var rotation by remember {
//        mutableStateOf(limitingAngle)
//    }
//    var touchX by remember {
//        mutableStateOf(0f)
//    }
//    var touchY by remember {
//        mutableStateOf(0f)
//    }
//    var centerX by remember {
//        mutableStateOf(0f)
//    }
//    var centerY by remember {
//        mutableStateOf(0f)
//    }
//
//    Image(
//        painter = painterResource(id = R.drawable.music_knob),
//        contentDescription = "Music knob",
//        modifier = modifier
//            .fillMaxSize()
//            .onGloballyPositioned {
//                val windowBounds = it.boundsInWindow()
//                centerX = windowBounds.size.width / 2f
//                centerY = windowBounds.size.height / 2f
//            }
//            .pointerInteropFilter { event ->
//                touchX = event.x
//                touchY = event.y
//                val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()
//
//                when (event.action) {
//                    MotionEvent.ACTION_DOWN,
//                    MotionEvent.ACTION_MOVE -> {
//                        if (angle !in -limitingAngle..limitingAngle) {
//                            val fixedAngle = if (angle in -180f..-limitingAngle) {
//                                360f + angle
//                            } else {
//                                angle
//                            }
//                            rotation = fixedAngle
//
//                            val percent = (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
//                            onValueChange(percent)
//                            true
//                        } else false
//                    }
//                    else -> false
//                }
//            }
//            .rotate(rotation)
//    )
//}