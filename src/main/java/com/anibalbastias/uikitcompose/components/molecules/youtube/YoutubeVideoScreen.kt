package com.anibalbastias.uikitcompose.components.molecules.youtube

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubeVideoScreen(
    modifier: Modifier = Modifier,
    viewModel: YouTubeViewModel,
    animateToEnd: Boolean,
) {
    if (viewModel.previousKey.value.isEmpty() ||
        viewModel.previousKey.value != viewModel.selectedVideo.value.key
    ) {
        viewModel.previousKey.value = viewModel.selectedVideo.value.key

        viewModel.movieYouTubePlayer?.loadVideo(
            viewModel.selectedVideo.value.key,
            viewModel.selectedVideo.value.currentTime.value
        )
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
                        viewModel.movieYouTubePlayer = youTubePlayer
                        viewModel.movieYouTubePlayer?.loadVideo(
                            viewModel.selectedVideo.value.key,
                            viewModel.selectedVideo.value.currentTime.value
                        )
                    }
                })

                addYouTubePlayerListener(object : YouTubePlayerListener {
                    override fun onApiChange(youTubePlayer: YouTubePlayer) {
                        Log.d("YoutubeVideoScreen", "onApiChange")
                    }

                    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                        viewModel.selectedVideo.value.currentTime.value = second
                    }

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError,
                    ) {
                        Log.d("YoutubeVideoScreen", "onError")
                    }

                    override fun onPlaybackQualityChange(
                        youTubePlayer: YouTubePlayer,
                        playbackQuality: PlayerConstants.PlaybackQuality,
                    ) {
                        Log.d("YoutubeVideoScreen", "onPlaybackQualityChange")
                    }

                    override fun onPlaybackRateChange(
                        youTubePlayer: YouTubePlayer,
                        playbackRate: PlayerConstants.PlaybackRate,
                    ) {
                        Log.d("YoutubeVideoScreen", "onPlaybackRateChange")
                    }

                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        Log.d("YoutubeVideoScreen", "onReady")
                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState,
                    ) {
                        when (state) {
                            PlayerConstants.PlayerState.PLAYING -> viewModel.isPlaying.value = true
                            PlayerConstants.PlayerState.PAUSED -> viewModel.isPlaying.value = false
                            else -> {
                                Log.d("YoutubeVideoScreen", "state: ${state.name}")
                            }
                        }
                    }

                    override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
                        Log.d("YoutubeVideoScreen", "onVideoDuration")
                    }

                    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {
                        Log.d("YoutubeVideoScreen", "onVideoId")
                    }

                    override fun onVideoLoadedFraction(
                        youTubePlayer: YouTubePlayer,
                        loadedFraction: Float,
                    ) {
                        Log.d("YoutubeVideoScreen", "onVideoLoadedFraction")
                    }
                })
            }
        },
        update = {
            Log.d("Youtube video", "update")
        }
    )
}