// #Textfileds, Buttons & Showing Snackbars
// source : https://www.youtube.com/watch?v=_yON9d9if6g&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=7

// little hard..

package com.example.jetpackcompose


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            // #SnackBar
//            Snackbar(){
//                Text(text = "Hello Kim") // #Screen1
//            }

            // For full control(how long it will stay), we could use Scaffold

            // #Scaffold
            // layouting compose which will make it easy for us to
            // include existing material design components
            // by it, we can just add top bar or tool bar and also snack bars.

            val scope = rememberCoroutineScope()
            val snackbarHostState = remember{ SnackbarHostState() }
            // just represent current text in that state
            // syntactic sugar
            var textFiledState by remember { mutableStateOf("") }

            Scaffold(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackbarHostState
                    )
                }){
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                    .fillMaxSize()
                    .padding(it))
                {
                    // you will link this 'text filed' to a 'state'
                    // if user type something into the text field, we need to update state variables.
                   TextField(
                       // value : represent sthe string that is visible in that text field.
                       value = textFiledState,
                       // hint of our TextField, if there are no text, pop up will be showed up.
                       label = {
                           Text("Enter your name")
                       },
                       // if text field's value changes, this function will be called.
                       onValueChange ={
                           textFiledState = it
                       },
                       singleLine = true,
                       modifier = Modifier.fillMaxWidth()
                   )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Hello $textFiledState"
                            )
                        }
                    }) {
                        Text("Plz Greet Me")
                    }
                }
            }
        }
    }
}














