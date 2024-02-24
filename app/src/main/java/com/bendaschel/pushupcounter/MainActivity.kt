package com.bendaschel.pushupcounter

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bendaschel.pushupcounter.ui.theme.PushupCounterTheme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.bendaschel.pushupcounter.ui.genRandomColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterScreen()
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CounterScreen() {
    var counter by remember { mutableStateOf(0) }


    var bgColor by remember { mutableStateOf(Color.LightGray) }
    var textColor by remember { mutableStateOf(Color.White) }
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .combinedClickable(
                onClick = {
                    counter++
                    val (newBgColor, newTextColor) = genRandomColors()
                    bgColor = newBgColor
                    textColor = newTextColor
                    playSound(context)
                },
                onLongClick = {
                    counter = 0
                    bgColor = Color.LightGray
                }
            ),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = counter.toString(),
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge.copy(
                    shadow = Shadow(
                        color = Color(0xff00ff00),
                        offset = Offset(0.5f, 0.5f),
                        blurRadius = 0.2f
                    ),
                    fontSize = 128.sp
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewCounterScreen() {
    CounterScreen()
}

fun playSound(context: Context) {
    val mediaPlayer = MediaPlayer.create(context, R.raw.boop)
    mediaPlayer.start()
}