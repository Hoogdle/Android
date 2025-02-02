package com.example.androidbascis

import android.app.Application
import android.content.Context

// it also have same 'life cycle' of Activity.
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Same to Application Class
        // Application class is sub-class of Context, so we can us Application as Context.
        val myContext: Context = this
    }
}