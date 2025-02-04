package com.example.jetpackcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

// DisposableEffect have only one different things compared with LaunchedEffect
// when key changed, DisposableEffect get rid of data within blocks, LaunchedEffect do not.

// +a) DisposableEffect must implement onDispose function.

@Composable
fun DisposableEffectDemo(){
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver {_, event ->
            if(event == Lifecycle.Event.ON_PAUSE){
                println("On pause called")
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }

    }
}