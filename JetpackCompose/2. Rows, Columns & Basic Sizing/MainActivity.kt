// Rows, Columns & Basic Sizing
// source : https://www.youtube.com/watch?v=rHKeRWK3zL4&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=2

package com.example.jetpackcompose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            // Why Row | Column?
            // they will stack each other!
            /*Text("Hello")
            Text("World")*/
            // -> we need either row or column
            // it similar to linear layout in xml

            Column {
                Text("Hello")
                Text("World")
                Row {
                    Text("Left")
                    Text("Right") // (left:Left, right:Right)
                }
            } // these items arrange well!(top:Hello, bottom:World)


            // we can hugh option for Column by 'ctrl+O'

            // [Alignment vs Arrangement]
            // before drill this concept, we need knowledge that Column and Row have 'main-axis' and 'cross-axis'
            // just be to know that 'HorizontalAlignment' and 'VerticalArrangement'

            // [Main-Axis vs Cross-Axis]
            // Main-Axis : Axis that new items are basically stacked. like drive car in the road. e.g. in Column Vertical, in Row Horizontal.
            // Cross-Axis : like cross the cross-walk in the road. e.g. in Column Horizontal, in Row Vertical.

            // [horizontalAlignment]
            // we can just position these items and align these in our column or row
            // because compose make this column and row only as wide and as high as it needs to be
            // for resolve this, we need modifier.

            // by modifier, we can understand alignment here and scale the size

            // because our "Hello" and "World" have wide that conditioned with content, horizontalAlignment dose not work [See Screen#1]
            Column(
                modifier = Modifier.background(Color.Green),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Hello")
                Text("World")
            }


            // now we can those word arranged in center. [Screen#2]
            // by Modifier.fillMaxSize() we can fill Text to maximum size.
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("Hello")
                Text("World")
            }


            // add verticalArrangement [Screen#3]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green),
                // Alignment.Start == left, Alignment.End = right
                horizontalAlignment = Alignment.CenterHorizontally,
                // Arrangement.SpaceBetween => Hello(top), Middle(middle), World(bottom)
                // Arrangement.SpaceEvenly => each text are arrange with gaps which have same space.
                // Arrangement.SpaceAround => seems like SpaceEvenly, but space which in top and bottom is half of space which in middle area.
                verticalArrangement = Arrangement.Bottom
            ){
                Text("Hello")
                Text("Middle")
                Text("World")
            }


            // For Row, axis is changed, that result in 'horizontalArrangement' and 'verticalAlignment' [Screen#4]
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Hello")
                Text("Middle")
                Text("World")
            }


            //Change Modifier.fillMaxSize()'s parameter [Screen#5]
            Row(
                modifier = Modifier
                    .fillMaxSize(0.5f)
                    .background(Color.Green),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Hello")
                Text("Middle")
                Text("World")
            }

            // just fill up the parameters which are width and height in the Modifier
            Row(
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp)
                    // instead above, below parameters also possible
                    // fillMaxWidth()
                    // fillMaxHeight()
                    .background(Color.Green),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Hello")
                Text("Middle")
                Text("World")
            }

        }






























    }
}

