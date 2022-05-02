package com.anibalbastias.uikitcompose.components.molecules.youtube

import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentManager
import com.anibalbastias.uikitcompose.R
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX

@Composable
fun YoutubeVideoScreen(
    modifier: Modifier = Modifier,
    playList: List<String>,
    fragmentManager: FragmentManager,
    onError: (String) -> Unit
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            var player: YouTubePlayer? = null

            val onPlaylistChangeListener = object : YouTubePlayer.PlaylistEventListener {
                override fun onPlaylistEnded() {}
                override fun onPrevious() {}
                override fun onNext() {}
            }


            val youtubeApiInitializedListener = object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
                    player = p1
                    player?.setPlaylistEventListener(onPlaylistChangeListener)
                    player?.loadVideos(playList)
                }

                override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                    onError(p1.toString())
                }
            }

            FrameLayout(context).apply {
                // select any R.id.X from your project, it does not matter what it is, but container must have one for transaction below.
                id = R.id.tv_id

                val youtubeView = YouTubePlayerSupportFragmentX()

                fragmentManager
                    .beginTransaction()
                    .add(
                        R.id.tv_id,
                        youtubeView,
                        null
                    )
                    .commit()

                youtubeView.initialize("BuildConfig.youtubeApi", youtubeApiInitializedListener)
            }
        },
        update = { }
    )
}