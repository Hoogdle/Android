package com.example.androidbascis

import android.content.Context
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    // don't code like below, it will occur memory leak!
    var context: Context? = null
}