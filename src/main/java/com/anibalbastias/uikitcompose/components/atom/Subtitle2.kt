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
fun Subtitle2(
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
        style = MaterialTheme.typography.subtitle2
    )
}

@Preview("Subtitle2 light theme")
@Composable
fun PreviewSubtitle2Light() {
    UIKitComposeTheme {
        Surface {
            Subtitle2(text = "Lorem ipsum dolor sit amet.")
        }
    }
}

@Preview("Subtitle2 dark theme")
@Composable
fun PreviewSubtitle2Dark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            Subtitle2(text = "Lorem ipsum dolor sit amet.")
        }
    }
}