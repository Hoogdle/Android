package com.example.jetpackcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

// SideEffect gets called whenever my composable successfully recompose.

@Composable
fun SideEffectDemo(nonComposeCounter: Int){
    SideEffect{
        println("Called after every successful recomposition")
    }
}