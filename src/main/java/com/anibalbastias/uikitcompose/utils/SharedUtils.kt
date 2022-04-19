package com.anibalbastias.uikitcompose.utils

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mxalbert.sharedelements.*

object SharedUtils {

    private const val TransitionDurationMillis = 300
    private const val listScreen = "list"
    private const val detailScreen = "details"

    private val fadeOutTransitionSpec = MaterialContainerTransformSpec(
        durationMillis = TransitionDurationMillis,
        fadeMode = FadeMode.Out
    )
    private val crossFadeTransitionSpec = SharedElementsTransitionSpec(
        durationMillis = TransitionDurationMillis,
        fadeMode = FadeMode.Cross,
        fadeProgressThresholds = ProgressThresholds(0.10f, 0.40f)
    )

    private var selectedItem: Int by mutableStateOf(-1)
    private var previousSelectedItem: Int = -1

    @Composable
    fun PrepareShareEffect(listState: LazyListState) {
        LaunchedEffect(listState) {
            val previousIndex = previousSelectedItem.coerceAtLeast(0)
            if (!listState.layoutInfo.visibleItemsInfo.any { it.index == previousIndex }) {
                listState.scrollToItem(previousIndex)
            }
        }
    }

    fun SharedElementsRootScope.changeItem(
        item: Int,
        vararg sharedItems: String
    ) {
        val currentUser = selectedItem
        if (currentUser != item) {
            val targetUser = if (item >= 0) item else currentUser
            if (targetUser >= 0) {
                prepareTransition(sharedItems)
            }
            previousSelectedItem = selectedItem
            selectedItem = item
        }
    }

    @Composable
    fun SharedListRootContainer(
        back: () -> Unit,
        container: @Composable (FiniteAnimationSpec<Float>, Int) -> Unit
    ) {
        SharedElementsRoot {
            BackHandler(enabled = selectedItem >= 0) {
                back.invoke()
                changeItem(-1)
            }

            container.invoke(
                tween(durationMillis = TransitionDurationMillis),
                selectedItem
            )
        }
    }

    @Composable
    fun SharedListBoxContainer(
        image: Any,
        container: @Composable () -> Unit
    ) {
        SharedMaterialContainer(
            key = image,
            screenKey = listScreen,
//            shape = CircleShape,
            color = Color.Transparent,
            transitionSpec = fadeOutTransitionSpec
        ) {
            container.invoke()
        }
    }

    @Composable
    fun SharedListElementContainer(
        title: Any,
        container: @Composable () -> Unit
    ) {
        SharedElement(
            key = title,
            screenKey = listScreen,
            transitionSpec = crossFadeTransitionSpec
        ) {
            container.invoke()
        }
    }

    @Composable
    fun SharedDetailBoxContainer(
        image: Any,
        container: @Composable () -> Unit
    ) {
        SharedMaterialContainer(
            key = image,
            screenKey = detailScreen,
            shape = MaterialTheme.shapes.medium,
            color = Color.Transparent,
            elevation = 10.dp,
            transitionSpec = fadeOutTransitionSpec
        ) {
            container.invoke()
        }
    }

    @Composable
    fun SharedDetailElementContainer(
        title: Any,
        container: @Composable () -> Unit
    ) {
        SharedElement(
            key = title,
            screenKey = detailScreen,
            transitionSpec = crossFadeTransitionSpec
        ) {
            container.invoke()
        }
    }
}