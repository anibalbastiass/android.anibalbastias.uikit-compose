package com.anibalbastias.uikitcompose.components.atom

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.anibalbastias.uikitcompose.theme.defaultTextColor

@Composable
fun HeadlineH4(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = defaultTextColor(),
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        style = MaterialTheme.typography.h4
    )
}

@Preview("HeadlineH4 light theme")
@Composable
fun PreviewHeadlineH4Light() {
    UIKitComposeTheme {
        Surface {
            HeadlineH4(text = "Lorem ipsum dolor sit amet.")
        }
    }
}

@Preview("HeadlineH4 dark theme")
@Composable
fun PreviewHeadlineH4Dark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            HeadlineH4(text = "Lorem ipsum dolor sit amet.")
        }
    }
}