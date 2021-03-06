package com.anibalbastias.uikitcompose.components.atom

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.anibalbastias.uikitcompose.R
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme

@Composable
fun EmailTextField(
    label: String = stringResource(id = R.string.email),
    emailText: String,
    onEmailChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = emailText,
        onValueChange = { onEmailChange(it) },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ).copy(imeAction = imeAction),
        keyboardActions = keyboardActions,
        maxLines = 1,
    )
}

@Preview("EmailTextField light mode")
@Composable
fun PreviewEmailTextFieldLight() {
    UIKitComposeTheme {
        Surface {
            val (emailText, setEmailText) = remember { mutableStateOf("") }
            EmailTextField(
                label = "Email",
                emailText = emailText,
                onEmailChange = setEmailText
            )
        }
    }
}

@Preview("EmailTextField dark mode")
@Composable
fun PreviewEmailTextFieldDark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            val (emailText, setEmailText) = remember { mutableStateOf("") }
            EmailTextField(
                label = "Email",
                emailText = emailText,
                onEmailChange = setEmailText
            )
        }
    }
}