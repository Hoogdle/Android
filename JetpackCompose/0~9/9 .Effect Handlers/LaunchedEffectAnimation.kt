package com.example.jetpackcompose

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun LaunchedEffectAnimation(
    counter : Int
){
    val animatable = remember {
        Animatable(0f)
    }

    // whenever counter value changed, cancel coroutine line's and relaunched it with new counter value.
    LaunchedEffect(key1 = counter) {
        animatable.animateTo(counter.toFloat())
    }
}