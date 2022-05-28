package com.anibalbastias.uikitcompose.components.pages.youtube.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class YouTubeVideoItem(
    val key: String = "",
    val name: String = "",
    val main: String = ""
) {
    var currentTime by mutableStateOf(0f)
}