// #State
// source : https://www.youtube.com/watch?v=s3m1PSd7VWc&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=6

// #What is State?
// Describe how our given UI looks at a moment.
// in real app, there is a 'dynamic content', which means user can directly interact with our content, which could change its appearance.
// and State describe "how our UI looks at a specific moment?"
// e.g. counter and button : if push button, counter's count growing up, change its value visually(1->2->3...), counter is 'state of button'
// such process, that redrawing count, called as 'Recomposing'


// we are going to make app, which have button and if push the button background color be changed.
// in this case, 'background' is a 'state'
package com.example.jetpackcompose


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // #setContent : always entry point of our composable
        setContent{
            Column(modifier = Modifier.fillMaxSize()) {


                // [2] change another box's color
                val color = remember {
                    mutableStateOf(Color.Yellow)
                }

                ColorBox(
                    // #weight : can use only column or row
                    // by 'weight' we can fill the space according to the parameter
                    // in this case, weight is 1f vs 1f, so that screen be filled by ColorBox and Box with 5:5 ratio in Vertical axis.
                    Modifier.weight(1f).fillMaxSize()
                ){
                    // put the code for lambda function, which name is 'updateColor'
                    color.value = it
                }
                Box(modifier = Modifier
                    // using value of ColorBox's value
                    .background(color.value)
                    .weight(1f)
                    .fillMaxSize())
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit// define callback function, change parent composable
){

    // using state

    // #Problem1(not optimal)
    //val color = mutableStateOf(Color.Yellow)

    // #Solution1(using 'remember')
    // by 'remember' color will remember last composition. so it won't reset that value on every recomposition.

    // [1] change its color

//    val color = remember {
//        mutableStateOf(Color.Yellow)
//    }

    Box(modifier = modifier
        .background(Color.Red) // refer Color.Yellow, which in the color
        .clickable{
            // if click -> reassign the color of background
            // more detail, Composable recompose when button get clicked
            // that means, it redraw it on our UI
            // problem here is "val color = ~~~" will be opted when button get clicked. and this is not optimal

            // [1] change its color
//            color.value = Color(
//                Random.nextFloat(), // Random for Red
//                Random.nextFloat(), // Random for Green
//                Random.nextFloat() // Random for Blue
//            )

            // [2] change another's color
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat()
                )
            )
        }
    )
}




















