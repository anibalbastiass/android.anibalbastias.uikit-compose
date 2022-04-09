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
fun HeadlineH2(text: String, modifier: Modifier = Modifier, color: Color = defaultTextColor()) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.h2
    )
}

@Preview("HeadlineH2 light theme")
@Composable
fun PreviewHeadlineH2Light() {
    UIKitComposeTheme {
        Surface {
            HeadlineH2(text = "Lorem ipsum dolor sit amet.")
        }
    }
}

@Preview("HeadlineH2 dark theme")
@Composable
fun PreviewHeadlineH2Dark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            HeadlineH2(text = "Lorem ipsum dolor sit amet.")
        }
    }
}