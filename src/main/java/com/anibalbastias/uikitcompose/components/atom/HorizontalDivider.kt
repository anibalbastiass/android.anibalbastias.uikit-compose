package com.anibalbastias.uikitcompose.components.atom

import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDivider(modifier: Modifier = Modifier, color: Color = Color.LightGray) {
    Divider(
        color = color,
        modifier = Modifier
            .height(1.dp)
            .then(modifier)
    )
}