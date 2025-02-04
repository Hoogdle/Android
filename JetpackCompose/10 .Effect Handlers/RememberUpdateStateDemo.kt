package com.example.jetpackcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.delay

@Composable
fun RememberUpdateStateDemo (
    onTimeout : () -> Unit
) {

    // if we want to use new time out function, by updated below code, we can use it.
    val updatedOnTimeout by rememberUpdatedState(newValue = onTimeout)

    LaunchedEffect(true) {
        delay(3000L)
        //onTimeout()
        updatedOnTimeout()
    }
}