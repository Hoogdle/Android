// ConstraintLayout
// source : https://www.youtube.com/watch?v=FBpiOAiseD0&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=9

package com.example.jetpackcompose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

// if you have complex things of compose, constrain layout will be helpful
// in simple case, using row and column will be great way.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val constraints = ConstraintSet{
                // we can define our constraints in here.
                val greenBox = createRefFor("greenbox")
                val redBox = createRefFor("redbox")
                //val guideline = createGuidelineFromTop(0.5f) // set guideline

                constrain(greenBox){
                    // constrain greenBox with other things
                    top.linkTo(parent.top)
                    //top.linkTo(guideline) // green box will be gone to about middle
                    start.linkTo(parent.start)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }

                constrain(redBox){
                    top.linkTo(parent.top)
                    start.linkTo(greenBox.end) // red box will be right to our green box
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }

                createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed) //[Screen#2]
            }

            // till now we just create constrain set not layout(not visible)

            ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()){
                Box(modifier = Modifier
                    .background(Color.Green)
                    .layoutId("greenbox")) // refers to green box which we constrained.

                Box(modifier = Modifier
                    .background(Color.Red)
                    .layoutId("redbox"))
            } // [Screen#1]
        }
    }
}














