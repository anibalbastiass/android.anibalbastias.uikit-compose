package com.anibalbastias.uikitcompose.utils

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.google.accompanist.systemuicontroller.SystemUiController

@Composable
fun Activity.isExpandedScreen(): Boolean {
    val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
    return widthSizeClass == WindowWidthSizeClass.Expanded
}

@Composable
fun isLandscapeOrientation(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}

fun SystemUiController.showFullScreen(isExpandedScreen: Boolean) {
    isStatusBarVisible = !isExpandedScreen // Status bar
    isNavigationBarVisible = !isExpandedScreen // Navigation bar
    isSystemBarsVisible = !isExpandedScreen // Status & Navigation bars
}