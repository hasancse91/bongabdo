package com.hellohasan.bongabdosample.screen.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.hellohasan.bongabdosample.R

sealed class Screen(val route: String, @StringRes val titleRes: Int, val icon: ImageVector) {
    data object Home : Screen("home", R.string.home, Icons.Filled.Home)
    data object Settings : Screen("settings", R.string.settings, Icons.Filled.Settings)
}