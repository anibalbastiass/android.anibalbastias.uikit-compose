package com.anibalbastias.uikitcompose.components.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anibalbastias.uikitcompose.components.atom.PrimaryButton
import com.anibalbastias.uikitcompose.components.atom.SecondaryButton
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme

private val DEFAULT_PADDING = 50.dp

@Composable
fun ButtonsScreen() {
    Column(
        modifier = Modifier
            .padding(DEFAULT_PADDING)
            .fillMaxWidth()
    ) {
        PrimaryButton(
            label = "Primary Button",
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(50.dp))
        SecondaryButton(
            label = "Secondary Button",
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview("ButtonsScreen light theme")
@Composable
fun PreviewErrorTemplateLight() {
    UIKitComposeTheme {
        Surface {
            ButtonsScreen()
        }
    }
}

@Preview("ButtonsScreen dark theme")
@Composable
fun PreviewErrorTemplateDark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            ButtonsScreen()
        }
    }
}