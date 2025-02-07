package com.example.androidbascis

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.roundToInt

// we need default parameters when create class of ManagerWorker
class PhotoCompressionWorker(
    private val appContext: Context,
    private val params: WorkerParameters
): CoroutineWorker(appContext, params) {


    // this function will execute when we want to run our worker
    // we can constrains some condition that workers only possible to run within the condition
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val stringUri = params.inputData.getString(KEY_CONTENT_URI)
            val compressionTresholdInBytes = params.inputData.getLong(
                KEY_COMPRESSION_THRESHOLD,
                0L
            )
            val uri = Uri.parse(stringUri)
            val bytes = appContext.contentResolver.openInputStream(uri)?.use{
                it.readBytes()
            } ?:return@withContext Result.failure()

            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

            var outputBytes: ByteArray
            var quality = 100

            do {
                val outputStream = ByteArrayOutputStream()
                outputStream.use{ outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
                    outputBytes = outputStream.toByteArray()
                    quality -= (quality * 0.1).roundToInt()
                }
            } while(outputBytes.size > compressionTresholdInBytes && quality > 5)

            // put the result to android file system's path
            val file = File(appContext.cacheDir, "${params.id}.jpg")
            file.writeBytes(outputBytes)

            Result.success(
                workDataOf(
                    KEY_RESULT_PATH to file.absoluteFile
                )
            )
        }
    }

    companion object{
        const val KEY_CONTENT_URI = "KEY_CONTENT_URI"
        const val KEY_COMPRESSION_THRESHOLD = "KEY_COMPRESSION_THRESHOLD"
        const val KEY_RESULT_PATH = "KEY_RESULT_PATH"
    }

}