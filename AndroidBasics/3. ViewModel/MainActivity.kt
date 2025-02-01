// #ViewModels
// source : https://www.youtube.com/watch?v=9sqvBydNJSg&list=PLQkwcJG4YTCSVDhww92llY3CAnc_vUhsm&index=3

// in Android 'mvvm' is one of the most popular design patterns
// 'mvvm' stands for model, view, view model

// [#Screen1]

// #MVVM
// View : just ui that present to user's screen
// Model : have data, remote API, which is responsible process data etc.
// View Model : bridge between View and Model.
// e.g. Model -> View : take data from Model and make it to new format for easily understanding in View(friendly format for UI), then send data to View
// e.g. View -> Model : if there are some action in UI, View Model take it and updates the Model according to that actions.
// e.g. if very simple case, just change background color, then ViewModel just interact with View
// e.g. if not simple case, fill up the data, and click 'save' button, then ViewModel interact between View and Model

// in Android it is more common that one ViewModel per screen.
// ViewModel is Bridge!

// ViewModel is just nothing than plain class.

package com.example.androidbascis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidbascis.ui.theme.AndroidBascisTheme


class MainActivity : ComponentActivity() {

    // #Problem
    // when we rotate the device 'configuration change' occur!
    // 'configuration change' can occur in "change language", "change theme"
    // android resolve this problem by "just recreate Activity" which means current activity totally destroyed and make new one.

    // #Problem1
    //private val viewModel = ContactsViewModel() // when rotate screen, always be recreated...
    // we still now reinitialize completely new ViewModel, we need another way.

    // #Solution1.1
//    private val viewModel by viewModels<ContactsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate()")
        enableEdgeToEdge()
        setContent {
            // #Solution1.2
            // +a) provide ViewModel Factory
            val viewModel = viewModel<ContactsViewModel>(
                factory = object: ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return ContactsViewModel(
                            helloWorld = "Hello user!"
                        ) as T
                    }
                }
            )
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = viewModel.backgroundColor
            ){
                Button(onClick = {
                    // take action when button click(ViewModel)
                    viewModel.changeBackgroundColor()
                }) {
                    Text(text = "Change color")
                }
            }
        }
    }
}
