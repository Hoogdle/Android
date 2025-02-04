// Effect Handlers
// source : https://www.youtube.com/watch?v=gxWcfz3V2QE&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=10

package com.example.jetpackcompose

import android.R.attr.onClick
import android.util.Log.i
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


// #side-effect
// escapes the scope of a composable functions

// see the below code.

//============================================================
//private var i = 0
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent{
//            var text by remember {
//                mutableStateOf("")
//            }
//
//            Button(onClick = {text += "#"}){
//                i++
//                Text(text = text)
//            }
//        }
//    }
//}
//============================================================

// non-compose things(i) get affected when Button clicked.
// suppose i is 'network api' then it is not optimal way to deal with it.
// don't do this!!!

// => by effect handlers we can avoid this.


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

// Code for explain
//============================================================
//private var i = 0;
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent{
//            var text by remember {
//                mutableStateOf("")
//            }
//
//            // whenever key1 value(text) is changed, Coroutine Space's codes will be cancelled.
//            // if key1 value(text) is not changed, Coroutine Space's codes will be launched.
//            LaunchedEffect(key1 = text) {
//                // Coroutine Scope
//                delay(1000L)
//                println("The text is $text")
//            }
//        }
//    }
//}
//============================================================

// by effective handler, we can control non-composible things. e.g. network.
// when recompose is occurred, most of code will re-launched.
// if non-composible things in our code, then that also have to be occurred.
// it is not optimal way, so we can control acting of non-composible things by effective handler


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            var text by remember {
                mutableStateOf("")
            }

            // whenever key1 value(text) is changed, Coroutine Space's codes will be cancelled and re-launched with 'new text value'
            LaunchedEffect(key1 = text) {
                // Coroutine Scope
                delay(1000L)
                println("The text is $text")
            }
        }
    }
}

@Composable
fun LaunchedEffectFlowDemo(
    viewModel: LaunchedEffectViewModel
){
    // key1 = true : make sure it's actually only called once.
    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collect { event ->
            when(event){
                is LaunchedEffectViewModel.ScreenEvents.ShowSnackbar ->{

                }
                is LaunchedEffectViewModel.ScreenEvents.Navigate ->{

                }
            }
        }
    }
}







