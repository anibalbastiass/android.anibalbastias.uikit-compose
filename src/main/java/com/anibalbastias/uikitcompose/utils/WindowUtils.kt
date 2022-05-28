@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.anibalbastias.uikitcompose.utils

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.SystemUiController

@Composable
fun Activity.isExpandedScreen(): Boolean {
    val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
    return widthSizeClass == WindowWidthSizeClass.Expanded
}

fun SystemUiController.showFullScreen(isExpandedScreen: Boolean) {
    isStatusBarVisible = !isExpandedScreen // Status bar
    isNavigationBarVisible = !isExpandedScreen // Navigation bar
    isSystemBarsVisible = !isExpandedScreen // Status & Navigation bars
}