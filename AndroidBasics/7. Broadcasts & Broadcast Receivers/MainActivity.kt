// Broadcasts & Broadcast Receivers
// source : https://www.youtube.com/watch?v=HDVyFsFUuVg&list=PLQkwcJG4YTCSVDhww92llY3CAnc_vUhsm&index=7
package com.example.androidbascis
import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
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


// #Broadcast
// System-wide events that app can actually consume and receive.
// e.g. Suppose you are playing music player app. if u get the incoming phone call, our phone calling app
// could send broad-cast, and may be music player would be stopped and you accept the phone call

// we can make 'broad-cast' as a class.

// #static receiver
// e.g. when our android booted up, static receiver will launch.
// even our android not active or launched static method could do its work.
// it accounts for whole our app.
// it have to be register in 'AndroidManifest'

// dynamically receiver have to be register in Activity class(normally)

class MainActivity : ComponentActivity() {

    private val airPlaneModeReceiver = AirPlaneModeReceiver()
    private val testReceiver = TestReceiver()

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // #registerReceiver
        // register Broad-cast receiver inside our app, that will receive events.
        // below thing is called as 'Dynamic Broadcast Receiver'
        // it is dynamically register when it needs, and also dynamically unregister when it not needs.
        registerReceiver(
            airPlaneModeReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )


        // register receiver for other app.
        registerReceiver(
            testReceiver,
            IntentFilter("TEST_ACTION")
        )

    setContent {

        // suppose this setContent is other app's Content of MainActivity
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(onClick = {
                // send broad cast to our app!
                sendBroadcast(
                    Intent("TEST_ACTION")
                )
            }) {
                Text(text = "Send boradcast")
            }
        }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        // destroy receiver of airplane mode
        unregisterReceiver(airPlaneModeReceiver)
        // destroy receiver of other app
        unregisterReceiver(testReceiver)
    }
}




