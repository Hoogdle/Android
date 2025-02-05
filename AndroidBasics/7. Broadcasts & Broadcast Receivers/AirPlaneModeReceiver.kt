package com.example.androidbascis

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings

// make broad-cast class
// every broad-cast class needs onReceive function
class AirPlaneModeReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED){
            val isTurnedOn = Settings.Global.getInt(
                context?.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON
            ) != 0
            {
                println("Is airplane mode enabled? $isTurnedOn")
            }
        }
    }
}