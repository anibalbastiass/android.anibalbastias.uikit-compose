package com.anibalbastias.uikitcompose.components.atom

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.anibalbastias.uikitcompose.theme.defaultTextColor

@Composable
fun HeadlineH1(text: String, modifier: Modifier = Modifier, color: Color = defaultTextColor()) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.h1
    )
}

@Preview("HeadlineH1 light theme")
@Composable
fun PreviewHeadlineH1Light() {
    UIKitComposeTheme {
        Surface {
            HeadlineH1(text = "Lorem ipsum dolor sit amet.")
        }
    }
}

@Preview("HeadlineH1 dark theme")
@Composable
fun PreviewHeadlineH1Dark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            HeadlineH1(text = "Lorem ipsum dolor sit amet.")
        }
    }
}