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
fun Body1(
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
        style = MaterialTheme.typography.body1
    )
}

@Preview("TextBody1 light theme")
@Composable
fun PreviewBody1Light() {
    UIKitComposeTheme {
        Surface {
            Body1(text = "Lorem ipsum dolor sit amet.")
        }
    }
}

@Preview("TextBody1 dark theme")
@Composable
fun PreviewBody1Dark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            Body1(text = "Lorem ipsum dolor sit amet.")
        }
    }
}