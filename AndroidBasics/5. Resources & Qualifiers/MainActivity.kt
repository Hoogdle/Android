// Resources & Qualifiers
// source : https://www.youtube.com/watch?v=vj1ZdUfPlJM&list=PLQkwcJG4YTCSVDhww92llY3CAnc_vUhsm&index=5

// #Resources
// non-coding things my album needs(pictures, vector graphics, localized string)
// all resource located in 'res' directory

// ex) dark theme
// 'res' -> 'android resource file' -> 'night'
// by create new theme(above process) we can manage the picture when mobile turn to dark theme
// we can see that 'drawable-night' directory newly created, in this directory things are saved, which related to dark theme.

// (1) drawable : relate to everything visual.
// (2) mipmap : for icon of app. (there are different size of devices.)
// (3) values : we can store data which be used many time. (colors, strings, themes(fonts, size, etc..))
// theme is not often used if you use purely jetpack compose.
package com.example.androidbascis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // take img from resource
        // using 'id' (whole feature have it own id)

        resources.getDrawable(R.drawable.pooh, null)

        setContent {
            // directly show img on the view

            // get color (2-way)
            val get_color1 = colorResource(id = R.color.purple_200)
            val get_color2 = colorResource(id = R.color.purple_200)

            Image(
                painter = painterResource(id = R.drawable.pooh),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
