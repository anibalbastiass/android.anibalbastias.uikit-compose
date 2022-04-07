package com.anibalbastias.uikitcompose.components.atom

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme

@Composable
fun HeadlineH6(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.h6
    )
}

@Preview("HeadlineH6 light theme")
@Composable
fun PreviewHeadlineH6Light() {
    UIKitComposeTheme {
        Surface {
            HeadlineH6(text = "Lorem ipsum dolor sit amet.")
        }
    }
}

@Preview("HeadlineH6 dark theme")
@Composable
fun PreviewHeadlineH6Dark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            HeadlineH6(text = "Lorem ipsum dolor sit amet.")
        }
    }
}