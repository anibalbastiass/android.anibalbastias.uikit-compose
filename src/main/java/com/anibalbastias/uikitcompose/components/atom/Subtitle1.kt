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
fun Subtitle1(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = defaultTextColor(),
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        color = color,
        modifier = modifier,
        textAlign = textAlign,
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