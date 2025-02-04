package com.example.jetpackcompose

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@SuppressLint("UnrememberedMutableState")
@Composable
fun DerivedStateOfDemo() {
    var counter by remember {
        mutableStateOf(0)
    }

    // in fact, when button clicked and counter's number go up, 'counterText' will be changed.
    // more specifically, the process is like this. "The counter is" + counter
    // concatenation cost is quite expensive, you can prevent it by 'derivedStateOf'

    // by 'derivedStateOf', you can cache the last 'counterText', so that you don't need to concatenate whenever recomposition occurred.

    // val counterText = "The counter is $counter"
    val counterText by derivedStateOf {
        "The counter is $counter"
    }

    Button(onClick = {counter++}) {
        Text(text = counterText)
    }
}