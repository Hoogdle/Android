// WorkManager
// source : https://www.youtube.com/watch?v=A2JetouoNSc&list=PLQkwcJG4YTCSVDhww92llY3CAnc_vUhsm&index=9

// suppose you launch some app that download 2000 img files, and suddenly you close the apps.
// 'WorkManager' will cease the downloading process.

// If you have compression app which compress the image shared from chrome.
// 'WorkManager' will calculate compress process, which could be hard exact size of image for compression

package com.example.androidbascis

import android.R.attr.bitmap
import android.R.attr.text
import android.app.ComponentCaller
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import coil.compose.AsyncImage
import kotlin.jvm.java

// below code not working well, just refer it as easy chip
class MainActivity : ComponentActivity() {

    // declare work manager
    private lateinit var workManger: WorkManager
    private val viewModel by viewModels<PhotoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        workManger = WorkManager.getInstance(applicationContext)

    setContent {
            val workerResult = viewModel.workId?.let{ id ->
                workManger.getWorkInfoByIdLiveData(id).observeAsState().value
            }
            LaunchedEffect(key1 = workerResult?.outputData) {
            if(workerResult?.outputData != null){
                val filePath = workerResult.outputData.getString(
                    PhotoCompressionWorker.KEY_RESULT_PATH
                )
                filePath?.let{
                    val bitmap = BitmapFactory.decodeFile(it)
                    viewModel.updateCompressedBitmap(bitmap)
                }
            }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                viewModel.uncompressedUri?.let{
                    Text(text = "Uncompressed photo")
                    AsyncImage(model = it, contentDescription = null)
                }
                Spacer(modifier = Modifier.height(16.dp))
                viewModel.compressedBitmap?.let{
                    Text(text = "Uncompressed photo")
                    Image(bitmap = it.asImageBitmap(), contentDescription = null)
                }
            }
        }
    }

    // this function only make sense when we overwrite 'Manifest' file.
    override fun onNewIntent(intent: Intent) {
        // here 'intent' get uri of the photo from chrome
        super.onNewIntent(intent)
        val uri = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
             intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        } ?: return // if there is not uri(means 'null')

        // update uri
        viewModel.updateUncompressUri(uri)
        Log.w("debug","get the uri")

         // by 'Work Request' we can launch worker
        val request = OneTimeWorkRequestBuilder<PhotoCompressionWorker>()
            .setInputData(
                // provide key content(uri), key threshold(how mush you will zip the img)
                workDataOf(
                    PhotoCompressionWorker.KEY_CONTENT_URI to uri.toString(),
                    PhotoCompressionWorker.KEY_COMPRESSION_THRESHOLD to 1024 * 20L
                )
            )

            // if worker require internet, then worker manager take care of that
            // in this case, we use local file, so we don't have implement for internet
            .setConstraints(Constraints(
                // user's storage must be not low.
                requiresStorageNotLow = true
            ))
            .build() // to finally build our work request

        viewModel.updateWorkId(request.id)

        // put our request(photo compression) to work manager
        workManger.enqueue(request)
    }
}




