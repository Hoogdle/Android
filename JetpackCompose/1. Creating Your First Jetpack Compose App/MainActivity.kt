// source : https://www.youtube.com/watch?v=cDabx3SjuOY&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC

package com.example.jetpackcompose

// what is JetPackCompose?
// 1. new way of designing native android apps using kotlin(no more XML)
// 2. we don't need to things that in res directory e.g. layout, theme, values, string.
// 3. now we do everything in kotlin!
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // we don't need use setContentView which is for using layout id. because we don't need 'layout file' or 'xml file' anymore.

        // every Activity have setContent function which have all the components or the composable.
        setContent {
            Greeting(name = "Kim Taeyeong")
        }
    }
}

// @Composable
// Annotation of Composable is used for View!
// We can separate our UI into single Component
@Composable // it is a function that composable. we can use it in setContent which in MainActivity
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    ) // Text is also composable, built in jetpack compose
}


// @Preview
// we can preview our composable.
// we can see our preview in split tab which in right-up corner
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
        Greeting("Kim taeyeong")
}