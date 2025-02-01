// source : https://www.youtube.com/watch?v=SJw3Nu_h8kk&list=PLQkwcJG4YTCSVDhww92llY3CAnc_vUhsm

// 1. Activity : kind of container for one or multiple screens in our apps.
// 2. Activity contains "active on the screen, background, entry points, etc.."
// 3. In past there were one activity per one screen. but with Jectpack Compose, we can use only 'One Activity' for whole app.



package com.example.androidbascis

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
import com.example.androidbascis.ui.theme.AndroidBascisTheme

// MainActivity serve entry point for our application.
// starting point of application
class MainActivity : ComponentActivity() {
    // by 'onCreate' our Activity is build.
    // and if it's not used anymore, be destroyed
    // [onCreate]
    // 1. initialize variables.
    // 2. set the views or UI.
    // 3. etc
    // even be onCreate, user cannot see anything on the screen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate()")
        enableEdgeToEdge()
        setContent {
            AndroidBascisTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    // for debugging
    override fun onStart() {
        super.onStart()
        println("onStart()")
    }

    override fun onResume() {
        super.onResume()
        println("onResume()")
    }

    override fun onPause() {
        super.onPause()
        println("onPause()")
    }

    override fun onStop() {
        super.onStop()
        println("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart()")
    }
}

// LifeCycle
// 1. onCreate() : initialize variables, set the view or UI. user cannot see any screen.
// 2. onStart() : state that screen is visible to user. but can't interact yet.
// 3. onResume() : in the foreground, which means user can interact with it(screen)
// 4. onPause() : activity don't use at that time(go to background). e.g. dialog upon the screen. after finished, go back to the 'onResume()'
// 5. onStop() : activity is not visible to the user anymore. e.g. user navigate to 'totally' another screen.
// 6. onRestart() : user comes back to that activity.
// 7. onDestroy() : our Activity totally, entirely destroyed. for get it back on screen, we need to create new one.

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidBascisTheme {
        Greeting("Android")
    }
}