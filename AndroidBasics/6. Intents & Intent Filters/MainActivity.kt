// Intents & Intent Filters
// source : https://www.youtube.com/watch?v=2hIY1xuImuQ&list=PLQkwcJG4YTCSVDhww92llY3CAnc_vUhsm&index=6

// envelop which is used to transfer an intention of my app.
// transfer to a different Android components. e.g. Activity.
// It just for communicating with a different Android Component.

// # Explicit Intents vs Implicit Intents

// #Explicit Intents
// just use our app's activity or other app that installed in our device.

// #Implicit Intents
// just specify action something i want to do with that intent
// Android will choose which app available to do such intent, and show up those app to user.
// User will choose the application he want.

package com.example.androidbascis

import android.R.attr.type
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import org.jetbrains.annotations.Async
import kotlin.jvm.java


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ImageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // edit gradle for adding coil-kt : coil compose for using AsyncImage(which is useful to deal with uri and img)
                viewModel.uri?.let{
                    AsyncImage(
                        model = viewModel.uri,
                        contentDescription = null
                    )
                }




                // we want to create intent with intention to launch our additional Activity if we clicked button.

                // even other app's activity(e.g.youtube), we can use it!
                // when we want to launch different app that installed in our device, we need its 'package name'.
                Button(onClick = {

//                    Intent(applicationContext, SecondActivity::class.java).also{
//                        startActivity(it)
//                    } [Screen#1]

                    // for using intent for youtube
                    // 'Action main' means "I want to launch the Main Activity of that app."



//                    Intent(Intent.ACTION_MAIN).also{
//                        // set the package name of youtube.
//                        // by use 'ADB' we can get the youtube's package name
//                        it.`package` = "com.google.android.youtube"
//
//                        // check if app in installed in devices (way1)
////                        if(it.resolveActivity(packageManager) != null){
////                            startActivity(it)
////                        }
//
//                        // (way2)
//                        try{
//                            startActivity(it)
//                        } catch(e: ActivityNotFoundException){
//                            e.printStackTrace()
//                        }
//                    }



                    // #Implicit Intent
                    val intent = Intent(Intent.ACTION_SEND).apply{
                        // we want to send below type of data.
                        type = "text/plain"

                        // we want to send some parameters to that intent
                        // gmail will receive below data
                        putExtra(Intent.EXTRA_EMAIL, arrayOf("text@text.com"))
                        putExtra(Intent.EXTRA_SUBJECT, arrayOf("Hello I'm Pooh"))
                        putExtra(Intent.EXTRA_TEXT, arrayOf("How Cutty Pooh!"))
                    }
                    if(intent.resolveActivity(packageManager) != null){
                        startActivity(intent)
                    }

                })
                {
                    Text(text = "Click me")
                }
            }
            // when we clicked our button, we go to the additional Activity



        }
    }

    // by setting 'launch mode = single top', we can use activity which is having active currently.
    // so that 'onCreate' is not called again, below function will be called
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        var uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent.getParcelableExtra(Intent.EXTRA_STREAM)
        }
        viewModel.updateUri(uri)
    }
}




