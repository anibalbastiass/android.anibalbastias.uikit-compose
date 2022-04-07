package com.anibalbastias.uikitcompose.components.pages

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.anibalbastias.uikitcompose.components.templates.ErrorTemplate

@Preview(showSystemUi = true)
@Composable
fun ErrorScreen() {
    ErrorTemplate(
        title = "Oh no!",
        contentMsg = "Something went wrong...",
        buttonLabel = "Retry",
        buttonEvent = { /*TODO*/ })
}