// Context
// source : https://www.youtube.com/watch?v=YdnM2ZvrIFM&list=PLQkwcJG4YTCSVDhww92llY3CAnc_vUhsm&index=4

// #What is Context?
// instance of class.
// middle-man of myApp and other Component.
// As the name suggests, it's the context of the current state of the application/object.
// It lets newly-created objects understand what has been going on.

package com.example.androidbascis

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel

// zero connection to Android ecosystem.
// just pure kotlin class
//class MyClass{}


// connected to the Android side of myApp
//class MyClass(private val context: Context){}

// [Screen#1] diagram about Context.
// in fact, 'Activity', 'Service', 'Application' are sub-class of Context!

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Context declaration
        // it used all the time during Android Dev
        // it used for bridging between my Android App and rest of Android operation System.
        // it provides context for my application to operate within the larger scope of the whole Android operating system.

        // if our app need to communicate with other system components or other apps, we need context

        // e.g. get a photo in gallery.
        // in this example, more detail, myApp doesn't interact with gallery directly, but with 'Android System'
        // 'Android System' will using information from context, and decide how to handle it.
        // in this case, Android System present user sort of gallery app for bring photo in those app.
        // if i choose, the result(photo) will bring back to myApp. more detailed process is below.

        // myApp -> Android System(show sort of gallery app) -> gallery(choose) -> Android System -> myApp

        // 'this' refers to 'instance of MainActivity'
        // because activity also sub-class of context, we can use activity as context.


        // #inheritence
        // SuperClass : Context
        // SubClass : Activity, Service, Application
        // Of course, SubClass have more features than SuperClass.
        // All of SubClass can refer SuperClass(plz note 'REFER'). it call 'is-a' relation.
        // it just refer it. not get the whole information from SuperClass.(like pointer!)
        // when we use Superclass declared with SubClass, we can use feature(properties, methods) by Superclass, with limitation that using features exit in SuperClass
        // so we can only use Superclass's features by below code.
        // val myContext: Context = this
        // #LifeTime
        // each context have specific lifetime.(Activity context, Application context life time may differ)
        // if Activity(sort of screen unit) freed up(destroyed), context all so be destroyed with that.
        // but in Application, that active whole time when launching app, context will be lived whole time except for exit app.


        enableEdgeToEdge()
        // make viewModel's context to current activity.
        // because viewModel's lifecycle is outlive of the activity(cause made by viewModels)
        // if there are rotate screen, below code will refer activity which already destroyed
        // it prevents the Activity from being garbage collected, because something(ViewModel) is still pointing to it.

        // of course, if there are re-configuration, Main Activity will be re-created, and below run again
        // viewModel.context = this
        // and then new-MainActivity's context will be putted to viewModel.context
        // however, before, when first MainActivity was destroyed, garbage collect couldn't get rid of it, because viewModel's context still have it.
        // so we lost the chance to get rid of first MainActivity, and first MainActivity will be remain in memory.(even new one has been created)

        // so it always to be recommended to "do not store activity context to outside of activity."

        // in 'application context' there will be no problem at all.
        // because its lifetime is longer than our app.

        // and we can retrieve 'application context' by below code
        // this.applicationContext

        // using Activity context example(pop up dialog)
//        ActivityCompat.requestPermissions(
            // we need to pass an 'activity context'
            // it need information about my current activity, because it will be shown on top of it.
//        )

        viewModel.context = this // memory leak!
        setContent {

        }
    }
}
