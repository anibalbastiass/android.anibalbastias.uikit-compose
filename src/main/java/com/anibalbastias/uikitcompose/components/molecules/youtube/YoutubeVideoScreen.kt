package com.anibalbastias.uikitcompose.components.molecules.youtube

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.anibalbastias.uikitcompose.utils.getActivity
import com.anibalbastias.uikitcompose.utils.isLandscapeOrientation
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController

@Composable
fun YoutubeVideoScreen(
    modifier: Modifier = Modifier,
    viewModel: YouTubeViewModel,
    animateToEnd: Boolean,
    onFullScreen: (isFullScreen: Boolean) -> Unit
) {
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    val currentActivity = LocalContext.current.getActivity()!!
    val isExpandedScreen = isLandscapeOrientation()

    if (viewModel.previousKey.isEmpty() ||
        viewModel.previousKey != viewModel.selectedVideo.key
    ) {
        viewModel.previousKey = viewModel.selectedVideo.key

        viewModel.movieYouTubePlayer?.loadOrCueVideo(
            lifecycleOwner.value.lifecycle,
            viewModel.selectedVideo.key,
            viewModel.selectedVideo.currentTime
        )
    }

    val videoModifier = if (animateToEnd) {
        Modifier.fillMaxWidth()
    } else {
        Modifier.size(90.dp, 90.dp)
    }

    AndroidView(
        modifier = modifier.then(videoModifier),
        factory = { context ->
            val youTubePlayerView = YouTubePlayerView(context).apply {
                enableAutomaticInitialization = false
            }

            val fullScreenListener = object: YouTubePlayerFullScreenListener {
                override fun onYouTubePlayerEnterFullScreen() {
                    onFullScreen(true)
                    currentActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                }

                @SuppressLint("SourceLockedOrientationActivity")
                override fun onYouTubePlayerExitFullScreen() {
                    onFullScreen(false)
                    currentActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }
            }

            val listener = object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val defaultPlayerUiController =
                        DefaultPlayerUiController(youTubePlayerView, youTubePlayer)
                    youTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)

                    viewModel.movieYouTubePlayer = youTubePlayer

                    if (isExpandedScreen) {
                        youTubePlayerView.enterFullScreen()
                    } else {
                        youTubePlayerView.exitFullScreen()
                    }

                    lifecycleOwner.value.lifecycle.addObserver(youTubePlayerView)

                    viewModel.movieYouTubePlayer?.loadOrCueVideo(
                        lifecycleOwner.value.lifecycle,
                        viewModel.selectedVideo.key,
                        viewModel.selectedVideo.currentTime
                    )
                }

                override fun onStateChange(
                    youTubePlayer: YouTubePlayer,
                    state: PlayerConstants.PlayerState,
                ) {
                    when (state) {
                        PlayerConstants.PlayerState.PLAYING -> viewModel.isPlaying = true
                        PlayerConstants.PlayerState.PAUSED -> viewModel.isPlaying = false
                        else -> {
                            Log.d("YoutubeVideoScreen", "state: ${state.name}")
                        }
                    }
                }

                override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                    viewModel.selectedVideo.currentTime = second
                }
            }

            // disable web ui
            val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
            youTubePlayerView.initialize(listener, options)
            youTubePlayerView.addFullScreenListener(fullScreenListener)

            youTubePlayerView
        },
        update = {
            Log.d("Youtube video", "update")
        }
    )
}