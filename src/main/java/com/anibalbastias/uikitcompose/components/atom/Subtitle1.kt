package com.anibalbastias.uikitcompose.components.atom

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme

@Composable
fun Subtitle1(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.subtitle1
    )
}

@Preview("Subtitle1 light theme")
@Composable
fun PreviewSubtitle1Light() {
    UIKitComposeTheme {
        Surface {
            Subtitle1(text = "Lorem ipsum dolor sit amet.")
        }
    }
}

@Preview("Subtitle1 dark theme")
@Composable
fun PreviewSubtitle1Dark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            Subtitle1(text = "Lorem ipsum dolor sit amet.")
        }
    }
}