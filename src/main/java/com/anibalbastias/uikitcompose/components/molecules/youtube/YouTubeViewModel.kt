package com.anibalbastias.uikitcompose.components.molecules.youtube

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.anibalbastias.uikitcompose.components.pages.youtube.model.YouTubeVideoItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

class YouTubeViewModel : ViewModel() {

    var videos by mutableStateOf(listOf<YouTubeVideoItem>())
    var selectedVideo by mutableStateOf(YouTubeVideoItem())

    var previousKey by mutableStateOf("")
    var previousMovie by mutableStateOf("")

    var isExpanded by mutableStateOf(false)
    var isShowing by mutableStateOf(false)
    var isPlaying by mutableStateOf(false)

    var movieYouTubePlayer: YouTubePlayer? = null

    fun reset() {
        selectedVideo = YouTubeVideoItem()

        previousKey = ""
        isExpanded = false
        isShowing = false
        isPlaying = false
        movieYouTubePlayer = null
    }
}