package com.example.androidbascis

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.getSystemService

class RunningApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                "running_channel", // id
                "Running Notifications", // name(user will see in the app setting
                NotificationManager.IMPORTANCE_HIGH // importance
            )

            // Android System provide service which our app can use it.
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            // whenever we create android components(broadcast, receiver, service, activity)
            // we need to register into manifest
        }
    }
}