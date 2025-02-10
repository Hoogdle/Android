// Content Providers
// source : https://www.youtube.com/watch?v=IVHZpTyVOxU&list=PLQkwcJG4YTCSVDhww92llY3CAnc_vUhsm&index=11

package com.example.androidbascis


import android.Manifest
import android.R
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import coil.compose.AsyncImage
import java.util.Calendar
import kotlin.collections.mutableListOf


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ImageViewModel>()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // request Permission to access images that located in external storage.
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
            0
        )

        val projection = arrayOf( // declare which data we need from img
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )

        // selection : filter img which we need. e.g. yesterday img or name

        val millisYesterday = Calendar.getInstance().apply{
            add(Calendar.DAY_OF_YEAR, -1)
        }.timeInMillis

        val selection = "${MediaStore.Images.Media.DATE_TAKEN} ? = ?"
        val selectionArgs = arrayOf(millisYesterday.toString()) // refer to '?' in selection

        // sortOrder : sorting items
        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use{ cursor -> // cursor : all items
            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID) // column of index(have all items)
            val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME) // column of file name(have all items)

            val images = mutableListOf<Image>()
            while(cursor.moveToNext()){
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                images.add(Image(id,name,uri))
            }

            viewModel.updateImages(images)
        }
        // we need to grant permission for access image.
    setContent {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.images){ image ->
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = image.uri,
                            contentDescription = null
                        )
                        Text(text = image.name)
                    }
                }
            }
        }
    }
}

data class Image(
    val id: Long,
    val name: String,
    val uri: Uri
)




