package com.anibalbastias.uikitcompose.components.molecules.youtube

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.layoutId
import com.anibalbastias.uikitcompose.R
import com.anibalbastias.uikitcompose.components.atom.Body2
import com.anibalbastias.uikitcompose.components.molecules.youtube.YouTubeUtils.getYouTubeThumbnail
import com.anibalbastias.uikitcompose.utils.getMotionScene
import com.anibalbastias.uikitcompose.utils.rememberForeverLazyListState
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@ExperimentalMotionApi
@Composable
fun YouTubeExpandableScreen(
    viewModel: YouTubeViewModel,
    background: Color,
    textColor: Color,
    closeButtonAction: () -> Unit,
) {
    val progress by animateFloatAsState(
        targetValue = if (viewModel.isExpanded) 1f else 0f,
        animationSpec = tween(300)
    )

    val context = LocalContext.current
    val startScene = getMotionScene(context = context, scene = R.raw.youtube_screen_start)
    val endScene = getMotionScene(context = context, scene = R.raw.youtube_screen_end)

    Column(Modifier.padding(bottom = 50.dp)) {
        MotionLayout(
            start = ConstraintSet(startScene),
            end = ConstraintSet(endScene),
            progress = progress,
            modifier = Modifier
                .fillMaxSize()
                .background(if (viewModel.isExpanded) background else Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .layoutId("background", "box")
                    .background(background)
                    .clickable(onClick = {
                        viewModel.isExpanded = !viewModel.isExpanded
                    })
            )
            Box(
                modifier = Modifier
                    .padding(0.dp)
                    .clickable { viewModel.isExpanded = !viewModel.isExpanded }
                    .layoutId("v1", "box")
            ) {
                YoutubeVideoScreen(
                    viewModel = viewModel,
                    animateToEnd = viewModel.isExpanded
                )
            }

            Text(
                text = viewModel.selectedVideo.main,
                modifier = Modifier
                    .layoutId("title")
                    .fillMaxWidth(.5f)
                    .background(background)
                    .clickable { viewModel.isExpanded = !viewModel.isExpanded },
                color = textColor,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = viewModel.selectedVideo.name,
                modifier = Modifier
                    .layoutId("description")
                    .fillMaxWidth(.4f)
                    .background(background)
                    .clickable { viewModel.isExpanded = !viewModel.isExpanded },
                color = textColor,
                maxLines = 1,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.body2,
                overflow = TextOverflow.Ellipsis
            )
            Box(
                modifier = Modifier
                    .layoutId("list", "box")
                    .background(background)
            ) {
                YouTubeVideoList(viewModel, textColor, background)
            }
            Icon(
                painter = painterResource(
                    id = if (viewModel.isPlaying) {
                        R.drawable.ic_pause
                    } else {
                        R.drawable.ic_play
                    }
                ),
                contentDescription = "Play",
                tint = textColor,
                modifier = Modifier
                    .clickable {
                        if (viewModel.isPlaying) {
                            viewModel.movieYouTubePlayer?.pause()
                        } else {
                            viewModel.movieYouTubePlayer?.play()
                        }
                        viewModel.isPlaying = !viewModel.isPlaying
                    }
                    .padding(10.dp)
                    .layoutId("play")
            )

            Icon(
                Icons.Filled.Close,
                contentDescription = "Close",
                tint = textColor,
                modifier = Modifier
                    .clickable { closeButtonAction.invoke() }
                    .padding(10.dp)
                    .layoutId("close")
            )
        }
    }
}

@Composable
fun YouTubeVideoList(
    viewModel: YouTubeViewModel,
    textColor: Color,
    background: Color,
) {
    val lazyListState = rememberForeverLazyListState(key = "VideoMovies")
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier.padding(top = 10.dp, bottom = 140.dp),
        state = lazyListState) {

        itemsIndexed(viewModel.videos) { index, video ->
            Card(
                border = if (video.key == viewModel.selectedVideo.key)
                    BorderStroke(2.dp, textColor)
                else null,
                backgroundColor = background
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .clickable {
                                viewModel.previousMovie = video.main

                                viewModel.selectedVideo = video
                                coroutineScope.launch {
                                    lazyListState.animateScrollToItem(index = index)
                                }
                            }
                            .padding(10.dp)
                    ) {
                        GlideImage(
                            imageModel = getYouTubeThumbnail(video.key),
                            modifier = Modifier
                                .background(Color.Black)
                                .size(50.dp)
                        )

                        Body2(
                            text = video.name,
                            color = textColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }

                    Divider(color = textColor, modifier = Modifier.padding(top = 5.dp))
                }
            }
        }
    }

    ScrollToSelectedVideo(viewModel, lazyListState)
}

@Composable
fun ScrollToSelectedVideo(
    viewModel: YouTubeViewModel,
    lazyListState: LazyListState,
) {
    var selectedIndex = 0
    viewModel.videos.mapIndexed { index, video ->
        if (viewModel.selectedVideo.key == video.key) {
            selectedIndex = index
        }
    }

    // Scroll to selected one
    LaunchedEffect(viewModel.selectedVideo) {
        lazyListState.animateScrollToItem(index = selectedIndex)
    }
}
