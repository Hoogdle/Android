// Foreground Services
// source : https://www.youtube.com/watch?v=YZL-_XJSClc&list=PLQkwcJG4YTCSVDhww92llY3CAnc_vUhsm&index=8

// #Services
// Component, just like Activity, But it doesn't have any UI
// Runs in the background

// Normal Service vs Foreground Service
// Normal Service : user don't know app is running
// Foreground Service : user know it is running. e.g. music player

// Foreground Service is usually used nowadays.


package com.example.androidbascis
import android.Manifest
import android.R
import android.R.attr.onClick
import android.R.attr.text
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat


// *** Below Code have some problem. plz just have reference it with easy-mind ***
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }

    setContent {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Button(onClick={
                Intent(applicationContext, RunningService::class.java).also{
                    it.action = RunningService.Actions.START.toString()
                    startService(it)
                }
            }){
                Text(text = "Start run")
            }

            Button(onClick={
                Intent(applicationContext, RunningService::class.java).also{
                    it.action = RunningService.Actions.STOP.toString()
                    startService(it)
                }
            }){
                Text(text = "Stop run")
            }
            }
        }
    }
}




