package com.anibalbastias.uikitcompose.components.molecules.youtube

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.anibalbastias.uikitcompose.components.molecules.youtube.model.YouTubeVideoItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

class YouTubeViewModel : ViewModel() {

    val videos: MutableState<List<YouTubeVideoItem>> = mutableStateOf(listOf())
    val selectedVideo: MutableState<YouTubeVideoItem> = mutableStateOf(YouTubeVideoItem())

    val previousKey = mutableStateOf("")
    val isExpanded = mutableStateOf(false)
    val isShowing = mutableStateOf(false)
    var isPlaying = mutableStateOf(false)

    var movieYouTubePlayer: YouTubePlayer? = null

    fun reset() {
        videos.value = listOf()
        selectedVideo.value = YouTubeVideoItem()
        previousKey.value = ""
        isExpanded.value = false
        isShowing.value = false
        isPlaying.value = false
        movieYouTubePlayer = null
    }
}