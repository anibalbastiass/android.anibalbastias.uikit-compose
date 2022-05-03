package com.anibalbastias.uikitcompose.components.molecules.youtube

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.anibalbastias.uikitcompose.R
import com.anibalbastias.uikitcompose.components.atom.Body1
import com.anibalbastias.uikitcompose.components.atom.Body2

@ExperimentalMotionApi
@Composable
fun YouTubeExpandableScreen(
    background: Color,
    textColor: Color,
    title: String,
    subTitle: String,
    key: String,
    description: String,
) {
    var animatedToEnd by remember {
        mutableStateOf(true)
    }

    val progress by animateFloatAsState(
        targetValue = if (animatedToEnd) 1f else 0f,
        animationSpec = tween(300)
    )

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene1)
            .readBytes()
            .decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp)
            .background(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .layoutId("content_video")
                .background(background)
                .clickable { animatedToEnd = !animatedToEnd }
        ) {
            YoutubeVideoScreen(key = key, animateToEnd = animatedToEnd)
        }

        Box(
            modifier = Modifier.layoutId("video_view")
        )

        Body1(
            text = title,
            color = textColor,
            modifier = Modifier.layoutId("title")
        )

        Body2(
            text = subTitle + "\n\n" + description,
            color = textColor,
            modifier = Modifier.layoutId("description")
        )
    }
}