// Modifiers
// source : https://www.youtube.com/watch?v=XCuC_p3E0qo&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=3


// #Modifier
// we can use Modifier to any composable in jetpack Compose. e.g. text, column, row, img etc..
// we can make composable as clickable and draggable and zoomable and scrollable by Modifier

package com.example.jetpackcompose


import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            //[#Screen1]
            Column(modifier = Modifier
                .background(Color.Green)
                .fillMaxHeight(0.5f)
                .width(300.dp)){
                Text("Hello")
                Text("World")
            }


            //by 'requiredWidth()' we can force the width to set for size which we want to be.
            Column(modifier = Modifier
                .background(Color.Green)
                .fillMaxHeight(0.5f)
                // by 600dp, horizontal space of screen filled with green(in fact, 600dp is bigger that our screen) [#Screen2]
//                .width(600.dp)
                // by requiredWidth() we can fill the screen with 'real' 600dp. so that "Hello\nWorld" text be gone to the left space. [#Screen3]
                .requiredWidth(600.dp)){
                Text("Hello")
                Text("World")
            }


            // #padding : push(in fact fill the margin with pad) our Container. [#Screen4]
            Column(modifier = Modifier
                .background(Color.Green)
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                // of course we can pad it with not all area(only to horizontal, vertical is possible)
                // e.g. padding(top=50.dp)
                .padding(16.dp)){
                Text("Hello")
                Text("World")
            }


            // #offset : same function as padding, but not push other element. like change its coordinate
            // #Spacer : responsible to make empty(extra) space.
            // actually all of composable is started from 'top-left'. by 'offset' we can change its start point.[#Screen5]
            Column(modifier = Modifier
                .background(Color.Green)
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .padding(16.dp)){
                // we can use modifier directly onto our composable!
                Text("Hello", modifier = Modifier
                    .offset(50.dp,20.dp)) // change the start coordinate!(it could cause overlap!)
                Spacer(modifier = Modifier.height(50.dp)) // insert empty composable between two texts.[#Screen6]
                Text("World")
            }


            // #border : surround our column container
            Column(modifier = Modifier
                .background(Color.Green)
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .border(5.dp, Color.Magenta) // [#Screen7]
            ){
                Text("Hello")
                Spacer(modifier = Modifier.height(50.dp))
                Text("World")
            }

            // #mutiple border(method)
            // applied function's sequence is very important because it could effect different results.
            // and we can use same modifier's method multiple time.
            Column(modifier = Modifier
                .background(Color.Green)
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .border(5.dp, Color.Magenta)
                .padding(5.dp)
                .border(5.dp, Color.Blue)
                .padding(5.dp)
                .border(5.dp, Color.Red)
                .padding(5.dp) //[#Screen8]
                // and actually above process is implemented too much complicated in XML!, wonderful JetPack Compose!
            ){
                Text("Hello")
                Spacer(modifier = Modifier.height(50.dp))
                Text("World")
            }



            // #we can do above process directly to member of container. [#Screen9]
            Column(modifier = Modifier
                .background(Color.Green)
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
            ){
                Text("Hello", modifier = Modifier
                    .border(5.dp, Color.Yellow)
                    .padding(5.dp)
                    .offset(20.dp, 20.dp)
                    .border(5.dp, Color.Black)
                    .padding(5.dp))
                Spacer(modifier = Modifier.height(50.dp))
                Text("World")
            }


            // #clickable
            Column(modifier = Modifier
                .background(Color.Green)
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
            ){
                // there is many option => Modifier.zoomable, Modifier.scrollable, Modifier.draggable
                Text("Hello", modifier = Modifier.clickable{
                    // by this lambda function(put in code in here), operate next step when user click this button
                    // and we can choose 'click-effect'
                })
                Spacer(modifier = Modifier.height(50.dp))
                Text("World")
            }



        }
    }
}



















