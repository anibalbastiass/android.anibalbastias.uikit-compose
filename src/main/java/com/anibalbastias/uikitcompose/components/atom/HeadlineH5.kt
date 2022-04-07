package com.anibalbastias.uikitcompose.components.atom

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme

@Composable
fun HeadlineH5(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.h5
    )
}

@Preview("HeadlineH5 light theme")
@Composable
fun PreviewHeadlineH5Light() {
    UIKitComposeTheme {
        Surface {
            HeadlineH5(text = "Lorem ipsum dolor sit amet.")
        }
    }
}

@Preview("HeadlineH5 dark theme")
@Composable
fun PreviewHeadlineH5Dark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            HeadlineH5(text = "Lorem ipsum dolor sit amet.")
        }
    }
}