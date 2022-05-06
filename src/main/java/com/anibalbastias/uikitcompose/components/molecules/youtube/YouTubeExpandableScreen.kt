package com.anibalbastias.uikitcompose.components.molecules.youtube

import android.content.Context
import androidx.annotation.RawRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.layoutId
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anibalbastias.uikitcompose.R
import com.anibalbastias.uikitcompose.components.atom.Body2
import com.anibalbastias.uikitcompose.components.molecules.youtube.YouTubeUtils.getYouTubeThumbnail
import com.anibalbastias.uikitcompose.utils.rememberForeverLazyListState
import kotlinx.coroutines.launch

@Composable
private fun getMotionScene(
    context: Context,
    @RawRes scene: Int,
): String {
    val motionScene = remember {
        context.resources.openRawResource(scene)
            .readBytes()
            .decodeToString()
    }
    return motionScene
}

@ExperimentalMaterialApi
@ExperimentalMotionApi
@Composable
fun YouTubeExpandableScreen(
    background: Color,
    textColor: Color,
    title: String,
    subTitle: String,
    key: String,
    videos: List<Pair<String, String>>,
    closeButtonAction: () -> Unit,
    isShowVideo: MutableState<Boolean>,
) {
    val currentKey = remember { mutableStateOf(key) }
    val progress by animateFloatAsState(
        targetValue = if (isShowVideo.value) 1f else 0f,
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
                .background(if (isShowVideo.value) background else Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .layoutId("background", "box")
                    .background(background)
                    .clickable(onClick = {
                        isShowVideo.value = !isShowVideo.value
                    })
            )
            Box(
                modifier = Modifier
                    .padding(0.dp)
                    .clickable { isShowVideo.value = !isShowVideo.value }
                    .layoutId("v1", "box")
            ) {
                currentKey.value = key
                YoutubeVideoScreen(key = currentKey, animateToEnd = isShowVideo.value)
            }

            Text(
                text = title,
                modifier = Modifier
                    .layoutId("title")
                    .background(background),
                color = textColor,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = subTitle,
                modifier = Modifier
                    .layoutId("description")
                    .background(background),
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
                val lazyListState = rememberForeverLazyListState(key = "VideoMovies")
                val coroutineScope = rememberCoroutineScope()

                LazyColumn(
                    modifier = Modifier.padding(top = 10.dp, bottom = 100.dp),
                    state = lazyListState) {
                    itemsIndexed(videos) { index, video ->
                        Card(
                            border = if (video.second == currentKey.value) BorderStroke(2.dp,
                                textColor) else null,
                            backgroundColor = background
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier
                                        .clickable {
                                            coroutineScope.launch {
                                                lazyListState.animateScrollToItem(index = index)
                                            }
                                            currentKey.value = video.second
                                        }
                                        .padding(10.dp)
                                ) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(getYouTubeThumbnail(video.second))
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .background(Color.Black)
                                            .height(50.dp)
                                    )

                                    Body2(
                                        text = video.first,
                                        color = textColor,
                                        modifier = Modifier
                                            .padding(start = 10.dp)
                                            .align(Alignment.CenterVertically)
                                    )
                                }

                                Divider(color = textColor, modifier = Modifier.padding(top = 5.dp))
                            }
                        }
                    }
                }
            }
            Icon(
                Icons.Filled.PlayArrow,
                contentDescription = "Play",
                tint = textColor,
                modifier = Modifier
                    .clickable { }
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