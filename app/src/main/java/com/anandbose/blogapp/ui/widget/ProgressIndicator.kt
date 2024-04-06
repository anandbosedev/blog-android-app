package com.anandbose.blogapp.ui.widget

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

private val progressStates = listOf(
    '—', '\\', '|', '/',
)
private val decorChars = listOf(
    '*', '~', '@', '#', '$', '%', '&', '?', ':'
)
private val blockSize = 24.dp

@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) {
    var index by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = Unit) {
        while (isActive) {
            index = (index + 1).rem(progressStates.size)
            delay(500L)
        }
    }
    val decor by remember {
        derivedStateOf {
            index
            decorChars.shuffled()
        }
    }
    Surface(
        modifier = modifier,
        color = Color.Black,
    ) {
        Column {
            Row {
                Text(
                    text = decor[0].toString(),
                    modifier = Modifier.size(blockSize)
                        .background(Color.Black),
                    color = Color.White,
                    fontFamily = FontFamily(Typeface.MONOSPACE),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = decor[1].toString(),
                    modifier = Modifier.size(blockSize)
                        .background(Color.Black),
                    color = Color.White,
                    fontFamily = FontFamily(Typeface.MONOSPACE),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = decor[2].toString(),
                    modifier = Modifier.size(blockSize)
                        .background(Color.Black),
                    color = Color.White,
                    fontFamily = FontFamily(Typeface.MONOSPACE),
                    textAlign = TextAlign.Center,
                )
            }
            Row {
                Text(
                    text = decor[3].toString(),
                    modifier = Modifier.size(blockSize)
                        .background(Color.Black),
                    color = Color.White,
                    fontFamily = FontFamily(Typeface.MONOSPACE),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = progressStates[index].toString(),
                    modifier = Modifier.size(blockSize)
                        .background(Color.White),
                    color = Color.Black,
                    fontFamily = FontFamily(Typeface.MONOSPACE),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = decor[4].toString(),
                    modifier = Modifier.size(blockSize)
                        .background(Color.Black),
                    color = Color.White,
                    fontFamily = FontFamily(Typeface.MONOSPACE),
                    textAlign = TextAlign.Center,
                )
            }
            Row {
                Text(
                    text = decor[5].toString(),
                    modifier = Modifier.size(blockSize)
                        .background(Color.Black),
                    color = Color.White,
                    fontFamily = FontFamily(Typeface.MONOSPACE),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = decor[6].toString(),
                    modifier = Modifier.size(blockSize)
                        .background(Color.Black),
                    color = Color.White,
                    fontFamily = FontFamily(Typeface.MONOSPACE),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = decor[7].toString(),
                    modifier = Modifier.size(blockSize)
                        .background(Color.Black),
                    color = Color.White,
                    fontFamily = FontFamily(Typeface.MONOSPACE),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview
@Composable
fun ProgressIndicatorPreview() {
    ProgressIndicator()
}