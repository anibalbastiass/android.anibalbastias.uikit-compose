package com.anibalbastias.uikitcompose.components.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anibalbastias.uikitcompose.components.atom.EmailTextField
import com.anibalbastias.uikitcompose.components.atom.PasswordTextField
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme

private val DEFAULT_PADDING = 50.dp

@Composable
fun TextFieldsScreen() {
    Column(
        modifier = Modifier
            .padding(DEFAULT_PADDING)
            .fillMaxWidth()
    ) {
        val (emailText, setEmailText) = remember { mutableStateOf("") }
        EmailTextField(
            label = "Email",
            emailText = emailText,
            onEmailChange = setEmailText,
        )
        Spacer(modifier = Modifier.size(50.dp))
        val (passwordText, setPasswordText) = remember { mutableStateOf("") }
        PasswordTextField(
            label = "Password",
            passwordText = passwordText,
            onPasswordChange = setPasswordText,
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(
                onDone = {
                    println("onDone Action")
                }
            )
        )
    }
}

@Preview("TextFieldsScreen light theme")
@Composable
fun PreviewTextFieldsScreenLight() {
    UIKitComposeTheme {
        Surface {
            TextFieldsScreen()
        }
    }
}

@Preview("TextFieldsScreen dark theme")
@Composable
fun PreviewTextFieldsScreenDark() {
    UIKitComposeTheme(darkTheme = true) {
        Surface {
            TextFieldsScreen()
        }
    }
}