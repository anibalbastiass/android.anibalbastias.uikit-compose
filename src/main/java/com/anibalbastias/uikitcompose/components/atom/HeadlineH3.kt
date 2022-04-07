package com.anibalbastias.uikitcompose.components.atom

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme

@Composable
fun HeadlineH3(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.h3
    )
}

@Preview("HeadlineH3 light theme")
@Composable
fun PreviewHeadlineH3Light() {
    UIKitComposeTheme {
        Surface {
            HeadlineH3(text = "Lorem ipsum dolor sit amet.")
        }
    }
}

@Preview("HeadlineH3 dark theme")
@Composable
fun PreviewHeadlineH3Dark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            HeadlineH3(text = "Lorem ipsum dolor sit amet.")
        }
    }
}