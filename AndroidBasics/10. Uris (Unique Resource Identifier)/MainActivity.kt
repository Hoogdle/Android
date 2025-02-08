// Uris (Unique Resource Identifier)
// source : https://www.youtube.com/watch?v=4Ob0plBL084&list=PLQkwcJG4YTCSVDhww92llY3CAnc_vUhsm&index=10

package com.example.androidbascis


import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import java.io.File
import java.io.FileOutputStream


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uri = Uri.parse("") // combine whole type(4-types) of Uri

        // 1. Resource Uri
        // point to resource which in our app.
        val uri1 = Uri.parse("android.resource://$packageName/drawable/pooh")
        val poohBytes = contentResolver.openInputStream(uri1)?.use{
            it.readBytes()
        }
        println("Pooh size : ${poohBytes?.size}") //Pooh size : 4908

        // 2. File Uri
        // each app has its own internal-storage, that other apps can not access.
        // let save our 'poohBytes' to internal-storage of our app.
        val file = File(filesDir, "pooh.jpg")
        FileOutputStream(file).use{
            it.write(poohBytes)
        }

        println(file.toURI())
        // file:/data/user/0/com.example.androidbascis/files/pooh.jpg
        // file path where it is saved.



    // 3. Content Uri : shared file to other app, such app don't need require-permission
    // content uri have reference to other apps. e.g. gallery(not save to app's internal space).
    // its not guarantee that your resource is persistently in your apps(because it just refer to other app's resource)
    setContent {
            val pickImage = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult = {contentUri ->
                    println(contentUri)
                }
            )
            Button(onClick = {
                pickImage.launch("image/*")
            }){
                Text(text = "Pick image")
            }
        }
    }
}






