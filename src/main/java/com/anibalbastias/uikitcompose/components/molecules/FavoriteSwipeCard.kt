package com.anibalbastias.uikitcompose.components.molecules

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.DismissDirection.EndToStart
import androidx.compose.material.DismissDirection.StartToEnd
import androidx.compose.material.DismissValue.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import com.anibalbastias.uikitcompose.theme.DarkGrey
import com.anibalbastias.uikitcompose.theme.RedAlpha

/**
 * Modification from
 * https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#swipetodismiss
 */

@ExperimentalMaterialApi
@Composable
fun FavoriteSwipeCard(
    currentState: Boolean,
    screenItem: @Composable (isFavorite: Boolean) -> Unit,
    onSwipe: (isFavorite: Boolean) -> Unit,
) {
    var isFavorite by remember { mutableStateOf(currentState) }
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissedToStart) {
                isFavorite = !isFavorite
                onSwipe(isFavorite)
            }
            it != DismissedToStart
        }
    )
    SwipeToDismiss(
        state = dismissState,
        modifier = Modifier
            .padding(vertical = 0.dp)
            .background(DarkGrey),
        directions = setOf(EndToStart),
        dismissThresholds = { direction ->
            FractionalThreshold(if (direction == StartToEnd) 0.25f else 0.5f)
        },
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    Default -> DarkGrey
                    DismissedToEnd -> DarkGrey
                    DismissedToStart -> RedAlpha
                }
            )
            val alignment = when (direction) {
                StartToEnd -> Alignment.CenterStart
                EndToStart -> Alignment.CenterEnd
            }
            val icon = when (direction) {
                StartToEnd -> Icons.Default.Favorite
                EndToStart -> Icons.Default.Favorite
            }
            val scale by animateFloatAsState(
                if (dismissState.targetValue == Default) 0.75f else 1f
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
                    .size(50.dp)
                    .padding(horizontal = 20.dp),
                contentAlignment = alignment
            ) {
                Icon(
                    icon,
                    tint = Red,
                    contentDescription = "Localized description",
                    modifier = Modifier.scale(scale)
                )
            }
        },
        dismissContent = {
            Card(
                elevation = animateDpAsState(
                    if (dismissState.dismissDirection != null) 4.dp else 0.dp
                ).value
            ) {
                screenItem(isFavorite)
            }
        }
    )
}
