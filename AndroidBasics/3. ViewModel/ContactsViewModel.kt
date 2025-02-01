package com.example.androidbascis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

/* by inherit ViewModel we can prevent recreate issue. it outlive the life cycle of screen!
* it will be destroyed when user totally pops the activity from the back stack. e.g. go th back screen*/

// we can't make primary initializer in ViewModel. because we use special class to make view model. and in that class there are no way to use primary constructors
// we can make this possible by 'ViewModel Factory' which responsible to represent build construction.
class ContactsViewModel(val helloWorld: String): ViewModel() {

    var backgroundColor by mutableStateOf(Color.White) // jetpack compose state
        private set // setter : private, getter : public(default). means we can change backgroundColor only by this ViewModel

    fun changeBackgroundColor(){
        backgroundColor = Color.Red
    }
}