// Lists
// source : https://www.youtube.com/watch?v=1Thp0bB5Ev0&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=8

package com.example.jetpackcompose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
//            val scrollState = rememberScrollState() // [Screen#2] (whole item showed up, scroll available)
//            // if we have so many item, then we need sort of 'lazy column'
//            Column(
//                // #Modifier.verticalScroll -> allow us to scroll screen
//                // it require 'scroll state'
//                // manipulate the current scroll position, programmatically scroll in our colum
//                modifier = Modifier.verticalScroll(scrollState)
//            ){
//                for(i in 1..50){
//                    Text(
//                        text = "Item $i",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(vertical = 24.dp)
//                    ) // [Screen#1] (just there are one 'Item1')
//                }
//            }

            // #LazyColumn
            // default is scrollable
            // items will be lazily loaded, so that item will be loaded only when we actually scroll to that position.


//            LazyColumn {
//                // let 5000 items here.
//                items(5000){
//                    // we can put composable which represent single item.
//                    Text(
//                        // LazyColumn provide item index with 'it'
//                        text = "Item $it",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(vertical = 24.dp)
//                    ) // [Screen#3]
//                }
//            }

            LazyColumn {
                itemsIndexed(
                    // we can choose any type of list
                    listOf<String>("This", "is", "Jetpack", "Compose")
                ) { index, string ->
                    Text(
                        text = string,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                            // [Screen#4]
                    )
                }
            }
        }
    }
}














