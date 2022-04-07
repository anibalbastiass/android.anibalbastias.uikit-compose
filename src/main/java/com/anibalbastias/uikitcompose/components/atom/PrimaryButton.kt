package com.anibalbastias.uikitcompose.components.atom

import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import java.util.*

@Composable
fun PrimaryButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = label.uppercase(Locale.getDefault()),
            color = Color.White
        )
    }
}

@Preview("PrimaryButton light mode")
@Composable
fun PreviewPrimaryButtonLight() {
    UIKitComposeTheme {
        Surface {
            PrimaryButton(label = "Primary Button", onClick = { })
        }
    }
}

@Preview("PrimaryButton dark mode")
@Composable
fun PreviewPrimaryButtonDark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            PrimaryButton(label = "Primary Button", onClick = { })
        }
    }
}