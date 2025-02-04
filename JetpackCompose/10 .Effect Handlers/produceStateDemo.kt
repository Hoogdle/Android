package com.example.jetpackcompose

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay

// purpose of this function is just produce some kind of state that changes overtime.


@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun produceStateDemo(countUpTo: Int): State<Int> {
    return produceState(initialValue = 0) {
        while(value < countUpTo){
            delay(1000L)
            value++
        }
    }
}