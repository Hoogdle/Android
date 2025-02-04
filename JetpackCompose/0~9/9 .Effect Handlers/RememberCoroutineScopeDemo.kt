package com.example.jetpackcompose

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// # rememberCoroutineScope
// only available in 'call-back'
// in scope variable which one could have other coroutine, other coroutines in the scope will be canceled.

@Composable
fun RememberCoroutineScopeDemo() {
    val scope = rememberCoroutineScope()

    Button(onClick = {
        scope.launch {
            delay(1000L)
            println("Hello World!")
        }
    }){

    }

}