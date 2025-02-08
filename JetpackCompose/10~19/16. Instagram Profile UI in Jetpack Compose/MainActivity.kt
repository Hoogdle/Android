// Instagram Profile UI in Jetpack Compose - Android Studio Tutorial
// source : https://www.youtube.com/watch?v=Kw4_i4l5y4s&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=17

package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jetpackcompose.instagramui.ProfileScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ProfileScreen()
        }
    }
}





