// Making a Meditation UI With Jetpack Compose
// source : https://www.youtube.com/watch?v=g5-wzZUnIbQ&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=14

package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jetpackcompose.ui.theme.HomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            HomeScreen()
        }
    }
}

