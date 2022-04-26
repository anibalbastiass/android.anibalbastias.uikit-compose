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
fun Body2(
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
        style = MaterialTheme.typography.body2
    )
}

@Preview("Body2 light theme")
@Composable
fun PreviewBody2Light() {
    UIKitComposeTheme {
        Surface {
            Body2(text = "Lorem ipsum dolor sit amet.")
        }
    }
}

@Preview("Body2 dark theme")
@Composable
fun PreviewBody2Dark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            Body2(text = "Lorem ipsum dolor sit amet.")
        }
    }
}