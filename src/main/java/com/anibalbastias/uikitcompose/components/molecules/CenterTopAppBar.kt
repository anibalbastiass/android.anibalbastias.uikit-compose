package com.anibalbastias.uikitcompose.components.molecules

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CenterTopAppBar(
    backgroundColor: Color,
    titleView: @Composable () -> Unit,
    navigationIconView: @Composable () -> Unit,
    actionsView: @Composable () -> Unit,
) {
    val appBarHorizontalPadding = 4.dp
    val titleIconModifier = Modifier
        .fillMaxHeight()
        .width(72.dp - appBarHorizontalPadding)

    TopAppBar(
        backgroundColor = backgroundColor,
        modifier = Modifier.fillMaxWidth()) {

        // TopAppBar Content
        Box(Modifier.height(32.dp)) {

            // Title
            Row(Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically) {

                ProvideTextStyle(value = MaterialTheme.typography.h6) {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            titleView()
                        }
                    }
                }
            }

            // Navigation Icon
            Row(titleIconModifier, verticalAlignment = Alignment.CenterVertically) {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high,
                ) {
                    navigationIconView()
                }
            }

            // Actions
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        actionsView()
                    }
                )
            }
        }
    }
}