package com.anibalbastias.uikitcompose.components.molecules.youtube

import android.util.Log
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentManager
import com.anibalbastias.uikitcompose.BuildConfig
import com.anibalbastias.uikitcompose.R
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX

var youTubePlayer: YouTubePlayer? = null

@Composable
fun YoutubeVideoScreen(
    modifier: Modifier = Modifier,
    key: String,
    fragmentManager: FragmentManager,
    firstTime: Boolean,
    onError: (String) -> Unit,
) {
    if (firstTime) {
        youTubePlayer?.loadVideo(key)
    }

    AndroidView(
        modifier = modifier,
        factory = { context ->

            val onPlaylistChangeListener = object : YouTubePlayer.PlaylistEventListener {
                override fun onPlaylistEnded() {}
                override fun onPrevious() {}
                override fun onNext() {}
            }

            val youtubeApiInitializedListener = object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean,
                ) {
                    youTubePlayer = p1
                    youTubePlayer?.fullscreenControlFlags = 0
                    youTubePlayer?.setPlaylistEventListener(onPlaylistChangeListener)
                    youTubePlayer?.loadVideo(key)
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?,
                ) {
                    onError(p1.toString())
                }
            }

            FrameLayout(context).apply {
                id = R.id.tv_id

                val youtubeView = YouTubePlayerSupportFragmentX()

                fragmentManager
                    .beginTransaction()
                    .replace(R.id.tv_id, youtubeView, null)
                    .commit()

                youtubeView.initialize(BuildConfig.YOUTUBE_API_KEY, youtubeApiInitializedListener)
            }
        },
        update = {
            Log.d("Youtube video", "update")
        }
    )
}