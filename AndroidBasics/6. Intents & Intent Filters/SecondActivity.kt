package com.example.androidbascis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text

class SecondActivity: ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // whenever we want to add additional Activity to our app, we need register that in Android manifest(is used to register permissions needs. e.g. camera).
        setContent{
            Text(text = "Second Activity")
        }
    }
}