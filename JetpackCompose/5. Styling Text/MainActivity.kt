package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // using Font
        val fontFamily = FontFamily(
            // put in fonts which will used
            Font(R.font.lexend_bold, FontWeight.Thin),
            Font(R.font.lexend_black, FontWeight.Light),
            Font(R.font.lexend_extrabold, FontWeight.Medium)
        )
        setContent {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF101010))){
                Text(
                    // we can give parameter not only 'text-itself' but 'annotated string'
                    //text = "Hello Im Kim",

                    // give us scope for putting 'annotated string builder'
                    // by this we can apply different styles, different sections of strings.
                    text = buildAnnotatedString {

                        // #withStyle : allow us to give individual style.
                        withStyle(
                            // #SpanStyle : setting how our items will be looked like.
                            style = SpanStyle(
                                color = Color.Green,
                                fontSize = 50.sp
                            )
                        ){
                            // here, we can append 'a section of string'
                            append('J')
                        }
                        append("etpack ")
                        withStyle(
                            // #SpanStyle : setting how our items will be looked like.
                            style = SpanStyle(
                                color = Color.Green,
                                fontSize = 50.sp
                            )
                        ){
                            append("C")
                        }
                        append("ompose")
                    },
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline // one of great things in jetpack!

                )
            }
        }
    }
}

