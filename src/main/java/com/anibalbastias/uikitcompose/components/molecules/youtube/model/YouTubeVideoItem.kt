package com.anibalbastias.uikitcompose.components.molecules.youtube.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class YouTubeVideoItem(
    val key: String = "",
    val name: String = "",
    val main: String = "",
    var isSelected: MutableState<Boolean> = mutableStateOf(false),
    val currentTime: MutableState<Float> = mutableStateOf(0f),
)