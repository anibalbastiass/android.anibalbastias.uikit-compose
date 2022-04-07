package com.anibalbastias.uikitcompose.components.pages

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.anibalbastias.uikitcompose.components.templates.listoflements.Element
import com.anibalbastias.uikitcompose.components.templates.listoflements.ListOfElements
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme

@Composable
fun ComponentsIndexScreen(
    buttonsAction: () -> Unit,
    errorTemplateAction: () -> Unit,
    textFieldsAction: () -> Unit,
    typeScaleAction: () -> Unit
) {
    ListOfElements(elements = listOf(
        Element(
            text = "Buttons",
            onElementSelect = buttonsAction
        ),
        Element(
            text = "Error Template",
            onElementSelect = errorTemplateAction
        ),
        Element(
            text = "Text Fields",
            onElementSelect = textFieldsAction
        ),
        Element(
            text = "Type Scale",
            onElementSelect = typeScaleAction
        ),
        Element(text = "Element 5", onElementSelect = {}),
        Element(text = "Element 6", onElementSelect = {}),
        Element(text = "Element 7", onElementSelect = {}),
        Element(text = "Element 8", onElementSelect = {}),
        Element(text = "Element 9", onElementSelect = {}),
        Element(text = "Element 10", onElementSelect = {}),
        Element(text = "Element 11", onElementSelect = {}),
        Element(text = "Element 12", onElementSelect = {}),
        Element(text = "Element 13", onElementSelect = {}),
        Element(text = "Element 14", onElementSelect = {}),
    ))
}

@Preview("ComponentsIndex light theme")
@Composable
fun PreviewComponentsIndexLight() {
    UIKitComposeTheme {
        Surface {
            ComponentsIndexScreen(
                buttonsAction = {},
                errorTemplateAction = {},
                textFieldsAction = {},
                typeScaleAction = {},
            )
        }
    }
}

@Preview("ComponentsIndex dark theme")
@Composable
fun PreviewComponentsIndexDark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            ComponentsIndexScreen(
                buttonsAction = {},
                errorTemplateAction = {},
                textFieldsAction = {},
                typeScaleAction = {},
            )
        }
    }
}