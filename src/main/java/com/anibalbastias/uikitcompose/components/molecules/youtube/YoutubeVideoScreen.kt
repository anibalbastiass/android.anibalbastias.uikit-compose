package com.anibalbastias.uikitcompose.components.molecules.youtube

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentManager
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

var movieYouTubePlayer: YouTubePlayer? = null
var currentKey: String = ""

@Composable
fun YoutubeVideoScreen(
    modifier: Modifier = Modifier,
    key: String,
    animateToEnd: Boolean,
) {
    if (key != currentKey) {
        movieYouTubePlayer?.loadVideo(key, 0f)
        currentKey = key
    }

    val videoModifier = if (animateToEnd) {
        Modifier.fillMaxWidth()
    } else {
        Modifier.size(120.dp, 120.dp)
    }

    AndroidView(
        modifier = modifier.then(videoModifier),
        factory = { context ->
            YouTubePlayerView(context).apply {
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        movieYouTubePlayer = youTubePlayer
                        youTubePlayer.loadVideo(key, 0f)
                    }
                })
            }
        },
        update = {
            Log.d("Youtube video", "update")
        }
    )
}