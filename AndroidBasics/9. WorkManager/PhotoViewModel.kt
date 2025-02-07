package com.example.androidbascis

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.UUID

class PhotoViewModel : ViewModel(){
    var uncompressedUri: Uri? by mutableStateOf(null)
        private set

    var compressedBitmap: Bitmap? by mutableStateOf(null)
        private set

    // if workId is changed, we also need change 'which worker's id we going to get'
    var workId: UUID? by mutableStateOf(null)
        private set

    fun updateUncompressUri(uri: Uri?){
        uncompressedUri = uri
    }

    fun updateCompressedBitmap(bmp: Bitmap?){
        compressedBitmap = bmp
    }

    fun updateWorkId(uuid: UUID?){
        workId = uuid
    }
}